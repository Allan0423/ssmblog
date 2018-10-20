package cn.zp.service.impl;

import cn.zp.mapper.BloggerMapper;
import cn.zp.model.Blogger;
import cn.zp.service.IBloggerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 博主service实现类
 */
@Service("bloggerService")
@Transactional(rollbackFor = Exception.class)
public class BloggerServiceImpl implements IBloggerService {

    @Resource
    private BloggerMapper bloggerMapper;

    @Override
    public Blogger findBloggerByName(String name) {
        return bloggerMapper.findBloggerByName(name);
    }

    @Override
    public Blogger findBlogger() {
        return bloggerMapper.findBlogger();
    }

    @Override
    public Integer update(Blogger blogger) {
        return bloggerMapper.update(blogger);
    }
}
