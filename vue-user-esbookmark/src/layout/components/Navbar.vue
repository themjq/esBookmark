<!-- 右侧顶部内容 -->
<template>
  <div class="navbar">
    <!-- 展开/收起 左侧内容 -->
    <!-- <hamburger :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" /> -->
    <!-- 面包屑 -->
    <!-- <breadcrumb class="breadcrumb-container" /> -->

    <div class="right-menu"  >
      <el-dropdown class="avatar-container" trigger="click">
        <div class="demo-type">

          <el-avatar :size="40" :src="userInfo.avatar" @error="errorHandler" style="float: right;">
            <el-avatar> {{ userInfo.username }} </el-avatar>
          </el-avatar>
      </div>
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <router-link to="/system/sysinfo">
            <el-dropdown-item>
              个人主页
            </el-dropdown-item>
          </router-link>

          <el-dropdown-item divided @click.native="logout">
            <span style="display:block;">退出</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>


import { removeToken,getToken } from '@/utils/auth'
import { selectById } from '@/api/user'
export default {
  data(){
    return{
      userInfo:''
    }
  },
  methods: {
    async logout() {
      removeToken()
      this.$router.push('/login')
    },
    errorHandler() {
        return true
    },
    webSocketInit(){
      const webSocketUrl = 'ws://localhost:8080/ws/'+this.userInfo.id;
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
      this.$message.success('接收到消息:'+receiveMessage.message+"\t时间:"+receiveMessage.date)
    },
    webSocketOnError(e){
      console.log('与服务端连接异常->',e)
    },
    webSocketOnClose(e){
      console.log('与服务端连接关闭->',e)
    }
  },
  mounted(){
    const jwt = require('jsonwebtoken');
        const token = getToken()
        try {
          var decodedToken = jwt.decode(token);
          // console.log(decodedToken); 
          let operator= selectById(decodedToken.id)
          operator.then((resp) => {
          if (resp.data.code === 1) {
            this.userInfo=resp.data.data
            this.webSocketInit()
            // console.log(this.userInfo)
          } else {
            this.$message.error("获取用户信息失败")
          }
        })
        } catch (err) {
          console.error('无法解码 JWT:', err);
        }
  }
}
</script>

<style lang="scss" scoped>
.navbar {
  overflow: hidden;
  margin-top: 1vh;
  margin-right: 1vw;

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color:transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 130px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
