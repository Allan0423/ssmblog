package cn.zp.service;

import cn.zp.model.BlogType;

import java.util.List;
import java.util.Map;

public interface IBlogTypeService {

    List<BlogType> countAll();

    List<BlogType> list(Map<String, Object> map);

    Integer getCount(Map<String, Object> map);

    Integer add(BlogType blogType);

    Integer update(BlogType blogType);

    Integer delete(Integer id);

}
