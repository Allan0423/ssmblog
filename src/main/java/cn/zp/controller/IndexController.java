package cn.zp.controller;

import cn.zp.model.Blog;
import cn.zp.model.PageBean;
import cn.zp.service.IBlogService;
import cn.zp.util.PageUtil;
import cn.zp.util.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 主页Controller
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Resource
    private IBlogService blogService;


    /**
     * 请求主页
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value="page", required=false) String page,
                              @RequestParam(value="typeId", required=false) String typeId,
                              @RequestParam(value="releaseDateStr", required=false) String releaseDateStr,
                              HttpServletRequest request){

        ModelAndView mav = new ModelAndView();
        if(StringUtil.isEmpty(page)){
            page = "1";
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page),10);
        Map<String,Object> map = new HashMap<>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("typeId", typeId);
        map.put("releaseDateStr", releaseDateStr == null ? null: new String(releaseDateStr.getBytes(ISO_8859_1), UTF_8));
        List<Blog> blogList = blogService.list(map);
        for(Blog blog : blogList){
            List<String> imagesList = blog.getImageList();
            String blogInfo = blog.getContent();
            Document doc = Jsoup.parse(blogInfo);
            Elements jpgs = doc.select("img[src$=.jpg]");
            for(int i = 0; i < jpgs.size(); i++){
                Element jpg = jpgs.get(i);
                imagesList.add(jpg.toString());
                if(i == 2){
                    break;
                }
            }
        }
        mav.addObject("blogList", blogList);
        StringBuffer param = new StringBuffer();
        if(StringUtil.isNotEmpty(typeId)){
            param.append("typeId=" + typeId+"&");
        }
        if(StringUtil.isNotEmpty(releaseDateStr)){
            param.append("releaseDateStr=" + releaseDateStr+"&");
        }
        mav.addObject("pageCode", PageUtil.genPagination(request.getContextPath()+"/index.html", blogService.getCount(map), Integer.parseInt(page), 10, param.toString()));
        mav.addObject("mainPage", "foreground/blog/list.jsp");
        mav.addObject("pageTitle","Allan的个人博客");
        mav.setViewName("mainTemp");
        return mav;
    }

}
