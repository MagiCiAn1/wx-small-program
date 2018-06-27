package com.srg.exchange.pojo;

import java.util.Date;

public class View {
    private Integer id;

    private String userId;

    private Integer classify;

    private String content;

    private Integer del;

    private Date createTime;

    private Date updateTime;

//    public View(Integer id, String userId, Integer classify, String content, Date createTime, Date updateTime) {
//        this.id = id;
//        this.userId = userId;
//        this.classify = classify;
//        this.content = content;
//        this.createTime = createTime;
//        this.updateTime = updateTime;
//    }

    public View(Integer id, String userId, Integer classify, String content, Integer del, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.classify = classify;
        this.content = content;
        this.del = del;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public View() {
        super();
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
        this.userId = userId == null ? null : userId.trim();
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