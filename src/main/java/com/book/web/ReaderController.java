package com.book.web;

import com.book.dao.RedisDao;
import com.book.domain.ReaderCard;
import com.book.domain.ReaderInfo;
import com.book.service.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import redis.clients.jedis.Tuple;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@Controller
public class ReaderController {

    private ReaderInfoService readerInfoService;
    @Autowired
    public void setReaderInfoService(ReaderInfoService readerInfoService) {
        this.readerInfoService = readerInfoService;
    }
    private LoginService loginService;

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
    private ReaderCardService readerCardService;

    @Autowired
    public void setReaderCardService(ReaderCardService readerCardService) {
        this.readerCardService = readerCardService;
    }

    private RedisDao redisDao;
    @Autowired
    public void setRedisDao(RedisDao redisDao){ this.redisDao = redisDao; }

    @RequestMapping("allreaders.html")
    public ModelAndView allBooks(){
//        redisDao.setSortedSet("new_score","article:00001",2);
//        redisDao.setSortedSet("new_score","article:00002",1);
//        redisDao.setSortedSet("new_score","article:00003",4);
//        redisDao.setSortedSet("score","article:00005",2);
//        redisDao.editSortedSet("score","article:00005",2);
//        String result = (String)redisDao.get("test");

//        Set score1 = redisDao.getSortedSet("new_score",0,1);
//        System.out.println(score1);
//        for(Object i:score1){
//            System.out.println(i+"   ");
//        }

//        String key = "new_score";
//        String bookName = "新书";
//        double buyNumber=2.0;
//        if(redisDao.getPosition(key,"新书")==null){
//            redisDao.addSortedSet(key,bookName,buyNumber);
//        }else{
//            redisDao.editSortedSet(key,bookName,buyNumber);
//        }
//        redisDao.editSortedSet("hotList","大雪中的山庄",2.0);

        ArrayList<ReaderInfo> readers=readerInfoService.readerInfos();
        ModelAndView modelAndView=new ModelAndView("admin_readers");
        modelAndView.addObject("readers",readers);
        return modelAndView;
    }

    @RequestMapping("reader_delete.html")
    public String readerDelete(HttpServletRequest request,RedirectAttributes redirectAttributes){
        int readerId= Integer.parseInt(request.getParameter("readerId"));
        boolean success=readerInfoService.deleteReaderInfo(readerId);

        if(success){
            redirectAttributes.addFlashAttribute("succ", "删除成功！");
            return "redirect:/allreaders.html";
        }else {
            redirectAttributes.addFlashAttribute("error", "删除失败！");
            return "redirect:/allreaders.html";
        }

    }
    @RequestMapping("/reader_info.html")
    public ModelAndView toReaderInfo(HttpServletRequest request) {
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        ReaderInfo readerInfo=readerInfoService.getReaderInfo(readerCard.getReaderId());
        ModelAndView modelAndView=new ModelAndView("reader_info");
        modelAndView.addObject("readerinfo",readerInfo);
        return modelAndView;
    }

    @RequestMapping("/admin_reader_info.html")
    public ModelAndView toAdminReaderInfo(HttpServletRequest request) {
        int readerId = Integer.parseInt(request.getParameter("readerId"));
        ReaderInfo readerInfo=readerInfoService.getReaderInfo(readerId);
        ModelAndView modelAndView=new ModelAndView("admin_reader_info");
        modelAndView.addObject("readerinfo",readerInfo);
        return modelAndView;
    }

    @RequestMapping("reader_edit.html")
    public ModelAndView readerInfoEdit(HttpServletRequest request){
        int readerId= Integer.parseInt(request.getParameter("readerId"));
        ReaderCard readerCard = readerCardService.getReaderCard(readerId);
        int vipState = readerCard.getVipState();
        ReaderInfo readerInfo=readerInfoService.getReaderInfo(readerId);
        //提取并设置出年月日等信息
        boolean succ = readerInfoService.parseDate(readerInfo);

        ModelAndView modelAndView=new ModelAndView("admin_reader_edit");
        modelAndView.addObject("vipState",vipState);
        modelAndView.addObject("readerInfo",readerInfo);
        return modelAndView;
    }

    @RequestMapping("reader_edit_do.html")
    public String readerInfoEditDo(HttpServletRequest request,String telcode,RedirectAttributes redirectAttributes) throws  Exception{
        int vipState = new Integer(request.getParameter("vipState"));
        String name = new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8");
        int readerId= Integer.parseInt(request.getParameter("id"));
        ReaderCard readerCard = loginService.findReaderCardByUserId(readerId);
        String oldName=readerCard.getName();

        String year = new String(request.getParameter("year").getBytes("ISO8859-1"),"UTF-8");
        String month = new String(request.getParameter("month").getBytes("ISO8859-1"),"UTF-8");
        String day = new String(request.getParameter("day").getBytes("ISO8859-1"),"UTF-8");
        String birth = year+"-"+month+"-"+day;
        System.out.println("readerController.birth="+birth);

        boolean succ1 = readerCardService.updateVipState(readerId,vipState);
        if(!oldName.equals(name)){
            boolean succo=readerCardService.updateName(readerId,name);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date nbirth=new Date();
            try{
                java.util.Date date=sdf.parse(birth);
                nbirth=date;
            }catch (ParseException e){
                e.printStackTrace();
            }
            ReaderInfo readerInfo=new ReaderInfo();
            readerInfo.setAddress(new String(request.getParameter("address").getBytes("ISO8859-1"),"UTF-8"));
            readerInfo.setBirth(nbirth);
            readerInfo.setName(name);
            readerInfo.setReaderId(readerId);
            readerInfo.setTelcode(telcode);
            readerInfo.setSex(new String(request.getParameter("sex").getBytes("ISO8859-1"),"UTF-8"));
            boolean succ=readerInfoService.editReaderInfo(readerInfo);
            if(succo&&succ){
                redirectAttributes.addFlashAttribute("succ", "读者信息修改成功！");
                return "redirect:/allreaders.html";
            }else {
                redirectAttributes.addFlashAttribute("error", "读者信息修改失败！");
                return "redirect:/allreaders.html";
            }
        }
        else {
            System.out.println("部分修改");
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date nbirth=new Date();
            try{
                java.util.Date date=sdf.parse(birth);
                nbirth=date;
            }catch (ParseException e){
                e.printStackTrace();
            }
            ReaderInfo readerInfo=new ReaderInfo();
            readerInfo.setAddress(new String(request.getParameter("address").getBytes("ISO8859-1"),"UTF-8"));
            readerInfo.setBirth(nbirth);
            readerInfo.setName(new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8"));
            readerInfo.setReaderId(readerId);
            readerInfo.setTelcode(telcode);
            readerInfo.setSex(new String(request.getParameter("sex").getBytes("ISO8859-1"),"UTF-8"));

            boolean succ=readerInfoService.editReaderInfo(readerInfo);
            if(succ){
                redirectAttributes.addFlashAttribute("succ", "读者信息修改成功！");
                return "redirect:/allreaders.html";
            }else {
                redirectAttributes.addFlashAttribute("error", "读者信息修改失败！");
                return "redirect:/allreaders.html";
            }
        }

    }

    @RequestMapping("reader_add.html")
    public ModelAndView readerInfoAdd(){
        ModelAndView modelAndView=new ModelAndView("admin_reader_add");
        return modelAndView;

    }
    //用户功能--进入修改密码页面
    @RequestMapping("reader_repasswd.html")
    public ModelAndView readerRePasswd(){
        ModelAndView modelAndView=new ModelAndView("reader_repasswd");
        return modelAndView;
    }
    //用户功能--修改密码执行
    @RequestMapping("reader_repasswd_do.html")
    public String readerRePasswdDo(HttpServletRequest request,String oldPasswd,String newPasswd,String reNewPasswd,RedirectAttributes redirectAttributes){
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        int readerId=readerCard.getReaderId();
        String passwd=readerCard.getPasswd();

        if (newPasswd.equals(reNewPasswd)){
            if(passwd.equals(oldPasswd)){
                boolean succ=readerCardService.updatePasswd(readerId,newPasswd);
                if (succ){
                    ReaderCard readerCardNew = loginService.findReaderCardByUserId(readerId);
                    request.getSession().setAttribute("readercard", readerCardNew);
                    redirectAttributes.addFlashAttribute("succ", "密码修改成功！");
                    return "redirect:/reader_repasswd.html";
                }else {
                    redirectAttributes.addFlashAttribute("succ", "密码修改失败！");
                    return "redirect:/reader_repasswd.html";
                }

            }else {
                redirectAttributes.addFlashAttribute("error", "修改失败,原密码错误");
                return "redirect:/reader_repasswd.html";
            }
        }else {
            redirectAttributes.addFlashAttribute("error", "修改失败,两次输入的新密码不相同");
            return "redirect:/reader_repasswd.html";
        }
    }

    //管理员功能--读者信息添加
    @RequestMapping("reader_add_do.html")
    public String readerInfoAddDo(HttpServletRequest request ,String telcode,int readerId,RedirectAttributes redirectAttributes)throws Exception{
        int vipState = new Integer(request.getParameter("vipState"));

        String year = new String(request.getParameter("year").getBytes("ISO8859-1"),"UTF-8");
        String month = new String(request.getParameter("month").getBytes("ISO8859-1"),"UTF-8");
        String day = new String(request.getParameter("day").getBytes("ISO8859-1"),"UTF-8");
        String birth = year+"-"+month+"-"+day;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date nbirth=new Date();
        try{
            java.util.Date date=sdf.parse(birth);
            nbirth=date;
        }catch (ParseException e){
            e.printStackTrace();
        }
        ReaderInfo readerInfo=new ReaderInfo();
        readerInfo.setAddress(new String(request.getParameter("address").getBytes("ISO8859-1"),"UTF-8"));
        readerInfo.setBirth(nbirth);
        readerInfo.setName(new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8"));
        readerInfo.setReaderId(readerId);
        readerInfo.setTelcode(telcode);
        readerInfo.setSex(new String(request.getParameter("sex").getBytes("ISO8859-1"),"UTF-8"));

        boolean succ=readerInfoService.addReaderInfo(readerInfo);
        boolean succc=readerCardService.addReaderCard(readerInfo);
        boolean succc1=readerCardService.updateVipState(readerId,vipState);
        if (succ&&succc&&succc1){
            redirectAttributes.addFlashAttribute("succ", "添加读者信息成功！");
            return "redirect:/allreaders.html";
        }else {
            redirectAttributes.addFlashAttribute("succ", "添加读者信息失败！");
            return "redirect:/allreaders.html";
        }
    }

    //读者功能--读者信息修改
    @RequestMapping("reader_info_edit.html")
    public ModelAndView readerInfoEditReader(HttpServletRequest request){
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        ReaderInfo readerInfo=readerInfoService.getReaderInfo(readerCard.getReaderId());
        //提取并设置出年月日等信息
        boolean succ = readerInfoService.parseDate(readerInfo);
        System.out.println("controller.readerinfor.year="+readerInfo.getYear());
        ModelAndView modelAndView=new ModelAndView("reader_info_edit");
        modelAndView.addObject("readerinfo",readerInfo);
        return modelAndView;

    }
    @RequestMapping("reader_edit_do_r.html")
    public String readerInfoEditDoReader(HttpServletRequest request,String telcode,RedirectAttributes redirectAttributes)throws Exception{
        String name = new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8");
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        String year = new String(request.getParameter("year").getBytes("ISO8859-1"),"UTF-8");
        String month = new String(request.getParameter("month").getBytes("ISO8859-1"),"UTF-8");
        String day = new String(request.getParameter("day").getBytes("ISO8859-1"),"UTF-8");
        String birth = year+"-"+month+"-"+day;
        System.out.println("readerController.birth="+birth);

        if (!readerCard.getName().equals(name)){
            boolean succo=readerCardService.updateName(readerCard.getReaderId(),name);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date nbirth=new Date();
            try{
                java.util.Date date=sdf.parse(birth);
                nbirth=date;
            }catch (ParseException e){
                e.printStackTrace();
            }

            ReaderInfo readerInfo=new ReaderInfo();
            readerInfo.setAddress(new String(request.getParameter("address").getBytes("ISO8859-1"),"UTF-8"));
            readerInfo.setBirth(nbirth);
            readerInfo.setName(name);
            readerInfo.setReaderId(readerCard.getReaderId());
            readerInfo.setTelcode(telcode);
            readerInfo.setSex(new String(request.getParameter("sex").getBytes("ISO8859-1"),"UTF-8"));

            boolean succ=readerInfoService.editReaderInfo(readerInfo);
            if(succ&&succo){
                ReaderCard readerCardNew = loginService.findReaderCardByUserId(readerCard.getReaderId());
                request.getSession().setAttribute("readercard", readerCardNew);
                redirectAttributes.addFlashAttribute("succ", "信息修改成功！");
                return "redirect:/reader_info.html";
            }else {
                redirectAttributes.addFlashAttribute("error", "信息修改失败！");
                return "redirect:/reader_info.html";
            }
        }else {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date nbirth=new Date();
            try{
                java.util.Date date=sdf.parse(birth);
                nbirth=date;
            }catch (ParseException e){
                e.printStackTrace();
            }

            ReaderInfo readerInfo=new ReaderInfo();
            readerInfo.setAddress(new String(request.getParameter("address").getBytes("ISO8859-1"),"UTF-8"));
            readerInfo.setBirth(nbirth);
            readerInfo.setName(name);
            readerInfo.setReaderId(readerCard.getReaderId());
            readerInfo.setTelcode(telcode);
            readerInfo.setSex(new String(request.getParameter("sex").getBytes("ISO8859-1"),"UTF-8"));

            boolean succ=readerInfoService.editReaderInfo(readerInfo);
            if(succ){
                ReaderCard readerCardNew = loginService.findReaderCardByUserId(readerCard.getReaderId());
                request.getSession().setAttribute("readercard", readerCardNew);
                redirectAttributes.addFlashAttribute("succ", "信息修改成功！");
                return "redirect:/reader_info.html";
            }else {
                redirectAttributes.addFlashAttribute("error", "信息修改失败！");
                return "redirect:/reader_info.html";
            }
        }
    }

    @RequestMapping("get_vip_do.html")
    public String getVipDo(HttpServletRequest request,RedirectAttributes redirectAttributes) {
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        int readerId = readerCard.getReaderId();
        int oldVipState = readerCard.getVipState();
        int newVipState = 1;

        boolean succ=readerCardService.updateVipState(readerId,newVipState);

        if(oldVipState==1){
            redirectAttributes.addFlashAttribute("error1","您已经是尊贵的会员，无需再次开通^_^");
        }
        else if((oldVipState==0)&&(succ)){
            //及时更换session信息
            ReaderCard readerCardNew = loginService.findReaderCardByUserId(readerCard.getReaderId());
            request.getSession().setAttribute("readercard", readerCardNew);
            redirectAttributes.addFlashAttribute("succ","会员开通成功！");
        }
        else if((oldVipState==0)&&(!succ)){
            redirectAttributes.addFlashAttribute("error2","会员开通失败！");
        }
        return "redirect:/reader_info.html";
    }
}
