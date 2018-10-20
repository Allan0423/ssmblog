package cn.zp.service;

import cn.zp.model.Comment;

import java.util.List;
import java.util.Map;

public interface ICommentService {

    /**
     * 添加评论
     * @param comment
     * @return
     */
    int add(Comment comment);

    /**
     * 修改评论
     * @param comment
     * @return
     */
    int update(Comment comment);

    /**
     * 查找用户评论
     * @param map
     * @return
     */
    List<Comment> list(Map<String, Object> map);


    /**
     * 获取评论总数
     * @param map
     * @return
     */
    int getCount(Map<String, Object> map);

    /**
     * 删除评论
     * @param id
     * @return
     */
    int delete(Integer id);
}
