package cn.zp.controller.admin;

import cn.zp.model.BlogType;
import cn.zp.model.PageBean;
import cn.zp.service.IBlogService;
import cn.zp.service.IBlogTypeService;
import cn.zp.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 博客类别管理Controller
 */
@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController {

    @Resource
    private IBlogTypeService blogTypeService;

    @Resource
    private IBlogService blogService;

    /**
     * 分页查询博客类别信息
     * @param page
     * @param rows
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public void list(@RequestParam(value="page", required=false)String page,
                     @RequestParam(value="rows", required=false)String rows,
                     HttpServletResponse response)throws Exception{
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String,Object> map = new HashMap();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<BlogType> blogTypeList = blogTypeService.list(map);
        Integer total = blogTypeService.getCount(map);
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(blogTypeList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
    }

    /**
     * 添加或者更新博客类别信息
     * @param blogType
     * @param response
     * @return null
     * @throws Exception
     */
    @RequestMapping("/save")
    public void save(BlogType blogType, HttpServletResponse response)throws Exception{
        int resultTotal;
        if(blogType.getId() == null){
            resultTotal = blogTypeService.add(blogType);
        }else{
            resultTotal = blogTypeService.update(blogType);
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
     * 删除博客类别
     * @param ids
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public void delete(@RequestParam(value="ids", required=false) String ids,
                       HttpServletResponse response)throws Exception{

        String[] idArray = ids.split(",");
        JSONObject result = new JSONObject();
        for(String id : idArray){
            if(blogService.getBlogCountByTypeId(Integer.parseInt(id))>0){
                result.put("exist", "博客类别下有博客，不能删除！");
            }else{
                blogTypeService.delete(Integer.parseInt(id));
            }
        }
        result.put("success", true);
        ResponseUtil.write(response, result);
    }
}
