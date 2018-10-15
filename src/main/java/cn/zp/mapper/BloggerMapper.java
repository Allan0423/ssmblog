package cn.zp.mapper;

import cn.zp.model.Blogger;
import cn.zp.model.TestUser;

import java.util.List;

public interface BloggerMapper {

    /**
     * 通过用户名查询博主
     * @param name
     * @return blogger
     */
    Blogger findBloggerByName(String name);


    /**
     * 查询博主信息的默认方式
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