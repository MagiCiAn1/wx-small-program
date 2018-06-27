// pages/Tou/Tou.js
var app=getApp();
var utils=require('../../utils/util.js');
const API_URL =app.globalData.URL;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    motto: '欢迎使用~',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    windowHeight:'',
    windowWidth:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.getSetting({
      success: function (res) {
        if (res.authSetting['scope.userInfo']) {
          wx.getUserInfo({
            success: function (res) {
              
              app.globalData.userInfo = res.userInfo
              that.setData({
                userInfo: res.userInfo,
                hasUserInfo: true
              })
              var userInfo = res.userInfo;
              wx.login({
                success: function (res_login) {
                  console.log(res_login.code);
                  var code = res_login.code;
                  if (res_login.code) {
                    wx.request({
                      url: API_URL+'user/login.do',
                      data: {
                        'userId': code,
                        'avatarurl': userInfo.avatarUrl,
                        'username': userInfo.nickName
                      },
                      method: 'POST',
                      header: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                      },
                      success: function (res) {
                        console.log(res);
                        app.globalData.userId = res.data.data.userId;
                        app.globalData.auth=res.data.data.auth;
                        if (app.globalData.cookie == null) {
                          app.globalData.cookie = 'JSESSIONID=' + res.data.msg;
                          console.log(app.globalData.userId);
                          setTimeout(function () {
                            wx.switchTab({
                              url: '../index/index',
                              fail: function (e) {
                                console.log(e)
                              }
                            })
                          }, 1000)
                        }
                      }
                    })
                  }
                }
              })

            }
          })
        }
        else {
          // 在没有 open-type=getUserInfo 版本的兼容处理
          wx.getUserInfo({
            success: res => {
              app.globalData.userInfo = res.userInfo
              this.setData({
                userInfo: res.userInfo,
                hasUserInfo: true
              })
              setTimeout(function () {
                wx.switchTab({
                  url: '../index/index',
                  fail: function (e) {
                    console.log(e)
                  }
                })
              }, 3000)
            }
          })
        }

      }
    })  
  },
  onShow:function(){
    var that=this;
    wx.getSystemInfo({
      success: (res) => {
        that.setData({
          windowHeight: res.windowHeight,
          windowWidth: res.windowWidth
        })
      }
    })
  },
  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },
  getUserInfo: function (e) {
    if (e.detail.userInfo) {
      app.globalData.userInfo = e.detail.userInfo;
      console.log(app.globalData);
      this.setData({
        userInfo: e.detail.userInfo,
        hasUserInfo: true
      })
      var userInfo = e.detail.userInfo;
      wx.login({
        success: function (res_login) {
          console.log(res_login.code);
          var code = res_login.code;
          if (res_login.code) {
            wx.request({
              url: API_URL+'user/login.do',
              data: {
                'userId': code,
                'avatarurl': userInfo.avatarUrl,
                'username': userInfo.nickName
              },
              method: 'POST',
              header: {
                'Content-Type': 'application/x-www-form-urlencoded'
              },
              success: function (res) {
                console.log(res.data.msg);
                app.globalData.userId = res.data.data.userId;
                app.globalData.auth = res.data.data.auth;
                app.globalData.cookie = 'JSESSIONID=' + res.data.msg;
                setTimeout(function () {
                  wx.switchTab({
                    url: '../index/index',
                    fail: function (e) {
                      console.log(e)
                    }
                  })
                }, 1000)
              }
            })
          }
        }
      })
    }
    else {
      wx.showModal({
        title: '注意',
        content: '未登录将无法使用该小程序，请登录',
      })
    }
  },
})