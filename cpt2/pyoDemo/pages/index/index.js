//index.js

const app = getApp()
Page({
  data: {
    background: ['demo-text-1', 'demo-text-2', 'demo-text-3'],
    pageData:[
      {
        "uid": 1,
        "userName": "小林",
        "userIcon": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1150476809,1167097731&fm=26&gp=0.jpg",
        "postId": 1,
        "description": "第一个自己拼出来的程序，超级好玩",
        "like": 12,
        "fav": 3,
        "cmmd": 2,
        "img": "https://i.loli.net/2020/12/04/UXnktijVPDwxsbK.png"
      },
      {
        "uid": 2,
        "userName": "小徐",
        "userIcon": "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1871196754,760898084&fm=26&gp=0.jpg",
        "postId": 2,
        "description": "分享个开发现场的美照~",
        "like": 13,
        "fav": 8,
        "cmmd": 2,
        "img": "https://i.loli.net/2020/12/03/bthJA6sWYfwm9c2.png"
      },
      {
        "uid": 0,
        "userName": "兔儿",
        "userIcon": "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1721266040,1911984289&fm=26&gp=0.jpg",
        "postId": 3,
        "description": "我的作品也不错吧",
        "like": 18,
        "fav": 3,
        "cmmd": 6,
        "img": "https://i.loli.net/2020/12/03/IDSz8MYF7huZBtA.png"
      }
    ],
    indicatorDots: false,
    vertical: true,
    autoplay: false,
    circular: true,
    interval: 2000,
    previousMargin: 0,
    nextMargin: 0,
    isDetailPage: false,
    duration: 0,
    comment: null,
    detailBg: "https://i.loli.net/2020/12/02/us5eKVFWy2OHbBA.jpg",
    userInfo: null,
    userData:{"work":1,"fav":12,"follower":13},
    comments: [{
      "pid": 1,
      "userName": "小聪",
      "content": "真的是很有创意的程序呢。",
      "userIcon": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1150476809,1167097731&fm=26&gp=0.jpg"
    }, {
      "pid": 1,
      "userName": "林治",
      "content": "不容易不容易啊",
      "userIcon": "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1871196754,760898084&fm=26&gp=0.jpg"
    }],
    hasUserInfo: false,
    modalName: null,
    discricpt: "",
    imgList: [],
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    showData: {
      "titile": "这是标题",
      "userData": {
        "userName": "DemoUser",
        "userIcon": "https://i.loli.net/2020/12/02/HJxNwqfnYjgyTza.jpg",
        "isFllowed": false
      }
    }
  },
  onLoad: function () {
      this.loadPages();
    //getApp().setWatcher(this); // 设置监听器
  },
  watch: {
    pageData: function (newPage) {
      newPage.forEach(e => {
        e.hotPoint = 0;
        if (e.fav > 5) {
          e.hotPoint += e.fav * 6
        } else {
          e.hotPoint += e.fav * 12
        }
        e.hotPoint += e.like * 3;
      })
      this.setData({
        pageData: newPage
      });
    }
  },
  hideModal() {
    this.setData({
      modalName: null
    });
  },
  ChooseImage() {
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album'], //从相册选择
      success: (res) => {
        this.setData({
          imgList: res.tempFilePaths
        })
      }
    });
  },
  ViewImage(e) {
    wx.previewImage({
      urls: this.data.imgList,
      current: e.currentTarget.dataset.url
    });
  },
  loadPages: function () {
    console.log("aa");
    const that = this;
    //不能启动时登录，必须伴随点击触发
    wx.request({
      url: 'https://migu.plus/api/pyo/allpost.php', //仅为示例
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        res.data.data.forEach(ele => {
          ele.isLiked = false;
          ele.isFaved = false;
          ele.isFollowed = false;
          ele.hotPoint = parseInt(ele.fav) * 12 + parseInt(ele.like) * 5;
        });
        that.setData({
          pageData: res.data.data
        });
        console.log(res.data)
      }
    })
  },
  pageChange(e) {
    console.log(e.detail.current);
    this.setData({
      duration: e.detail.current
    });
  },
  triggerDetailPage() {
    console.log(this.data.isDetailPage);
    this.setData({
      isDetailPage: !this.data.isDetailPage
    })
  },
  shotComment() {
    if (this.data.userInfo == null) {
      this.login();
      return;
    }
    if (this.data.comment == null)
      return false;
    console.log("commenting pid:" + this.data.pageData[this.data.duration].postId);
    let that = this;
    let newcomments = that.data.comments;
    newcomments.push({
      "pid": that.data.pageData[that.data.duration].postId,
      "userName": that.data.userInfo.nickName,
      "content": that.data.comment,
      "userIcon": that.data.userInfo.avatarUrl
    });
    that.setData({
      comments: newcomments
    });
    wx.request({
      url: 'https://migu.plus/api/pyo/postComments?pid=' + that.data.pageData[that.data.duration].postId + '&cmmd=' + that.data.commd + that.data.comment + '&userName=' + that.data.userInfo.nickName + '&userIcon=' + that.data.userInfo.avatarUrl, //发送品论
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (e) {
        if (e.statusCode == 200) {
          console.log("posted");
          let newcomments = that.data.comments;
          newcomments.push({
            "pid": that.data.pageData[that.data.duration].postId,
            "userName": that.data.userInfo.nickName,
            "content": that.data.comment,
            "userIcon": that.data.userInfo.avatarUrl
          });
          that.setData({
            comments: newcomments
          });
        }
      }
    })
  },
  newPost(){
    if(this.data.userInfo==null){
      this.login();
      return;
    }
    this.setData({
      modalName:"postModal"
    });
  },
  openUserInfo(){//打开用户个人页面
    if(this.data.userInfo==null){
      this.login();
      return;
    }
    this.setData({
      modalName:"uInfoModal"
    });
    updateUserInfo();
  },
  openCmmd(e) {
    this.setData({
      modalName:"cmmdModal"
    });
    const pid = e.currentTarget.dataset.pid;
    wx.request({
      url: 'https://migu.plus/api/pyo/getCommd?pid=' + pid, //加载评论
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (e) {
        if (e.statusCode == 200) {
          that.setData({
            comments:e.data
          });
        }
      }
    })
  },
  likePost(e) {
    if (!this.data.userInfo) {
      this.login();
      return;
    }
    const pid = e.currentTarget.dataset.pid;
    if (this.data.pageData[pid - 1].isLiked)
      return;
    console.log("liking pid:" + pid);
    let that = this;
    wx.request({
      url: 'https://migu.plus/api/pyo/likePost?pid=' + pid, //喜欢帖子
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (e) {
        if (e.statusCode == 200) {
          console.log("success");
          let change = 'pageData[' + (pid - 1) + '].isLiked';
          let changeCount = 'pageData[' + (pid - 1) + '].like';
          let likedCount = that.data.pageData[pid - 1].like + 1;
          that.setData({
            [change]: true,
            [changeCount]: likedCount
          });
        }
      }
    })
  },
  favPost(e) {
    const pid = e.currentTarget.dataset.pid;
    if (this.data.pageData[pid - 1].isFaved)
      return;
    let that = this;
    console.log("faving pid:" + pid);
    wx.request({
      url: 'https://migu.plus/api/pyo/favPost?pid=' + pid, //喜欢帖子
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (e) {
        if (e.statusCode == 200) {
          console.log("faild");
          let change = 'pageData[' + (pid - 1) + '].isFaved';
          let changeCount = 'pageData[' + (pid - 1) + '].fav';
          let favedCount = that.data.pageData[pid - 1].fav + 1;
          that.setData({
            [change]: true,
            [changeCount]: favedCount
          });
        }
      }
    })
  },
  discriBInput(e) {
    const input = e.detail.value;
    this.setData({
      discricpt: input
    });
  },
  commentBInput(e) {
    const input = e.detail.value;
    this.setData({
      comment: input
    });
  },
  updateUserInfo(){
    let that=this;
    wx.request({
      url: 'https://migu.plus/api/pyo/userInfo?uName=' + that.data.userInfo.nickName, //获取用户信息（点赞收藏啥的）
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (e) {
        if (e.statusCode == 200) {
          console.log("user Info fetched");
          that.setData({
            userInfo:res.data.data
          });
        }
      }
    })
  },
  uplaod() {
    if (this.data.imgList.length != 1) { //必须有图片
      return;
    }
    wx.uploadFile({
      url: 'migu.plus/api/pyo/upload', //开发者服务器的 url
      filePath: 'this.data.imgList[0]', // 要上传文件资源的路径
      name: 'uploadImg',
      header: {
        'content-type': 'multipart/form-data'
      },
      formData: {
        uploader: this.data.userInfo.userName
      },
      success: function (res) {
        this.hideModal();
      },
      fail: function (res) {}
    })
  },
  login() {
    let that = this;
    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              that.setData({
                userInfo: res.userInfo
              });
              console.log(res.userInfo);
              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  }
})