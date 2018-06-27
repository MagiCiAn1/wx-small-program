package com.srg.exchange.vo;

import com.srg.exchange.pojo.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by srg
 *
 * @date 2018/5/31
 */
public class ViewShowVo {
    private Integer id;

    private String userId;

    private Integer classify;

    private String content;

    private Date createTime;

    private Date updateTime;

    private String username;

    private String avatarurl;

    private List<String> photoUrl;

    public ViewShowVo(Integer id, String userId, Integer classify, String content, Date createTime, Date updateTime, String username, String avatarurl, List<String> photoUrl) {
        this.id = id;
        this.userId = userId;
        this.classify = classify;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.username = username;
        this.avatarurl = avatarurl;
        this.photoUrl = photoUrl;
    }

    public ViewShowVo(ViewVo viewVo){
        this.id = viewVo.getId();
        this.userId = viewVo.getUserId();
        this.classify = viewVo.getClassify();
        this.content = viewVo.getContent();
        this.createTime = viewVo.getCreateTime();
        this.updateTime = viewVo.getUpdateTime();
        this.username = viewVo.getUsername();
        this.avatarurl = viewVo.getAvatarurl();
        this.photoUrl = new ArrayList<>();
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

    public List<String> getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(List<String> photoUrl) {
        this.photoUrl = photoUrl;
    }
}
