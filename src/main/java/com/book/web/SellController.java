package com.book.web;

import com.book.domain.Book;
import com.book.domain.ReaderCard;
import com.book.domain.Sell;
import com.book.service.BookService;
import com.book.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class SellController {
    private SellService sellService;
    private BookService bookService;

    @Autowired
    public void setSellService(SellService sellService,BookService bookService) {
        this.sellService = sellService;
        this.bookService = bookService;
    }

    @RequestMapping("/mybuy.html")
    public ModelAndView myBuy(HttpServletRequest request){
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        ModelAndView modelAndView=new ModelAndView("reader_buy_list");
        modelAndView.addObject("list",sellService.mySellList(readerCard.getReaderId()));
        return modelAndView;
    }

    @RequestMapping("/buy_book_do.html")
    public ModelAndView buyBook(HttpServletRequest request) throws Exception{
        //该参数通过url后缀传过来
        long bookId = Long.valueOf(new String(request.getParameter("bookId").getBytes("ISO8859-1"),"UTF-8"));
        String buyNumber = new String(request.getParameter("buyNumber").getBytes("ISO8859-1"),"UTF-8");
        System.out.println("bookId="+bookId+";"+"buyNumber="+buyNumber);
        Book book = bookService.getBook(bookId);
        int bookNumber = book.getState() - Integer.valueOf(new String(request.getParameter("buyNumber").getBytes("ISO8859-1"),"UTF-8"));

        //设置库存数量
        book.setState(bookNumber);
        System.out.println("book.name"+book.getName());
        //修改书籍的储存信息
        bookService.editBook(book);

        System.out.println(new Date());
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        Sell sell = new Sell();
        sell.setDate(new Date());
        sell.setReaderId(readerCard.getReaderId());
        sell.setBookId(book.getBookId());
        sell.setState(0);

        int vip_state = readerCard.getVipState();
        //判断当期用户是不是会员
        if(vip_state==1){
            sell.setPrice(book.getVipPrice());
        }
        else{
            sell.setPrice(book.getPrice());
        }

        //增加流水信息
        sellService.addSell(sell);

        ModelAndView modelAndView=new ModelAndView("reader_buy_list");
        modelAndView.addObject("list", sellService.mySellList(readerCard.getReaderId()));
        return modelAndView;
    }

    @RequestMapping("/selllist.html")
    public ModelAndView lendList(){
        ModelAndView modelAndView=new ModelAndView("admin_sell_list");
        modelAndView.addObject("list",sellService.sellList());
        return modelAndView;
    }
}
