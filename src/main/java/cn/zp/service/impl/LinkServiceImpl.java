package cn.zp.service.impl;

import cn.zp.mapper.LinkMapper;
import cn.zp.model.Link;
import cn.zp.service.ILinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("linkService")
public class LinkServiceImpl implements ILinkService {

    @Resource
    private LinkMapper linkMapper;

    @Override
    public int add(Link link) {
        return linkMapper.add(link);
    }

    @Override
    public int update(Link link) {
        return linkMapper.update(link);
    }

    @Override
    public List<Link> list(Map<String, Object> map) {
        return linkMapper.list(map);
    }

    @Override
    public int getCount(Map<String, Object> map) {
        return linkMapper.getCount(map);
    }

    @Override
    public int delete(Integer id) {
        return linkMapper.delete(id);
    }
}
