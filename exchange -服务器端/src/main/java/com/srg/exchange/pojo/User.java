package com.srg.exchange.pojo;

import java.util.Date;

public class User {
    private Integer id;

    private String userId;

    private String username;

    private String avatarurl;

    private String name;

    private String school;

    private String schoolCardNo;

    private String phoneNumber;

    private String city;

    private String sex;

    private String email;

    private Integer auth;

    private Date createTime;

    private Date updateTime;

    public User(Integer id, String userId, String username, String avatarurl, String name, String school, String schoolCardNo, String phoneNumber, String city, String sex, String email, Integer auth, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.avatarurl = avatarurl;
        this.name = name;
        this.school = school;
        this.schoolCardNo = schoolCardNo;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.sex = sex;
        this.email = email;
        this.auth = auth;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public User() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl == null ? null : avatarurl.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getSchoolCardNo() {
        return schoolCardNo;
    }

    public void setSchoolCardNo(String schoolCardNo) {
        this.schoolCardNo = schoolCardNo == null ? null : schoolCardNo.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
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