package com.srg.exchange.vo;

import com.srg.exchange.util.CommonUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by srg
 *
 * @date 2018/5/20
 */
public class ViewVo {
    private Integer id;

    private String userId;

    private Integer classify;

    private String content;

    private Date createTime;

    private Date updateTime;

    private String username;

    private String avatarurl;

    public ViewVo(Integer id, String userId, Integer classify, String content, Date createTime, Date updateTime, String username, String avatarurl) {
        this.id = id;
        this.userId = userId;
        this.classify = classify;
        this.content = CommonUtil.decode(content);
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.username = CommonUtil.decode(username);
        this.avatarurl = avatarurl;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }
}
