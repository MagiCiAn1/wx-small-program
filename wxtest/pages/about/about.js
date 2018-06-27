// pages/about/about.js
var app = getApp();
var utils=require('../../utils/util.js')
const API_URL = app.globalData.URL;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    page:0,
    reply:[],
    emojiChar: "😊-😋-😌-😍-😏-😜-😝-😞-😔-😪-😭-😁-😂-😃-😅-😆-👿-😒-😓-😔-😏-😖-😘-😚-😒-😡-😢-😣-😤-😢-😨-😳-😵-😷-😸-😻-😼-😽-😾-😿-🙊-🙋-🙏-✈-🚇-🚃-🚌-🍄-🍅-🍆-🍇-🍈-🍉-🍑-🍒-🍓-🐔-🐶-🐷-👦-👧-👱-👩-👰-👨-👲-👳-💃-💄-💅-💆-💇-🌹-💑-💓-💘-🚲",
    emoji: [
      "60a", "60b", "60c", "60d", "60f",
      "61b", "61d", "61e", "61f",
      "62a", "62c", "62e",
      "602", "603", "605", "606", "608",
      "612", "613", "614", "615", "616", "618", "619", "620", "621", "623", "624", "625", "627", "629", "633", "635", "637",
      "63a", "63b", "63c", "63d", "63e", "63f",
      "64a", "64b", "64f", "681",
      "68a", "68b", "68c",
      "344", "345", "346", "347", "348", "349", "351", "352", "353",
      "414", "415", "416",
      "466", "467", "468", "469", "470", "471", "472", "473",
      "483", "484", "485", "486", "487", "490", "491", "493", "498", "6b4"
    ],
    emojis: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var page=this.data.page;
    var cookie=app.globalData.cookie;
    wx.showNavigationBarLoading();
    var em = {}, emChar = that.data.emojiChar.split("-");
    var emojis = []
    that.data.emoji.forEach(function (v, i) {
      em = {
        char: emChar[i],
        emoji: "0x1f" + v
      };
      emojis.push(em)
    });
    that.setData({
      emojis: emojis
    });
    wx.request({
      url: API_URL + 'comment/list_comment_about_me.do',
      data: {
        'pageNum':page
      },
      method: 'POST',
      header: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Cookie':cookie
      },
      success: function (res) {
        console.log(res);
        var listData=that.data.reply;
        var data = res.data.data;
        var emojis = that.data.emojis;
        for (var i = 0; i < data.length; i++) {
          var get_time = new Date(data[i].createTime);
          var content = data[i].commentContent;
          data[i].createTime = utils.formatTime(get_time);
          data[i].commentContent=utils.formatContent(content,emojis);
          listData.push(data[i]);
        }
        that.setData({
          reply: listData
        })
      },
      complete: function () {
        wx.hideNavigationBarLoading();
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    this.setData({
      text: [],
      page: 0
    })
    this.onLoad();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
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

})