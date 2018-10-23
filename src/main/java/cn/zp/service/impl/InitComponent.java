package cn.zp.service.impl;

import cn.zp.model.Blog;
import cn.zp.model.BlogType;
import cn.zp.model.Blogger;
import cn.zp.model.Link;
import cn.zp.service.IBlogService;
import cn.zp.service.IBlogTypeService;
import cn.zp.service.IBloggerService;
import cn.zp.service.ILinkService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * 应用启动时初始化博主和博客相关信息
 */
public class InitComponent implements ServletContextListener, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();

        // 初始化博主信息
        IBloggerService bloggerService = (IBloggerService) applicationContext.getBean("bloggerService");
        Blogger blogger = bloggerService.findBlogger();
        blogger.setPassword(null);
        application.setAttribute("blogger", blogger);

        // 初始化友情链接信息
        ILinkService linkService = (ILinkService) applicationContext.getBean("linkService");
        List<Link> linkList = linkService.list(null);
        application.setAttribute("linkList", linkList);

        // 初始化博客类别与数量信息
        IBlogTypeService blogTypeService = (IBlogTypeService) applicationContext.getBean("blogTypeService");
        List<BlogType> blogTypeCountList = blogTypeService.countAll();
        application.setAttribute("blogTypeCountList", blogTypeCountList);

        // 根据日期分组查询博客数量
        IBlogService blogService = (IBlogService) applicationContext.getBean("blogService");
        List<Blog> blogList = blogService.countList();
        application.setAttribute("blogList", blogList);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // leave void
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        InitComponent.applicationContext = applicationContext;
    }
}
