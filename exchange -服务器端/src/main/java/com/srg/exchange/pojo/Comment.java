package com.srg.exchange.pojo;

import java.util.Date;

public class Comment {
    private Integer id;

    private String userId;

    private Integer viewId;

    private String content;

    private Integer del;

    private Date createTime;

    private Date updateTime;

    public Comment(Integer id, String userId, Integer viewId, String content, Integer del, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.viewId = viewId;
        this.content = content;
        this.del = del;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Comment() {
        super();
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getViewId() {
        return viewId;
    }

    public void setViewId(Integer viewId) {
        this.viewId = viewId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}