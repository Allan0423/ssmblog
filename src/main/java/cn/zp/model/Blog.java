package cn.zp.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Blog {

    /**
     * 博客编号
     */
    private Integer id;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 摘要
     */
    private String digest;

    /**
     * 发布日期
     */
    private Date releaseDate;

    /**
     * 点击次数
     */
    private Integer clickHit;

    /**
     * 回复次数
     */
    private Integer replyHit;

    /**
     * 博客内容
     */
    private String content;

    /**
     * 去除网页标签的博客内容
     */
    private String contentNoTag;

    /**
     * 博客类型
     */
    private BlogType blogType;

    /**
     * 博客数量
     */
    private Integer blogCount;

    /**
     * 发布日期字符串
     */
    private String releaseDateStr;

    /**
     * 空格分隔的关键字
     */
    private String keyWords;

    /**
     * 博客里存在的图片，主要用于列表展示显示缩略图
     */
    private List<String> imageList = new LinkedList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getClickHit() {
        return clickHit;
    }

    public void setClickHit(Integer clickHit) {
        this.clickHit = clickHit;
    }

    public Integer getReplyHit() {
        return replyHit;
    }

    public void setReplyHit(Integer replyHit) {
        this.replyHit = replyHit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentNoTag() {
        return contentNoTag;
    }

    public void setContentNoTag(String contentNoTag) {
        this.contentNoTag = contentNoTag;
    }

    public BlogType getBlogType() {
        return blogType;
    }

    public void setBlogType(BlogType blogType) {
        this.blogType = blogType;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public String getReleaseDateStr() {
        return releaseDateStr;
    }

    public void setReleaseDateStr(String releaseDateStr) {
        this.releaseDateStr = releaseDateStr;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
}
