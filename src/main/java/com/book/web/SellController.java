package com.book.web;

import com.book.dao.RedisDao;
import com.book.domain.Book;
import com.book.domain.ReaderCard;
import com.book.domain.Sell;
import com.book.service.BookService;
import com.book.service.ReaderCardService;
import com.book.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class SellController {
    private SellService sellService;
    private BookService bookService;
    private ReaderCardService readerCardService;
    private RedisDao redisDao;
    public static final String sortedSetName = "hotListId";

    @Autowired
    public void setSellService(SellService sellService,BookService bookService,ReaderCardService readerCardService,RedisDao redisDao) {
        this.sellService = sellService;
        this.bookService = bookService;
        this.readerCardService = readerCardService;
        this.redisDao = redisDao;
    }

    @RequestMapping("/mybuy.html")
    public ModelAndView myBuy(HttpServletRequest request){
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        ModelAndView modelAndView=new ModelAndView("reader_buy_list");
        ArrayList<Sell> sell_list = sellService.mySellList(readerCard.getReaderId());
        //获取书籍名字
        for(int i=0;i<sell_list.size();i++){
            Sell sell = sell_list.get(i);
            long bookId = sell.getBookId();
            sell_list.get(i).setBookName(bookService.getBook(bookId).getName());
        }
        modelAndView.addObject("list",sell_list);
        return modelAndView;
    }

    @RequestMapping("/buy_book_do.html")
    public ModelAndView buyBook(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
        //该参数通过url后缀传过来
        long bookId = Long.valueOf(new String(request.getParameter("bookId").getBytes("ISO8859-1"),"UTF-8"));
        int buyNumber = new Integer(request.getParameter("buyNumber"));
        Book book = bookService.getBook(bookId);
        int remainderNumber = book.getState() - buyNumber;

        //设置库存数量
        book.setState(remainderNumber);

        //增加订单信息
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        Sell sell = new Sell();
        sell.setDate(new Date());
        sell.setReaderId(readerCard.getReaderId());
        sell.setBookId(book.getBookId());
        sell.setNumber(buyNumber);
        sell.setState(0);

        int vip_state = readerCard.getVipState();
        //判断当期用户是不是会员
        if(vip_state==1){
            sell.setPrice(book.getVipPrice().multiply(new BigDecimal(buyNumber)));
        }
        else{
            sell.setPrice(book.getPrice().multiply(new BigDecimal(buyNumber)));
        }

        boolean buySucc = false;
        String bookIdStr = String.valueOf(bookId);
        try{
            //调用事务方法，修改图书信息同时增加流水单号
            buySucc=sellService.editAndAdd(bookService,book,sell);

            //书籍购买成功，添加信息到redis
            if(redisDao.getPosition(sortedSetName,bookIdStr)==null){
                redisDao.addSortedSet(sortedSetName,bookIdStr,buyNumber);
            }else{
                redisDao.editSortedSet(sortedSetName,bookIdStr,buyNumber);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        ModelAndView modelAndView=new ModelAndView("reader_buy_list");
        ArrayList<Sell> sell_list = sellService.mySellList(readerCard.getReaderId());
        //获取书籍名字
        for(int i=0;i<sell_list.size();i++){
            Sell sellTmp = sell_list.get(i);
            long bookIdTmp = sellTmp.getBookId();
            sell_list.get(i).setBookName(bookService.getBook(bookIdTmp).getName());
        }
        modelAndView.addObject("list",sell_list);
//        modelAndView.addObject("list", sellService.mySellList(readerCard.getReaderId()));
        if (buySucc){
            modelAndView.addObject("succ", "图书购买成功！");
        }else {
            modelAndView.addObject("error", "图书购买失败！");
        }
        return modelAndView;
    }

    @RequestMapping("/selllist.html")
    public ModelAndView lendList(){
        ModelAndView modelAndView=new ModelAndView("admin_sell_list");
        ArrayList<Sell> sell_list = sellService.sellList();
        //获取书籍名字
        for(int i=0;i<sell_list.size();i++){
            Sell sell = sell_list.get(i);
            long bookId = sell.getBookId();
            sell_list.get(i).setBookName(bookService.getBook(bookId).getName());
        }
        modelAndView.addObject("list",sell_list);
        return modelAndView;
    }

    @RequestMapping("/sell_edit.html")
    public ModelAndView sellEdit(HttpServletRequest request){
        int serialNumber = Integer.parseInt(request.getParameter("serialNumber"));
        Sell sell = sellService.getSell(serialNumber);
        ModelAndView modelAndView=new ModelAndView("admin_sell_edit");
        modelAndView.addObject("sell",sell);
        return modelAndView;
    }

    @RequestMapping("/sell_edit_do.html")
    public String sellEditDo(HttpServletRequest request, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
        Sell sell = new Sell();
        sell.setSerialNumber(new Integer(request.getParameter("serialNumber")));
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        try{
            java.util.Date date_tmp=sdf.parse(request.getParameter("date"));
            date=date_tmp;
        }catch (ParseException e){
            e.printStackTrace();
        }
        sell.setDate(date);
        sell.setReaderId(new Integer(request.getParameter("readerId")));
        sell.setNumber(new Integer(request.getParameter("number")));
        sell.setBookId(new Long(request.getParameter("bookId")));
        sell.setState(new Integer(request.getParameter("state")));


        //获取原来的订单信息
        int serialNumber = new Integer(request.getParameter("serialNumber"));
        Sell originSell = sellService.getSell(serialNumber);
        int originNumber = originSell.getNumber();
        long originBookId = originSell.getBookId();

        //还原书籍的库存数量
        Book book = bookService.getBook(originBookId);
        int remainderNumber = book.getState() + originNumber;
        //设置库存数量
        book.setState(remainderNumber);
        //修改书籍的储存信息
        bookService.editBook(book);

        //修改新书籍的库存数量
        long bookId = new Long(request.getParameter("bookId"));
        Book newBook = bookService.getBook(bookId);
        int newRemainderNumber = newBook.getState() - (new Integer(request.getParameter("number")));

        if(bookService.getOneBook(bookId).getName()==null){
            book.setState(remainderNumber - originNumber);
            bookService.editBook(book);
            redirectAttributes.addFlashAttribute("error_bookId","修改订单失败，书籍编号不存在！");
            return "redirect:/selllist.html";
        }
        else if(newRemainderNumber<0){
            book.setState(remainderNumber - originNumber);
            bookService.editBook(book);
            redirectAttributes.addFlashAttribute("error_bookNumber","修改订单失败，库存数量不足！");
            return "redirect:/selllist.html";
        }

        //设置库存数量
        newBook.setState(newRemainderNumber);
        //修改书籍的储存信息
        bookService.editBook(newBook);

        //修改订单信息
        ReaderCard readerCard= readerCardService.getReaderCard(new Integer(request.getParameter("readerId")));
        int vip_state = readerCard.getVipState();
        //判断当期用户是不是会员
        if(vip_state==1){
            sell.setPrice(book.getVipPrice().multiply(new BigDecimal(request.getParameter("number"))));
        }
        else{
            sell.setPrice(book.getPrice().multiply(new BigDecimal(request.getParameter("number"))));
        }

        boolean succ = sellService.editSell(sell);
        if(succ){
            redirectAttributes.addFlashAttribute("succ","订单修改成功");
        }else{
            redirectAttributes.addFlashAttribute("error","订单修改失败");
        }
        return "redirect:/selllist.html";
    }

    @RequestMapping("/sell_delete.html")
    public String deleteSell(HttpServletRequest request,RedirectAttributes redirectAttributes){
        int serialNumber = new Integer(request.getParameter("serialNumber"));
        boolean succ = sellService.deleteSell(serialNumber);
        if(succ){
            redirectAttributes.addFlashAttribute("succ","成功删除订单");
        }else{
            redirectAttributes.addFlashAttribute("error","订单删除失败");
        }
        return "redirect:/selllist.html";
    }

}
