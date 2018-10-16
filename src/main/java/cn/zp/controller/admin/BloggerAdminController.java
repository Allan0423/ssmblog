package cn.zp.controller.admin;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/blogger")
public class BloggerAdminController {

    /**
     * 注销登陆
     * @return 重定向到登陆界面
     */
    @RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "redirect:/login.jsp";
    }
}
