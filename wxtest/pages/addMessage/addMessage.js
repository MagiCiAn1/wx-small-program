// pages/addMessage/addMessage.js
var app=getApp();
const API_URL = app.globalData.URL;
var utils=require('../../utils/util.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    markers:[],
    longitude:'',
    latitude:'',
    classify:'',
    imglist:[],
    item: '../../image/upic.png',
    loading:false,
    disabled:true,
    loadingHide:true,
    content:'',
    userInfo:{},
    index:0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      userInfo: app.globalData.userInfo,
      classify: options.classify
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
  checkimg:function(){
    var that=this;
    var imglist=this.data.imglist;
    wx.chooseImage({
      count:1,
      sizeType: ['original', 'compressed'],
      sourceType:['album','camera'],
      success: function(res) {
        var tempFilePaths=res.tempFilePaths
        console.log(tempFilePaths)
        that.setData({
          imglist: imglist.concat(tempFilePaths)
        })
      },
    })
  },
  formSubmit:function(e){
    var that=this;
    var userInfo=this.data.userInfo;
    var imglist=that.data.imglist;
    var content=e.detail.value.content;
    var classify=that.data.classify;
    var avatar=userInfo.avatarUrl;
    var uname =userInfo.nickName;
    var cookie = app.globalData.cookie;
    if(content.length===0){
      wx.showToast({
        title: '内容不能为空',
        icon: 'loading',
        duration: 1500
      })
    }else{
      wx.showToast({
        title: '请稍后',
        icon: 'loading',
        duration: 1500
      })
      wx.request({
        url: API_URL+'view/insert.do',
        data:{
          content:content,
          classify:classify
        },
        header: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Cookie': cookie
        },
        method: 'POST',
        success:function(res){
          console.log(res);
          var vid=res.data.data;
          console.log(vid);
          if(imglist.length>0){
            for(var i=0;i<imglist.length;i++){
              wx.uploadFile({
                url: API_URL+'view/upload.do',
                filePath: imglist[i],
                name: 'file',
                formData:{
                  'viewId':vid
                },
                method:'POST',
                header: {
                  'Content-Type': 'multipart/form-data',
                  'Cookie':cookie
                },
                success:function(res){
                  console.log(res);
                  if(i>=imglist.length){
                    wx.showToast({
                      title: '发布成功',
                      icon:'sucess',
                      duration:1500
                    })
                    that.setData({
                      imglist:[],
                      loading:true,
                      disabled:true,
                      content:''
                    })
                    setTimeout(function(){
                      wx.switchTab({
                        url: '../index/index',
                      })
                    },2000)
                  }
                }
              })
            }
          }else{
            wx.showToast({
              title: '发布成功',
              icon: 'success',
              duration: 1500
            })
            that.setData({
              loading: true,
              disabled: true,
              content:''
            })
            setTimeout(function () {
              wx.switchTab({
                url: '../index/index',
              })
            }, 2000) 
          }
        }
        
      })
    }
  },
  charchange:function(e){
    var content=e.detail.value;
    if(content.length>0){
      this.setData({
        content:content,
        disabled:false
      })
    }else{
      this.setData({
        content: '',
        disabled: true
      })
    }
  },
  bindPickerChange: function (e) {
    this.setData({
      index: e.detail.value
    })
  },
})