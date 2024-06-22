<template>
    <div>
      <el-table
      highlight-current-row
      ref="multipleTable"
      :data="messageList"
      tooltip-effect="dark"
      style="width: 90%;margin-left: 5%;"
      border
      >
        <el-table-column type="index" width="100" label="序号" header-align="center" align="center"> </el-table-column>
        <el-table-column prop="date" width="100" label="发送时间" header-align="center" align="center"></el-table-column>
        <el-table-column prop="message" label="发送内容" header-align="center" align="center"></el-table-column>
      </el-table>
      <div style="width: 100%;display: flex;justify-content: center;">
        <el-input v-model="sendMessage" style="width:225px;" placeholder="请输入要发送的信息"></el-input>
        <el-button @click="handleSendButton" type="primary">发送</el-button>
      </div>
    </div>
</template>
 
<script>
export default {
  name: "Home",
  data() {
    return {
      webSocketObject: null,
      username: '',
      messageList: [
 
      ],
      sendMessage: ''
    }
  },
  created() {
    //从localStorage中获得username
    this.username = 1
    //如果username不存在返回到登录页面
    //初始化WebSocket
    this.webSocketInit()
  },
  beforeDestroy() {
    this.webSocketObject.close();//在该组件销毁时关闭该连接以节约资源
  },
  methods: {
    webSocketInit(){
      const webSocketUrl = 'ws://localhost:8080/ws/'+1;
      this.webSocketObject = new WebSocket(webSocketUrl);
      this.webSocketObject.onopen = this.webSocketOnOpen
      this.webSocketObject.onmessage = this.webSocketOnMessage
      this.webSocketObject.onerror = this.webSocketOnError
      this.webSocketObject.onclose = this.webSocketOnClose
    },
    webSocketOnOpen(e){
      console.log('与服务端连接打开->',e)
    },
    webSocketOnMessage(e){
      // console.log('接收到消息->',e)
      const receiveMessage = JSON.parse(e.data);
      receiveMessage.date=new Date(receiveMessage.date).toLocaleTimeString()
      this.messageList.push(receiveMessage)
    },
    webSocketOnError(e){
      console.log('与服务端连接异常->',e)
    },
    webSocketOnClose(e){
      console.log('与服务端连接关闭->',e)
    },
    handleSendButton() {
      const message = this.sendMessage
      this.webSocketObject.send(JSON.stringify({
        message
      }))
      this.$message.success('消息发送成功')
      this.sendMessage = ''
    },
  },
}
</script>
 
<style scoped>
 
</style>