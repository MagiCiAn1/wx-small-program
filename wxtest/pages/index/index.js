//index.js
//获取应用实例
var app = getApp()
var utils=require('../../utils/util.js')
const API_URL = app.globalData.URL;

Page({
  data: {
    heart: 'pl.png',
    text: [],
    page: 0,

  },
  //事件处理函数
  onLoad: function () {
    var that = this;
    var page = this.data.page;
    var cookie = app.globalData.cookie;
    console.log('index cookie:' + cookie);
    wx.request({
      url: API_URL + 'view/index.do',
      data: {
        pageNum: page
      },
      method: 'POST',
      header: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Cookie': cookie
      },
      success: function (res) {
        console.log(res);
        var data = res.data.data;
        var listData = that.data.text;
        for (var i = 0; i < data.length; i++) {
          var get_time = new Date(data[i].createTime);
          data[i].createTime = utils.formatTime(get_time);
          listData.push(data[i]);
        }
        that.setData({
          text: listData
        })
      }
    })
  },
  onShow:function(){
    
  },
  onPullDownRefresh: function () {
    this.setData({
      text: [],
      page:0
    })
    this.onLoad();
  },
  onReachBottom: function () {
    var that = this;
    var page = this.data.page+1;
    this.setData({
      page: page
    })
    setTimeout(function () {
      that.onLoad()
    }, 1000)
  },
  onShareAppMessage: function () {
    return {
      title: '说出我们的故事',
      path: '/pages/index/index'
    }
  }
})
