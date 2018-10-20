package cn.zp.mapper;

import cn.zp.model.Link;

import java.util.List;
import java.util.Map;

public interface LinkMapper {

    /**
     * 增加链接
     * @param link
     * @return
     */
    int add(Link link);

    /**
     * 更新链接
     * @param link
     * @return
     */
    int update(Link link);

    /**
     * 查询链接
     * @param map
     * @return
     */
    List<Link> list(Map<String, Object> map);

    /**
     * 查询链接总数
     * @param map
     * @return
     */
    int getCount(Map<String, Object> map);

    /**
     * 删除链接
     * @param id
     * @return
     */
    int delete(Integer id);
}
