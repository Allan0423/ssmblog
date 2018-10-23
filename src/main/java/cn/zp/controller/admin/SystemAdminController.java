package cn.zp.controller.admin;

import cn.zp.model.Blog;
import cn.zp.model.BlogType;
import cn.zp.model.Blogger;
import cn.zp.model.Link;
import cn.zp.service.IBlogService;
import cn.zp.service.IBlogTypeService;
import cn.zp.service.IBloggerService;
import cn.zp.service.ILinkService;
import cn.zp.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/admin/system")
public class SystemAdminController {

    @Resource
    private IBloggerService bloggerService;

    @Resource
    private ILinkService linkService;

    @Resource
    private IBlogTypeService blogTypeService;

    @Resource
    private IBlogService blogService;

    @RequestMapping("/refreshSystem")
    public void refreshSystem(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ServletContext context = RequestContextUtils.getWebApplicationContext(request).getServletContext();

        Blogger blogger = bloggerService.findBlogger();
        // 不能在前台暴露管理员密码
        blogger.setPassword(null);
        context.setAttribute("blogger", blogger);

        List<Link> linkList = linkService.list(null);
        context.setAttribute("linkList", linkList);

        List<BlogType> blogTypeCountList = blogTypeService.countAll();
        context.setAttribute("blogTypeCountList", blogTypeCountList);

        List<Blog> blogCountList = blogService.countList();
        context.setAttribute("blogCountList", blogCountList);

        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }

}
