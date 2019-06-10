package com.book.web;

import com.book.dao.PublishDao;
import com.book.dao.RedisDao;
import com.book.domain.Book;
import com.book.domain.ClassInfo;
import com.book.domain.Publish;
import com.book.service.BookService;
import com.book.service.ClassService;
import com.book.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Set;

@Controller
public class BookController {
    private BookService bookService;
    private RedisDao redisDao;
    private PublishDao publishDao;
    private ClassService classService;
    private PublishService publishService;

    @Autowired
    public void setBookService(BookService bookService,RedisDao redisDao,PublishDao publishDao,ClassService classService,PublishService publishService) {
        this.bookService = bookService;
        this.redisDao = redisDao;
        this.publishDao = publishDao;
        this.classService = classService;
        this.publishService = publishService;
    }

    @RequestMapping("/querybook.html")
    public ModelAndView queryBookDo(HttpServletRequest request,String searchWord){
        boolean exist=bookService.matchBook(searchWord);
        if (exist){
            ArrayList<Book> books = bookService.queryBook(searchWord);
            ModelAndView modelAndView = new ModelAndView("admin_books");
            modelAndView.addObject("books",books);
            return modelAndView;
        }
        else{
            return new ModelAndView("admin_books","error","没有匹配的图书");
        }
    }

    @RequestMapping("/reader_hot_list.html")
    public ModelAndView readerHotList(){
        ArrayList<Book> books = new ArrayList<>();
        Set bookIdListAll = redisDao.getSortedSet(SellController.sortedSetName,0,-1);
        System.out.println(bookIdListAll);
        Set bookIdList = redisDao.getSortedSet(SellController.sortedSetName,0,9);
        System.out.println(bookIdList);
        for(Object id:bookIdList){
            Book book = bookService.getOneBook(Long.parseLong(String.valueOf(id)));
            books.add(book);
        }
        ArrayList<Book> reverseBooks=new ArrayList<>();
        for(int i=books.size()-1;i>=0;i--){
            reverseBooks.add(books.get(i));
        }
        ModelAndView modelAndView = new ModelAndView("reader_book_hotList");
        modelAndView.addObject("books",reverseBooks);
        return modelAndView;
    }

    @RequestMapping("/reader_querybook.html")
    public ModelAndView readerQueryBook(){
       return new ModelAndView("reader_book_query");
    }

    @RequestMapping("/reader_querybook_do.html")
    public String readerQueryBookDo(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
        boolean exist=bookService.matchBook(new String(request.getParameter("searchWord").getBytes("ISO8859-1"),"UTF-8"));
        if (exist){
            ArrayList<Book> books = bookService.queryBook(new String(request.getParameter("searchWord").getBytes("ISO8859-1"),"UTF-8"));
            redirectAttributes.addFlashAttribute("books", books);
            return "redirect:/reader_querybook.html";
        }
        else{
            redirectAttributes.addFlashAttribute("error", "没有匹配的图书！");
            return "redirect:/reader_querybook.html";
        }
    }

    @RequestMapping("/allbooks.html")
    public ModelAndView allBook(){
        ArrayList<Book> books=bookService.getAllBooks();
        ModelAndView modelAndView=new ModelAndView("admin_books");
        modelAndView.addObject("books",books);
        return modelAndView;
    }
    @RequestMapping("/deletebook.html")
    public String deleteBook(HttpServletRequest request,RedirectAttributes redirectAttributes){
        long bookId=Integer.parseInt(request.getParameter("bookId"));
        int res=bookService.deleteBook(bookId);

        if (res==1){
            redirectAttributes.addFlashAttribute("succ", "图书删除成功！");
            return "redirect:/allbooks.html";
        }else {
            redirectAttributes.addFlashAttribute("error", "图书删除失败！");
            return "redirect:/allbooks.html";
        }
    }

    @RequestMapping("/book_add.html")
    public ModelAndView addBook(HttpServletRequest request){
        return new ModelAndView("admin_book_add");
    }

    @RequestMapping("/book_add_do.html")
    public String addBookDo(HttpServletRequest request, BookAddCommand bookAddCommand,RedirectAttributes redirectAttributes) throws Exception{
        Book book = new Book();
        book.setBookId(0);
        book.setName(new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8"));
        book.setAuthor(new String(request.getParameter("author").getBytes("ISO8859-1"),"UTF-8"));
        book.setTranslator(new String(request.getParameter("translator").getBytes("ISO8859-1"),"UTF-8"));
        int publishId = new Integer(new String(request.getParameter("publishId").getBytes("ISO8859-1"),"UTF-8"));
        book.setPublishId(publishId);
        book.setIsbn(new String(request.getParameter("isbn").getBytes("ISO8859-1"),"UTF-8"));
        book.setIntroduction(new String(request.getParameter("introduction").getBytes("ISO8859-1"),"UTF-8"));
        book.setLanguage(new String(request.getParameter("language").getBytes("ISO8859-1"),"UTF-8"));
        book.setPrice(new BigDecimal(new String(request.getParameter("price").getBytes("ISO8859-1"),"UTF-8")));
        book.setVipPrice(new BigDecimal(new String(request.getParameter("vipPrice").getBytes("ISO8859-1"),"UTF-8")));
        book.setPressmark(new Integer(new String(request.getParameter("pressmark").getBytes("ISO8859-1"),"UTF-8")));

        String year = new String(request.getParameter("year").getBytes("ISO8859-1"),"UTF-8");
        String month = new String(request.getParameter("month").getBytes("ISO8859-1"),"UTF-8");
        String day = new String(request.getParameter("day").getBytes("ISO8859-1"),"UTF-8");
        String pubdate = year+"-"+month+"-"+day;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try{
            java.util.Date date=sdf.parse(pubdate);
            book.setPubdate(date);
        }catch (ParseException e){
            e.printStackTrace();
        }

        String classId =new String(request.getParameter("classId").getBytes("ISO8859-1"),"UTF-8");
        book.setClassId(classId);
        book.setState(new Integer(new String(request.getParameter("state").getBytes("ISO8859-1"),"UTF-8")));

        if(classService.querySpecifyClass(classId).getName()==null){
            redirectAttributes.addFlashAttribute("error_class","图书添加失败，分类号不存在");
        }
        else if(publishService.getPublish(publishId).getPublishName()==null){
            redirectAttributes.addFlashAttribute("error_publish","图书添加失败，出版社编号不存在");
        }
        else{
            //分类号和出版社编号无误
            boolean succ=bookService.addBook(book);
            if (succ){
                redirectAttributes.addFlashAttribute("succ", "图书添加成功！");
            }
            else {
                redirectAttributes.addFlashAttribute("succ", "图书添加失败！");
            }
        }
        return "redirect:/allbooks.html";
    }

    @RequestMapping("/updatebook.html")
    public ModelAndView bookEdit(HttpServletRequest request){
        long bookId=Integer.parseInt(request.getParameter("bookId"));
        Book book=bookService.getBook(bookId);
        bookService.parseDate(book);
        ModelAndView modelAndView=new ModelAndView("admin_book_edit");
        modelAndView.addObject("detail",book);
        return modelAndView;
    }

    @RequestMapping("/book_edit_do.html")
    public String bookEditDo(HttpServletRequest request,RedirectAttributes redirectAttributes)throws Exception{
        Book book = new Book();
        book.setBookId(new Integer(new String(request.getParameter("bookId"))));
        book.setName(new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8"));
        book.setAuthor(new String(request.getParameter("author").getBytes("ISO8859-1"),"UTF-8"));
        book.setTranslator(new String(request.getParameter("translator").getBytes("ISO8859-1"),"UTF-8"));
        int publishId = new Integer(new String(request.getParameter("publishId").getBytes("ISO8859-1"),"UTF-8"));
        book.setPublishId(publishId);
        book.setIsbn(new String(request.getParameter("isbn").getBytes("ISO8859-1"),"UTF-8"));
        book.setIntroduction(new String(request.getParameter("introduction").getBytes("ISO8859-1"),"UTF-8"));
        book.setLanguage(new String(request.getParameter("language").getBytes("ISO8859-1"),"UTF-8"));
        book.setPrice(new BigDecimal(new String(request.getParameter("price").getBytes("ISO8859-1"),"UTF-8")));
        book.setVipPrice(new BigDecimal(new String(request.getParameter("vipPrice").getBytes("ISO8859-1"),"UTF-8")));
        book.setPressmark(new Integer(new String(request.getParameter("pressmark").getBytes("ISO8859-1"),"UTF-8")));

        String year = new String(request.getParameter("year").getBytes("ISO8859-1"),"UTF-8");
        String month = new String(request.getParameter("month").getBytes("ISO8859-1"),"UTF-8");
        String day = new String(request.getParameter("day").getBytes("ISO8859-1"),"UTF-8");
        String pubdate = year+"-"+month+"-"+day;

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try{
            java.util.Date date=sdf.parse(pubdate);
            book.setPubdate(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        String classId =new String(request.getParameter("classId").getBytes("ISO8859-1"),"UTF-8");
        book.setClassId(classId);
        book.setState(new Integer(new String(request.getParameter("state").getBytes("ISO8859-1"),"UTF-8")));

        ClassInfo classInfo = classService.querySpecifyClass(classId);
        if(classInfo.getName()==null){
            System.out.println("classInfo为空");
        }
        else{
            System.out.println("classInfo不为空，classInfo="+classInfo.getName());
        }

        if(classService.querySpecifyClass(classId).getName()==null){
            redirectAttributes.addFlashAttribute("error_class","图书修改失败，分类号不存在");
        }
        else if(publishService.getPublish(publishId).getPublishName()==null){
            redirectAttributes.addFlashAttribute("error_publish","图书添加失败，出版社编号不存在");
        }
        else{
            boolean succ=bookService.editBook(book);
            if (succ){
                redirectAttributes.addFlashAttribute("succ", "图书修改成功！");
            }
            else {
                redirectAttributes.addFlashAttribute("error", "图书修改失败！");
            }
        }
        return "redirect:/allbooks.html";
    }

    @RequestMapping("/admin_book_detail.html")
    public ModelAndView bookDetail(HttpServletRequest request){
        long bookId=Integer.parseInt(request.getParameter("bookId"));
        Book book=bookService.getBook(bookId);
        int publishId = book.getPublishId();
        Publish publish = publishDao.getPublish(publishId);
        String classId = book.getClassId();
        ClassInfo classInfo = classService.querySpecifyClass(classId);
        String className = classInfo.getName();
        ModelAndView modelAndView=new ModelAndView("admin_book_detail");
        modelAndView.addObject("publish",publish.getPublishName());
        modelAndView.addObject("className",className);
        modelAndView.addObject("detail",book);
        return modelAndView;
    }

    @RequestMapping("/readerbookdetail.html")
    public ModelAndView readerBookDetail(HttpServletRequest request){
        long bookId=Integer.parseInt(request.getParameter("bookId"));
        Book book=bookService.getBook(bookId);
        int publishId = book.getPublishId();
        Publish publish = publishDao.getPublish(publishId);
        String classId = book.getClassId();
        ClassInfo classInfo = classService.querySpecifyClass(classId);
        String className = classInfo.getName();
        ModelAndView modelAndView=new ModelAndView("reader_book_detail");
        modelAndView.addObject("publish",publish.getPublishName());
        modelAndView.addObject("className",className);
        modelAndView.addObject("detail",book);
        return modelAndView;
    }
}
