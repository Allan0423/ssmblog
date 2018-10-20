package cn.zp.service.impl;

import cn.zp.mapper.CommentMapper;
import cn.zp.model.Comment;
import cn.zp.service.ICommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("commentService")
public class CommentServiceImpl implements ICommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public int add(Comment comment) {
        return commentMapper.add(comment);
    }

    @Override
    public int update(Comment comment) {
        return commentMapper.update(comment);
    }

    @Override
    public List<Comment> list(Map<String, Object> map) {
        return commentMapper.list(map);
    }

    @Override
    public int getCount(Map<String, Object> map) {
        return commentMapper.getCount(map);
    }

    @Override
    public int delete(Integer id) {
        return commentMapper.delete(id);
    }


}
