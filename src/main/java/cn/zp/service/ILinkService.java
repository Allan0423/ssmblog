package cn.zp.service;

import cn.zp.model.Link;

import java.util.List;
import java.util.Map;

public interface ILinkService {

    /**
     * 添加友情链接
     * @param link
     * @return
     */
    int add(Link link);

    /**
     * 修改友情链接
     * @param link
     * @return
     */
    int update(Link link);

    /**
     * 查找友情链接信息
     * @param map
     * @return
     */
    List<Link> list(Map<String, Object> map);

    /**
     * 获取链接总数
     * @param map
     * @return
     */
    int getCount(Map<String, Object> map);

    /**
     * 删除友情链接
     * @param id
     * @return
     */
    int delete(Integer id);
}
