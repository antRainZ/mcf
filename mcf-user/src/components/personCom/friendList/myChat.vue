<template >
  <div>
    <JwChat-index :config="config"  scrollType="noroll"
      :taleList="taleList" @enter="bindEnter" @clickTalk="clickTalk" 
      v-model="inputMsg" :toolConfig="tool" :winBarConfig="winBarConfig"/>
  </div>
</template>

<script>
import { readBySession } from '@/utils/storeUtil'
import { dateFormat } from '@/utils/DateUtil'
import messageApi from '@/api/message'
export default {
  name: 'MyChat',
  props: ['friendList'],
  data () {
    return {
      inputMsg: '',
      taleList: [],
      tool: {
        // show: ["file", "video", "img", "hongbao", "more", "history"] // 现在只能配置这些，二级数组中放自定义名称
        showEmoji: true, // 是否显示表情图标
        callback: this.toolEvent
      },
      config: {
        id: '',
        img: '',
        name: '',
        dept: '',
        callback: this.bindCover,
        historyConfig:{
          show: true,
          tip: '加载更早历史聊天记录',
          callback: this.bindLoadHistory,
        }
      },
      winBarConfig: {
        active: '',
        width: '160px',
        listHeight: '60px',
        list: [],
        callback: this.bindWinBar,
      },
      pageInfo: {
        page: 1,
        limit: 5,
        id: ''
      },
      pages: 0,
      avatar: '',
      nickname: '',
      intervalId: ''
    }
  },
  mounted() {
    this.avatar = readBySession('avatar')
    this.nickname =  readBySession("nickname")
  },
  destroyed() {
    window.clearInterval(this.intervalId)
  },
  watch: {
    friendList: function(newVal) {
      this.friendList = newVal
      if (Array.isArray(newVal)){
        this.winBarConfig.list = newVal
      }
    }
  },
  methods: {
    /**
     * 输入框点击就发送或者回车触发的事件	 参数：输入的原始数据
     */
    bindEnter () {
      const msg = this.inputMsg
      if (!msg) return;
      messageApi.add({
        acceptId: this.config.id,
        acceptName: this.config.name,
        content: msg
      }).then(response=>{
        if(response.code != 200) return
        const msgObj = {
          "date": dateFormat("yyyy-MM-dd hh:mm:ss", new Date()) ,
          "text": { "text": msg },
          "mine": true,
          "name": this.nickname,
          "img": this.avatar
        }
        this.taleList.push(msgObj)
      })
     
    },
    /**
     * @description: 
     * @param {*} type 当前点击的按钮
     * @param {*} plyload 附加文件或者需要处理的数据
     * @return {*}
     */
    toolEvent (type, plyload) {
      console.log('tools', type, plyload)
    },
    /**
     * 点击聊天框列中的用户和昵称触发事件 当前对话数据
     */
    clickTalk(play) {
      //console.log(play)
    },
     /**
     * @description: 点击加载更多的回调函数
     * @param {*}
     * @return {*}
     */
    bindLoadHistory () {
      if (this.pages <= this.pageInfo.page ) {
        this.config.historyConfig.tip = "历史消息已到底"
        return 
      } 
      this.pageInfo.page += 1
      let list = this.getMessageList()
      window.setTimeout(_=>{
        list = list.concat(this.taleList)
        this.taleList = list
      },1000)
    },
    /**
     * 点击聊天框头部的头像和昵称的回调函数
     */
    bindCover (type) {
      //console.log('header', type)
    },
    /**
     * 点击 左侧的好友列表事件
     */
    bindWinBar (play = {}) {
      const {type, data={}} = play
      // console.log(play);
      if( type === 'winBar'){
        this.winBarConfig.active = data.id
        if (data.id == this.config.id) return
        this.config.id = data.id
        this.config.name = data.name
        this.config.img = data.img
        this.pageInfo = {
          page: 1,
          limit: 5,
          id: data.id        
        }
        this.config.historyConfig.tip = "加载更早历史聊天记录" 
        messageApi.reset(data.id).then( _=>{
          this.taleList = this.getMessageList()
          // 清楚之前循环获取用户聊天
          window.clearInterval(this.intervalId)
          // 开启新的获取新的聊天信息
          this.getLatestByInterval()
        })
      }
    },
    getMessageList () {
      let list = []
      messageApi.getMyList(this.pageInfo).then(response => {
        let chatData = response.data.records
        if (!Array.isArray(chatData)) return []
        this.pages = response.data.pages
        for(let i=chatData.length-1;i>=0;--i) {
          if (this.config.id == chatData[i].sendId) {
            list.push({
              "date": chatData[i].createTime ,
              "text": { "text": chatData[i].content },
              "mine": false,
              "name":  this.config.name,
              "img": this.config.img              
            })
          } else {
            list.push({
              "date": chatData[i].createTime ,
              "text": { "text": chatData[i].content },
              "mine": true,
              "name": this.nickname,
              "img": this.avatar              
            })
          }
        }
      })
      return list
    },
    getLatestByInterval () {
      this.intervalId = window.setInterval(_=>{
        messageApi.getLatest(this.config.id).then( response => {
          let data =  response.data
          if (!Array.isArray(data)) return
          for(let i=0,len=data.length;i<len;++i) {
            this.taleList.push({
              "date": data[i].createTime ,
              "text": { "text": data[i].content },
              "mine": false,
              "name": this.config.name,
              "img": this.config.img
            })
          } 
        })
      },60000);
    }
  }
}
</script>

<style>
  .rightBox .switch{
    display: none;
  }
</style>

<style scoped>
  .ChatPage {
    margin-top: 5px;
    margin-left: 160px;
  }
</style>