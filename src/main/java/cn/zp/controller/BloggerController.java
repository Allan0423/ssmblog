package cn.zp.controller;


import cn.zp.model.Blogger;
import cn.zp.service.IBloggerService;
import cn.zp.util.CryptoUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 博主Controller
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BloggerController.class);

    @Resource
    private IBloggerService bloggerService;


    /**
     * 登陆请求交由login方法处理
     * @param blogger
     * @param request
     * @return main.jsp/login
     */
    @RequestMapping("/login")
    public String bloggerLogin(Blogger blogger, HttpServletRequest request){

        String loginSucced = "redirect:/admin/main.jsp";
        String login = "login";

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(blogger.getName(), CryptoUtil.sha256(blogger.getPassword()));
        try{
            // 登陆验证
            subject.login(token);
            return loginSucced;
        }catch (AuthenticationException authExcep) {
            LOGGER.debug(blogger.getName() + " attempted to login but failed!", authExcep);
            request.setAttribute("blogger", blogger);
            request.setAttribute("errorInfo", "用户名或密码错误！");
            return login;
        }
    }


    /**
     * 关于博主
     * @return modelAndView
     */
    /*
    @RequestMapping("/aboutMe")
    public ModelAndView aboutMe(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle", "关于博主");
        modelAndView.addObject("mainPage", "foreground/blogger/info.jsp");
        modelAndView.setViewName("mainTemp");

        return modelAndView;
    }
    */
}
