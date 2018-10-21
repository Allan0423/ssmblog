package cn.zp.controller;

import cn.zp.model.Blog;
import cn.zp.model.Comment;
import cn.zp.service.IBlogService;
import cn.zp.service.ICommentService;
import cn.zp.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 评论Controller
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private ICommentService commentService;

    @Resource
    private IBlogService blogService;

    /**
     * 添加或者修改评论
     * @param comment
     * @param response
     * @return
     */
    @RequestMapping("/save")
    public void save(Comment comment,
                       @RequestParam("imageCode") String imageCode,
                       HttpServletRequest request,
                       HttpServletResponse response,
                       HttpSession session) throws Exception{

        String sRand = (String) session.getAttribute("sRand"); // 获取系统生成的验证码
        JSONObject result = new JSONObject();
                if(!imageCode.equals(sRand)){
            result.put("success", false);
            result.put("errorInfo", "验证码填写错误！");
        }else{
            String userIp = request.getRemoteAddr();
            comment.setUserIp(userIp);
            int resultTotal = commentService.add(comment);
            // 该博客的回复次数加1
            Blog blog = blogService.findById(comment.getBlog().getId());
            blog.setReplyHit(blog.getReplyHit() + 1);
            blogService.update(blog);

            if(resultTotal > 0){
                result.put("success", true);
            }else{
                result.put("success", false);
            }
        }
        ResponseUtil.write(response, result);
    }
}
