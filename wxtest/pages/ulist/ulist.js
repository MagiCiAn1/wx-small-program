// pages/ulist/ulist.js
var app=getApp();
const API_URL = app.globalData.URL;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    page:0,
    openid:'',
    list:[],
    startX:0,
    startY:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
    var page=this.data.page;
    var cookie = app.globalData.cookie;
    wx.showNavigationBarLoading();
    wx.request({
      url: API_URL +'view/list_view_about_me.do',
      data:{
        pageNum:page
      },
      method:'POST',
      header: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Cookie': cookie
      },
      success:function(res){
        console.log(res);
        var data=res.data.data;
        var len=data.length;
        for(var i=0;i<len;i++){

        }
        that.setData({
          list:data
        })
      },
      complete:function(){
        wx.hideNavigationBarLoading();
      }
    })
  },
  touchstart: function (e) {
    this.data.list.forEach(function (v, i) {
      if (v.isTouchMove)
        v.isTouchMove = false;
    })
    this.setData({
      startX: e.changedTouches[0].clientX,
      startY: e.changedTouches[0].clientY,
      list: this.data.list
    })
  },

  touchmove: function (e) {
    var that = this,
      index = e.currentTarget.dataset.index,
      startX = that.data.startX,
      startY = that.data.startY,
      touchMoveX = e.changedTouches[0].clientX,
      touchMoveY = e.changedTouches[0].clientY,
      angle = that.angle({ X: startX, Y: startY }, { X: touchMoveX, Y: touchMoveY });
    that.data.list.forEach(function (v, i) {
      v.isTouchMove = false
      if (Math.abs(angle) > 30) return;
      if (i == index) {
        if (touchMoveX > startX)
          v.isTouchMove = false
        else
          v.isTouchMove = true
      }
    })
    that.setData({
      list: that.data.list
    })
  },

  angle: function (start, end) {
    var _X = end.X - start.X,
      _Y = end.Y - start.Y
    return 360 * Math.atan(_Y / _X) / (2 * Math.PI);
  },
  del: function (e) {
    var that = this;
    var cookie = app.globalData.cookie;
    var openid=this.data.openid;
    var txtId = e.currentTarget.dataset.id;
    that.data.list.splice(e.currentTarget.dataset.index, 1)
    //console.log(that.data.list);
    wx.request({
      url: API_URL + 'view/delete_view.do',
      data:{
        viewId:txtId
      },
      method: 'POST',
      header: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Cookie': cookie
      },
      success: function (res) {
        console.log(res);
        wx.showToast({
          title: '已删除',
          icon: 'success',
          duration: 2000
        })
        that.setData({
          list: that.data.list
        })
      }
    })

  }
})