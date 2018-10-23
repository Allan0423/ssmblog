package cn.zp.model;

public class BlogType {

    /**
     * 类别ID
     */
    private Integer id;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 排序序号
     */
    private Integer orderNo;

    /**
     * 该类别下博客数量
     */
    private Integer blogCount;

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

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }
}
