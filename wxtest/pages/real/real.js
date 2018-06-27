// pages/real/real.js
var app=getApp();
const API_URL=app.globalData.URL;
Page({

  /**
   * 页面的初始数据
   */
  data: {
      school: ['山东科技大学'],
      s: 0,
      major:['计算机科学与工程学院','自动化学院'],
      m:0,
      se:0,
      sex:['男','女'],
      card:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
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
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },
  bindSchoolChange: function (e) {
    this.setData({
      s: e.detail.value
    })
  },
  bindMajorChange: function (e) {
    this.setData({
      m: e.detail.value
    })
  },
  bindSexChange: function (e) {
    this.setData({
      m: e.detail.value
    })
  },
  valueChange:function(e){
    var c=e.detail.value;
    var card=this.data.card;
    if(c.length<=card.length){
      this.setData({
        card: c
      });
    }
    else{
      var len = c.length;
      c = c.substring(len - 1, len);
      if (c != " " && !isNaN(c)) {
        card += c;
      }
      this.setData({
        card: card
      });
    }
    
  },
  formSubmit: function (e) {
    var card=e.detail.value.card;
    var name=e.detail.value.name;
    var school = this.data.school[e.detail.value.school];
    var sex = this.data.sex[e.detail.value.sex];
    var cookie = app.globalData.cookie;
    console.log(school+' '+sex);
    if(card.length!=10||card==""){
      wx.showModal({
        title: '提示',
        content: '请输入正确的校园卡号',
      })
    }
    if(name==""){
      wx.showModal({
        title: '提示',
        content: '请输入真实姓名',
      })
    }
    wx.request({
      url: API_URL+'user/auth.do',
      data: {
        'name': name,
        'school': school,
        'schoolCardNo': card,
        'sex':sex
      },
      method: 'POST',
      header: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Cookie': cookie
      },
      success:function(res){
        console.log(res);
        app.globalData.auth=1;
        setTimeout(function(){
          wx.switchTab({
            url: '../index/index',
            fail: function (e) {
              console.log(e)
            }
          })
        },1000)
      }
    })
  },
})