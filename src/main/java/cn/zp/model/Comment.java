package cn.zp.model;

import java.util.Date;

/**
 * 评论实体类
 */
public class Comment {

    /**
     * 评论编号
     */
    private Integer id;

    /**
     * 评论用户IP
     */
    private String userIp;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 被评论的博客
     */
    private Blog blog;

    /**
     * 评论日期
     */
    private Date date;

    /**
     * 评论状态
     */
    private Integer state;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
