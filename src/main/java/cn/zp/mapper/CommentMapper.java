package cn.zp.mapper;

import cn.zp.model.Comment;

import java.util.List;
import java.util.Map;

public interface CommentMapper {

    /**
     * 新增评论
     * @param comment
     * @return
     */
    int add(Comment comment);

    /**
     * 更新评论状态
     * @param comment
     * @return
     */
    int  update(Comment comment);

    /**
     * 查询评论
     * @param map
     * @return
     */
    List<Comment> list(Map<String, Object> map);

    /**
     * 获取总记录数
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
