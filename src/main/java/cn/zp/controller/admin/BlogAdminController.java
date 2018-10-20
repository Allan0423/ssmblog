package cn.zp.controller.admin;

import cn.zp.lucene.BlogIndex;
import cn.zp.model.Blog;
import cn.zp.model.PageBean;
import cn.zp.service.IBlogService;
import cn.zp.util.JsonDateValueProcessUtil;
import cn.zp.util.ResponseUtil;
import cn.zp.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 博客管理Controller
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

    @Resource
    private IBlogService blogService;

    // 博客索引
    private BlogIndex blogIndex=new BlogIndex();

    /**
     * 添加或者修改博客信息
     * @param blog
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    public void save(Blog blog, HttpServletResponse response)throws Exception{
        int resultTotal;
        if(blog.getId() == null){
            resultTotal = blogService.add(blog);
            blogIndex.addIndex(blog);
        }else{
            resultTotal = blogService.update(blog);
            blogIndex.updateIndex(blog);
        }
        JSONObject result = new JSONObject();
        if(resultTotal > 0){
            result.put("success", true);
        }else{
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
    }

    /**
     * 分页查询博客信息
     * @param page
     * @param rows
     * @param blog
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public void list(@RequestParam(value="page", required=false) String page,
                     @RequestParam(value="rows",required=false) String rows,
                     Blog blog, HttpServletResponse response)throws Exception{

        PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String,Object> map = new HashMap();
        map.put("title", StringUtil.formatLike(blog.getTitle()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Blog> blogList = blogService.list(map);
        int total = blogService.getCount(map);
        JSONObject result = new JSONObject();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessUtil("yyyy-MM-dd"));
        JSONArray jsonArray = JSONArray.fromObject(blogList,jsonConfig);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
    }

    /**
     * 删除博客信息
     * @param ids
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public void delete(@RequestParam(value="ids")String ids,
                         HttpServletResponse response)throws Exception{

        String[] idArray = ids.split(",");
        for(String id : idArray){
            blogService.delete(Integer.parseInt(id));
            blogIndex.deleteIndex(id);
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }

    /**
     * 通过ID查找实体
     * @param id
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById")
    public void findById(@RequestParam(value="id")String id,
                           HttpServletResponse response)throws Exception{

        Blog blog = blogService.findById(Integer.parseInt(id));
        JSONObject jsonObject = JSONObject.fromObject(blog);
        ResponseUtil.write(response, jsonObject);
    }
}
