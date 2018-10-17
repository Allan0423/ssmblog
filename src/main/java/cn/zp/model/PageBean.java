package cn.zp.model;

/**
 * 分页数据模型
 */
public class PageBean {

    private int pageNo;
    private int pageSize;
    private int start;

    public PageBean(int pageNo, int pageSize){
        super();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return start;
    }

}
