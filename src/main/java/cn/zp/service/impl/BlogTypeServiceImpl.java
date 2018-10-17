package cn.zp.service.impl;

import cn.zp.mapper.BlogTypeMapper;
import cn.zp.model.BlogType;
import cn.zp.service.IBlogTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("blogTypeService")
public class BlogTypeServiceImpl implements IBlogTypeService {

    @Resource
    private BlogTypeMapper blogTypeMapper;

    public List<BlogType> countAll() {
        return blogTypeMapper.countAll();
    }

    public List<BlogType> list(Map<String, Object> map) {
        return blogTypeMapper.list(map);
    }

    public Integer getCount(Map<String, Object> map) {
        return blogTypeMapper.getCount(map);
    }

    public Integer add(BlogType blogType) {
        return blogTypeMapper.add(blogType);
    }

    public Integer update(BlogType blogType) {
        return blogTypeMapper.update(blogType);
    }

    public Integer delete(Integer id) {
        return blogTypeMapper.delete(id);
    }

}
