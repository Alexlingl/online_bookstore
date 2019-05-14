package com.book.web;

import com.book.dao.BookDao;
import com.book.dao.PublishDao;
import com.book.domain.Book;
import com.book.domain.Publish;
import com.book.service.BookService;
import com.book.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    private PublishService publishService;

    @Autowired
    public void setBookService(PublishService publishService) {
        this.publishService = publishService;
    }

//    @RequestMapping("/publishdetail.html")
//    public ModelAndView publishDetail(HttpServletRequest request){
//        long publishId=Integer.parseInt(request.getParameter("publishId"));
//        BookDao bookService  = nBookService();
//        Book book=bookService.getBook(bookId);
//        ModelAndView modelAndView=new ModelAndView("admin_book_detail");
//        modelAndView.addObject("detail",book);
//        return modelAndView;
//    }

    @RequestMapping("/readerpublishdetail.html")
    public ModelAndView readerPublishDetail(HttpServletRequest request){
        int publishId=Integer.parseInt(request.getParameter("publishId"));
        Publish publish=publishService.getPublish(publishId);
        ModelAndView modelAndView=new ModelAndView("reader_publish_detail");
        modelAndView.addObject("detail",publish);
        return modelAndView;
    }

}
