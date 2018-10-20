package cn.zp.controller.admin;

import cn.zp.model.Comment;
import cn.zp.model.PageBean;
import cn.zp.service.ICommentService;
import cn.zp.util.JsonDateValueProcessUtil;
import cn.zp.util.ResponseUtil;
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

@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {

    @Resource
    private ICommentService commentService;

    /**
     * 分页查询评论信息
     * @param page
     * @param rows
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public void list(@RequestParam(value="page", required=false) String page,
                       @RequestParam(value="rows", required=false) String rows,
                       @RequestParam(value="state", required=false) String state,
                       HttpServletResponse response)throws Exception{

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String,Object> map=new HashMap<>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("state", state);
        List<Comment> commentList = commentService.list(map);
        int total = commentService.getCount(map);
        JSONObject result = new JSONObject();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessUtil("yyyy-MM-dd"));
        JSONArray jsonArray = JSONArray.fromObject(commentList, jsonConfig);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
    }

    /**
     * 删除评论信息
     * @param ids
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public void delete(@RequestParam(value="ids") String ids,
                       HttpServletResponse response)throws Exception{
        String[] idArray=ids.split(",");
        for(String id : idArray){
            commentService.delete(Integer.parseInt(id));
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }

    /**
     * 评论审核
     * @param ids
     * @param state
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/review")
    public void review(@RequestParam(value="ids")String ids,
                         @RequestParam(value="state")Integer state,
                         HttpServletResponse response)throws Exception{
        String[] idArray=ids.split(",");
        for(String id : idArray){
            Comment comment = new Comment();
            comment.setState(state);
            comment.setId(Integer.parseInt(id));
            commentService.update(comment);
        }
        JSONObject result=new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }
}
