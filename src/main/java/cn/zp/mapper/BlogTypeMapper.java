package cn.zp.mapper;

import cn.zp.model.BlogType;

import java.util.List;
import java.util.Map;


/**
 * 博客类型映射关系接口
 */
public interface BlogTypeMapper {

    /**
     * 查询所有博客类型和对应的博客数量
     * @return 博客类型列表
     */
    List<BlogType> countAll();


    /**
     * 通过id查找博客类型实体
     * @param id
     * @return 博客类型信息
     */
    BlogType findById(Integer id);


    /**
     * 查询博客类型信息
     * @param map
     * @return 博客类型列表
     */
    List<BlogType> list(Map<String, Object> map);


    /**
     * 查询某类型博客总数
     * @param map
     * @return 博客数量
     */
    Integer getCount(Map<String, Object> map);


    /**
     * 增加博客类型
     * @param blogType
     * @return 影响记录数
     */
    Integer add(BlogType blogType);


    /**
     * 更新博客类型
     * @param blogType
     * @return 影响记录数
     */
    Integer update(BlogType blogType);


    /**
     * 删除博客类型
     * @param id
     * @return 影响记录数
     */
    Integer delete(Integer id);

}
