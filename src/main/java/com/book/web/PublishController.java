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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

@Controller
public class PublishController {
    private PublishService publishService;

    @Autowired
    public void setBookService(PublishService publishService) {
        this.publishService = publishService;
    }

    @RequestMapping("/readerpublishdetail.html")
    public ModelAndView readerPublishDetail(HttpServletRequest request) throws UnsupportedEncodingException{
        int publishId=Integer.parseInt(request.getParameter("publishId"));
//        String bookName=new String(request.getParameter("searchWord").getBytes("ISO8859-1"),"UTF-8");
//        System.out.println("BookName:"+bookName);
        Publish publish=publishService.getPublish(publishId);
        ModelAndView modelAndView=new ModelAndView("reader_publish_detail");
        modelAndView.addObject("detail",publish);
//        modelAndView.addObject("searchWord",bookName);
        return modelAndView;
    }

    @RequestMapping("/adminpublishdetail.html")
    public ModelAndView adminPublishDetail(HttpServletRequest request){
        int publishId=Integer.parseInt(request.getParameter("publishId"));
        Publish publish=publishService.getPublish(publishId);
        ModelAndView modelAndView=new ModelAndView("admin_publish_detail");
        modelAndView.addObject("detail",publish);
        return modelAndView;
    }

    @RequestMapping("/adminallpublish.html")
    public ModelAndView adminAllPublish(HttpServletRequest request){
        ArrayList<Publish> publishes = publishService.getAllPublish();
        ModelAndView modelAndView=new ModelAndView("admin_publishes");
        modelAndView.addObject("publishes",publishes);
        return modelAndView;
    }

    @RequestMapping("/publish_edit.html")
    public ModelAndView publishEdit(HttpServletRequest request){
        int publishId=Integer.parseInt(request.getParameter("publishId"));
        Publish publish = publishService.getPublish(publishId);
        ModelAndView modelAndView=new ModelAndView("admin_publish_edit");
        modelAndView.addObject("publish",publish);
        return modelAndView;
    }

    @RequestMapping("/publish_edit_do.html")
    public String publishEditDo(HttpServletRequest request,RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
        Publish publish = new Publish();
        publish.setPublishId(Integer.parseInt(request.getParameter("publishId")));

        publish.setPublishName(new String(request.getParameter("publishName").getBytes("ISO8859-1"),"UTF-8"));
        publish.setAddress(new String(request.getParameter("address").getBytes("ISO8859-1"),"UTF-8"));
        publish.setContacter(new String(request.getParameter("contacter").getBytes("ISO8859-1"),"UTF-8"));
        publish.setEmail(new String(request.getParameter("email").getBytes("ISO8859-1"),"UTF-8"));
        publish.setPhone(new String(request.getParameter("phone").getBytes("ISO8859-1"),"UTF-8"));

        boolean succ = publishService.editPublish(publish);
        if (succ){
            redirectAttributes.addFlashAttribute("succ", "出版社信息修改成功！");
            return "redirect:/adminallpublish.html";
        }
        else {
            redirectAttributes.addFlashAttribute("error", "出版社信息修改失败！");
            return "redirect:/adminallpublish.html";
        }
    }

    @RequestMapping("/publish_delete.html")
    public String readerDelete(HttpServletRequest request, RedirectAttributes redirectAttributes){
        int publishId = Integer.parseInt(request.getParameter("publishId"));
        int res = publishService.deletePublish(publishId);

        if(res==1){
            redirectAttributes.addFlashAttribute("succ", "删除成功！");
            return "redirect:/adminallpublish.html";
        }else {
            redirectAttributes.addFlashAttribute("error", "删除失败！");
            return "redirect:/adminallpublish.html";
        }

    }

    @RequestMapping("/publish_add.html")
    public ModelAndView publishAdd(HttpServletRequest request){
        return new ModelAndView("admin_publish_add");
    }


    @RequestMapping("/publish_add_do.html")
    public String addPblishDo(HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception{
        Publish publish = new Publish();
        publish.setPhone(new String(request.getParameter("phone").getBytes("ISO8859-1"),"UTF-8"));
        publish.setEmail(new String(request.getParameter("email").getBytes("ISO8859-1"),"UTF-8"));
        publish.setContacter(new String(request.getParameter("contacter").getBytes("ISO8859-1"),"UTF-8"));
        publish.setAddress(new String(request.getParameter("address").getBytes("ISO8859-1"),"UTF-8"));
        publish.setPublishName(new String(request.getParameter("publishName").getBytes("ISO8859-1"),"UTF-8"));
        boolean succ = publishService.addPublish(publish);

        ArrayList<Publish> publishes = publishService.getAllPublish();
        if(succ){
            redirectAttributes.addFlashAttribute("succ","出版社添加成功");
        }
        else{
            redirectAttributes.addFlashAttribute("succ","出版社添加失败");
        }
        return "redirect:/adminallpublish.html";
    }

}
