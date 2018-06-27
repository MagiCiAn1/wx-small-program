package com.srg.exchange.vo;

import com.srg.exchange.util.CommonUtil;

import java.util.Date;

/**
 * Created by srg
 *
 * @date 2018/6/1
 */
public class CommentVo {

    private Integer id;

    private String userId;

    private String content;

    private String userName;

    private String avatarurl;

    private Date createTime;

    public CommentVo() {
    }

    public CommentVo(Integer id, String userId, String content, String userName, String avatarurl, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.content = CommonUtil.decode(content);
        this.userName = CommonUtil.decode(userName);
        this.avatarurl = avatarurl;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
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
}
