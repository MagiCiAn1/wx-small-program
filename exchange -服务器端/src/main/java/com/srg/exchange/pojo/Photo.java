package com.srg.exchange.pojo;

import java.util.Date;

public class Photo {
    private Integer id;

    private Integer viewId;

    private String url;

    private Date createTime;

    private Date updateTime;

    public Photo(Integer id, Integer viewId, String url, Date createTime, Date updateTime) {
        this.id = id;
        this.viewId = viewId;
        this.url = url;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Photo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getViewId() {
        return viewId;
    }

    public void setViewId(Integer viewId) {
        this.viewId = viewId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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