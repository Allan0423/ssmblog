package cn.zp.controller;

import cn.zp.lucene.BlogIndex;
import cn.zp.model.Blog;
import cn.zp.service.IBlogService;
import cn.zp.service.ICommentService;
import cn.zp.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 博客Controller层
 *
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private IBlogService blogService;

    @Resource
    private ICommentService commentService;

    // 博客索引
    private BlogIndex blogIndex = new BlogIndex();


    /**
     * 请求博客详细信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/articles/{id}")
    public ModelAndView details(@PathVariable("id") Integer id, HttpServletRequest request)throws Exception{
        ModelAndView mav = new ModelAndView();
        Blog blog = blogService.findById(id);
        String keyWords = blog.getKeyWords();
        if(StringUtil.isNotEmpty(keyWords)){
            String[] keyWordArr = keyWords.split(" ");
            mav.addObject("keyWords", StringUtil.filterWhite(Arrays.asList(keyWordArr)));
        }else{
            mav.addObject("keyWords",null);
        }
        mav.addObject("blog", blog);
        blog.setClickHit(blog.getClickHit() + 1);
        blogService.update(blog);
        Map<String,Object> map=new HashMap<>();
        map.put("blogId", blog.getId());
        map.put("state", 1); // 查询审核通过的评论
        mav.addObject("commentList", commentService.list(map));
        mav.addObject("pageCode", this.genUpAndDownPageCode(blogService.getLastBlog(id),blogService.getNextBlog(id),request.getServletContext().getContextPath()));
        mav.addObject("mainPage", "foreground/blog/view.jsp");
        mav.addObject("pageTitle", blog.getTitle());
        mav.setViewName("mainTemp");
        return mav;
    }

    /**
     * 根据关键字查询相关博客信息
     * @param query
     * @return
     * @throws Exception
     */
    @RequestMapping("/query")
    public ModelAndView search(@RequestParam(value="queryWord", required=false) String query,
                               @RequestParam(value="page", required=false) String page,
                               HttpServletRequest request)throws Exception{

        if(StringUtil.isEmpty(page)){
            page = "1";
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("mainPage", "foreground/blog/result.jsp");
        List<Blog> blogList = blogIndex.searchBlog(query.trim());
        Integer toIndex = blogList.size() >= Integer.parseInt(page) * 10 ? Integer.parseInt(page) * 10 : blogList.size();
        mav.addObject("blogList", blogList.subList((Integer.parseInt(page) - 1) * 10, toIndex));
        mav.addObject("pageCode",this.genUpAndDownPageCode(Integer.parseInt(page), blogList.size(), query,10, request.getServletContext().getContextPath()));
        mav.addObject("query", query);
        mav.addObject("resultTotal", blogList.size());
        mav.addObject("pageTitle","搜索关键字'" + query + "'搜索结果");
        mav.setViewName("mainTemp");
        return mav;
    }

    /**
     * 获取下一篇博客和下一篇博客代码
     * @param lastBlog
     * @param nextBlog
     * @return
     */
    private String genUpAndDownPageCode(Blog lastBlog, Blog nextBlog, String projectContext){
        StringBuffer pageCode = new StringBuffer();
        if(lastBlog == null || lastBlog.getId() == null){
            pageCode.append("<p>上一篇：没有了</p>");
        }else{
            pageCode.append("<p>上一篇：<a href='" + projectContext + "/blog/articles/" + lastBlog.getId() + ".html'>" + lastBlog.getTitle() + "</a></p>");
        }
        if(nextBlog == null || nextBlog.getId() == null){
            pageCode.append("<p>下一篇：没有了</p>");
        }else{
            pageCode.append("<p>下一篇：<a href='" + projectContext + "/blog/articles/" + nextBlog.getId() + ".html'>" + nextBlog.getTitle()+"</a></p>");
        }
        return pageCode.toString();
    }

    /**
     * 获取上一页，下一页代码 查询博客用到
     * @param page 当前页
     * @param totalNum 总记录数
     * @param query 查询关键字
     * @param pageSize 每页大小
     * @param projectContext
     * @return
     */
    private String genUpAndDownPageCode(Integer page, Integer totalNum, String query, Integer pageSize, String projectContext){
        int totalPage = totalNum%pageSize == 0 ? totalNum/pageSize : totalNum/pageSize + 1;
        StringBuffer pageCode = new StringBuffer();
        if(totalPage == 0){
            return "";
        }else{
            pageCode.append("<nav>");
            pageCode.append("<ul class='pager' >");
            if(page > 1){
                pageCode.append("<li><a href='"+ projectContext + "/blog/query.html?page=" + (page - 1) + "&query=" + query + "'>上一页</a></li>");
            }else{
                pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
            }
            if(page < totalPage){
                pageCode.append("<li><a href='" + projectContext + "/blog/query.html?page=" + (page + 1) + "&query=" + query + "'>下一页</a></li>");
            }else{
                pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
            }
            pageCode.append("</ul>");
            pageCode.append("</nav>");
        }
        return pageCode.toString();
    }
}
