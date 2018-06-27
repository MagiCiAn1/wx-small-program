//user.js
//获取应用实例
const app = getApp()
const API_URL=app.globalData.URL;
var utils=require('../../utils/util.js')
Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
    openid:'',
    zhuang:'未认证'
  },
  onLoad: function () {
    var cookie = app.globalData.cookie;
    var that=this;
    wx.request({
      url: API_URL+'user/detail.do',
      method: 'POST',
      header: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Cookie': cookie
      },
      success:function(res){
        console.log(res);
        var data = res.data.data;
        that.setData({
          userInfo:data
        })
      }
    })
    
  },
  onShow(){
    var auth = app.globalData.auth;
    if (auth == 1) {
      this.setData({
        zhuang: '已认证'
      })
    }
  },
  redirect:function(){
    var auth = app.globalData.auth;
    if (auth != 1) {
    wx.navigateTo({
    url: '../real/real',
    success: function(res) {},
    fail: function(res) {},
    complete: function(res) {},
  })
    }
  }
})
