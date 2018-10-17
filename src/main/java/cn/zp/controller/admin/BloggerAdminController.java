package cn.zp.controller.admin;

import cn.zp.model.Blogger;
import cn.zp.service.IBloggerService;
import cn.zp.util.CryptoUtil;
import cn.zp.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 博主信息设置控制器
 * 1. 修改密码
 * 2. 退出登录
 */
@Controller
@RequestMapping("/admin/blogger")
public class BloggerAdminController {

    @Resource
    private IBloggerService bloggerService;


    /**
     * 修改博主密码
     * @param newPassword
     * @param response
     * @return null
     * @throws Exception
     */
    @RequestMapping("/modifyPassword")
    public String modifyPassword(String name, String newPassword, HttpServletResponse response) throws Exception{
       Blogger blogger = new Blogger();
       blogger.setName(name);
       blogger.setPassword(CryptoUtil.sha256WithSalt(newPassword));
       int dbOpResult = bloggerService.update(blogger);
        JSONObject resultJson = new JSONObject();
        if (dbOpResult > 0) {
            resultJson.put("success", true);
        }else {
            resultJson.put("success", false);
        }
        ResponseUtil.write(response, resultJson);
        return null;
    }

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
