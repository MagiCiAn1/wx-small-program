// pages/classify/classify.js
var app = getApp()
const API_URL = app.globalData.URL;
var utils=require('../../utils/util.js')
Page({
  data: {
    heart: 'pl.png',
    text: [],
    page: 0,
    params:null,
    nsdata:false
  },
  //事件处理函数
  onLoad: function (params) {
    var that = this;
    var classify=params.id;
    that.setData({
      params:params
    });
    var page = this.data.page;
    var cookie = app.globalData.cookie;
    wx.request({
      url: API_URL + 'view/list_by_classify.do',
      data: {
        pageNum: page,
        classify:classify
      },
      method: 'POST',
      header: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Cookie': cookie
      },
      success: function (res) {
        var data = res.data.data;
        var listData = that.data.text;
        for (var i = 0; i < data.length; i++) {
          var get_time = new Date(data[i].createTime);
          data[i].createTime = utils.formatTime(get_time);
          //console.log(now);
          listData.push(data[i]);
        }
        if(listData.length!=0){
          that.setData({
            nsdata:true
          })
        }
        console.log(listData)
        that.setData({
          text: listData
        })
      }
    })
  },
  onPullDownRefresh: function () {
    this.setData({
      text: [],
      page: 0
    })
    this.onLoad();
  },
  onReachBottom: function () {
    var that = this;
    var params=this.data.params;
    var page = this.data.page + 1;
    this.setData({
      page: page
    })
    setTimeout(function () {
      that.onLoad(params)
    }, 1000)
  },
  onShareAppMessage: function () {
    return {
      title: '说出我们的故事',
      path: '/pages/index/index'
    }
  }
})
