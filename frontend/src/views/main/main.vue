<template>
  <el-container class="main-wrapper">
    <main-header
      :height="`70px`"
      @openLoginDialog="onOpenLoginDialog"/>
    <el-container class="main-container">
      <el-aside class="hide-on-small" width="240px">
        <main-sidebar
          :width="`240px`"/>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
    <main-footer :height="`110px`"/>
  </el-container>
  <login-dialog
    :open="loginDialogOpen"
    @closeLoginDialog="onCloseLoginDialog"/>
</template>
<style>
  @import "https://unpkg.com/element-plus/lib/theme-chalk/index.css";
  @import './main.css';
  @import '../../common/css/common.css';
  @import '../../common/css/element-plus.css';

</style>
<script>
import LoginDialog from './components/login-dialog'
import MainHeader from './components/main-header'
import MainSidebar from './components/main-sidebar'
import MainFooter from './components/main-footer'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
export default {
  name: 'MainView',
  components: {
    MainHeader,
    MainSidebar,
    MainFooter,
    LoginDialog
  },
  data () {
    return {
      loginDialogOpen: false
    }
  },
  methods: {
    onOpenLoginDialog () {
      this.loginDialogOpen = true
    },
    onCloseLoginDialog () {
      this.loginDialogOpen = false
    },
    async socketCreate(){
        let socket = await SockJS("https://i7d109.p.ssafy.io/ws/game")
        this.stompClient = Stomp.over(socket)
        console.log('소켓 연결하는 중')
        this.stompClient.connect({}, ()=> {
            console.log('연결성공')
            this.isConnect = true
        })
    },
  },
  create(){
    this.socketCreate()
  }
}
</script>
