package com.book.web;

import com.book.domain.Admin;
import com.book.domain.ReaderCard;
import com.book.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

//标注为一个Spring mvc的Controller
@Controller
public class LoginController {

    private LoginService loginService;


    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    //负责处理login.html请求
    //当用户输入"http://localhost:8081/"或者"http://localhost:8081/login.html"时，为其返回/WEBIF/jsp/index.jsp
    @RequestMapping(value = {"/","/login.html"})
    public String toLogin(HttpServletRequest request){
        request.getSession().invalidate();
        return "index";
    }

    //退出登录界面，当用户点击"退出登录"按钮时，将界面跳回到登录界面
    @RequestMapping("/logout.html")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login.html";
    }


    //负责处理loginCheck.html请求
    //请求参数会根据参数名称默认契约自动绑定到相应方法的入参中
    //由index.jsp使用ajax调用，最后返回get其一个res
    @RequestMapping(value = "/api/loginCheck", method = RequestMethod.POST)
    public @ResponseBody Object loginCheck(HttpServletRequest request){
        //获取登录的账号和密码
        int id=Integer.parseInt(request.getParameter("id"));
        String passwd = request.getParameter("passwd");
        //查询账号和密码是否是管理员或者读者
                boolean isReader = loginService.hasMatchReader(id, passwd);
                boolean isAdmin = loginService.hasMatchAdmin(id, passwd);
                //将处理信息存放到一个hashmap中，最后返回给index.jsp中的ajax
                HashMap<String, String> res = new HashMap<String, String>();
                if (isAdmin==false&&isReader==false) {
                    res.put("stateCode", "0");
                    res.put("msg","账号或密码错误！");
                } else if(isAdmin){
                    Admin admin=new Admin();
                    admin.setAdminId(id);
                    admin.setPassword(passwd);
                    //getSession()获取当前用户的会话对象，如果不存在则创建一个新的会话对象；
                    //setAttribute("admin",admin)设置当前会话对象的admin属性为刚刚建立的admin对象
                    request.getSession().setAttribute("admin",admin);
                    res.put("stateCode", "1");
                    res.put("msg","管理员登陆成功！");
                }else {
                    ReaderCard readerCard = loginService.findReaderCardByUserId(id);
                    //设置会话信息
                    request.getSession().setAttribute("readercard", readerCard);
                    res.put("stateCode", "2");
                    res.put("msg","读者登陆成功！");
                }
        return res;
    };

    //当用户以管理员的账号登录时，为其返回/WEBIF/jsp/admin_main.jsp界面
    @RequestMapping("/admin_main.html")
    public ModelAndView toAdminMain(HttpServletResponse response) {
        return new ModelAndView("admin_main");
    }

    //当用户以普通读者的账号登录时,为其返回/WEBIF/jsp/reader_main.jsp界面
    //已废弃，现在当用户以普通身份登录时进入热榜界面
//    @RequestMapping("/reader_main.html")
//    public ModelAndView toReaderMain(HttpServletResponse response) {
//        return new ModelAndView("reader_main");
//    }

    @RequestMapping("/admin_repasswd.html")
    public ModelAndView reAdminPasswd() {

        return new ModelAndView("admin_repasswd");
    }

    @RequestMapping("/admin_repasswd_do")
    public String reAdminPasswdDo(HttpServletRequest request,String oldPasswd,String newPasswd,String reNewPasswd,RedirectAttributes redirectAttributes ) {

        Admin admin=(Admin) request.getSession().getAttribute("admin");
        int id=admin.getAdminId();
        String passwd=loginService.getAdminPasswd(id);

        if(passwd.equals(oldPasswd)){
            boolean succ=loginService.adminRePasswd(id,newPasswd);
            if (succ){
                redirectAttributes.addFlashAttribute("succ", "密码修改成功！");
                return "redirect:/admin_repasswd.html";
            }
            else {
                redirectAttributes.addFlashAttribute("error", "密码修改失败！");
                return "redirect:/admin_repasswd.html";
            }
        }else {
            redirectAttributes.addFlashAttribute("error", "旧密码错误！");
            return "redirect:/admin_repasswd.html";
        }
    };

    //配置404页面
     @RequestMapping("*")
     public String notFind(){
     return "404";
       }


}