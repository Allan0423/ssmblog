package cn.zp.service;

import cn.zp.model.Blogger;

/**
 * 博主service接口
 */
public interface IBloggerService {

    /**
     * 通过用户名查询博主信息
     * @param name
     * @return
     */
    Blogger findBloggerByName(String name);


    /**
     * 查询默认博主信息
     * @return
     */
    Blogger findBlogger();


    /**
     * 更新博主信息
     * @param blogger
     * @return
     */
    Integer update(Blogger blogger);
}
