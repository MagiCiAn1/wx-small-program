package com.srg.exchange.vo;

import com.srg.exchange.util.CommonUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by srg
 *
 * @date 2018/6/3
 */
public class ViewCommentVo {

    private String avatarurl;

    private String username;

    private String commentContent;

    private Date createTime;

    private Integer viewId;

    private String viewContent;

    public ViewCommentVo(String avatarurl, String username, String commentContent, Date createTime, Integer viewId, String viewContent) {
        this.avatarurl = avatarurl;
        this.username = CommonUtil.decode(username);
        this.commentContent = CommonUtil.decode(commentContent);
        this.createTime = createTime;
        this.viewId = viewId;
        this.viewContent = CommonUtil.decode(viewContent);
    }

    public ViewCommentVo() {
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
}
