<template>
  <div class="home">
    <HomeCarousel />
  </div>
</template>

<script>
// @ is an alias to /src
import HomeCarousel from '@/views/home/components/HomeCarousel.vue'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

const serverUrl = "https://i7d109.p.ssafy.io/ws/game"
export default {
  name: 'HomeView',
  components: {
    HomeCarousel
  },
  methods : {
    async socketCreate(){
        let socket = await SockJS("http://localhost:8080")
        this.stompClient = Stomp.over(socket)
        console.log('소켓 연결하는 중')
        this.stompClient.connect({}, ()=> {
            console.log('연결성공')
            this.isConnect = true
        })
    },
  },
  created(){
    this.socketCreate()
  }
}
</script>
