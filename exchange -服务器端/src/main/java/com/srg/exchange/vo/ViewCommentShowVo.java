package com.srg.exchange.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by srg
 *
 * @date 2018/6/3
 */
public class ViewCommentShowVo {

    private String avatarurl;

    private String username;

    private String commentContent;

    private Date createTime;

    private Integer viewId;

    private String viewContent;

    private List<String> photoUrl;

    public ViewCommentShowVo(ViewCommentVo viewCommentVo){
        this.avatarurl = viewCommentVo.getAvatarurl();
        this.username = viewCommentVo.getUsername();
        this.commentContent = viewCommentVo.getCommentContent();
        this.createTime = viewCommentVo.getCreateTime();
        this.viewId = viewCommentVo.getViewId();
        this.viewContent = viewCommentVo.getViewContent();
    }

    public ViewCommentShowVo() {
    }

    public Integer getViewId() {
        return viewId;
    }

    public void setViewId(Integer viewId) {
        this.viewId = viewId;
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getViewContent() {
        return viewContent;
    }

    public void setViewContent(String viewContent) {
        this.viewContent = viewContent;
    }

    public List<String> getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(List<String> photoUrl) {
        this.photoUrl = photoUrl;
    }
}
