package cn.zp.controller.admin;

import cn.zp.model.Link;
import cn.zp.model.PageBean;
import cn.zp.service.ILinkService;
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

@Controller
@RequestMapping("/admin/link")
public class LinkAdminController {


    @Resource
    private ILinkService linkService;

    /**
     * 分页查询友情链接信息
     * @param page
     * @param rows
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public void list(@RequestParam(value="page", required=false) String page,
                     @RequestParam(value="rows",required=false)String rows,
                     HttpServletResponse response)throws Exception{
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String,Object> map = new HashMap<>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Link> linkList = linkService.list(map);
        int total = linkService.getCount(map);
        JSONObject result=new JSONObject();
        JSONArray jsonArray=JSONArray.fromObject(linkList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
    }

    /**
     * 添加或者修改友情链接信息
     * @param link
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    public void save(Link link, HttpServletResponse response)throws Exception{
        // 影响的的记录条数
        int opCount;
        // 没有id，视为新链接记录，使用add方法
        if(link.getId() == null){
            opCount = linkService.add(link);
        }else{
            opCount = linkService.update(link);
        }
        JSONObject result = new JSONObject();
        if(opCount > 0){
            result.put("success", true);
        }else{
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
    }

    /**
     * 删除友情链接信息
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
            linkService.delete(Integer.parseInt(id));
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }
}
