package cn.zp.model;

/**
 * 链接实体类
 */
public class Link {

    /**
     * 链接编号
     */
    private Integer id;

    /**
     * 链接名称
     */
    private String name;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 排序序号 从小到大排序
     */
    private Integer orderNo;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

}
