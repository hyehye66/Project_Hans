<template>
<div class="bg-gray-100 flex items-center justify-center py-5">
<div class="containter mx-auto lg-2 px-20">
  
<ChatMainCardListItem v-for="room in rooms" :room="room" :key="room.id" :mode="mode" />  </div></div>
</template>

<script>
import axios from 'axios';
import ChatMainCardListItem from './ChatMainCardListItem.vue'


const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";

// const OPENVIDU_SERVER_URL = "https://i7d109.p.ssafy.io:4443";
// const OPENVIDU_SERVER_SECRET = "hans";

export default {
  data () {
    return {
      rooms : '',
      temp : '',
      mode : 'chat'
    }
  },
  components : {
    ChatMainCardListItem
  },
  methods : {
    // 모든 세션 데이터 받아오는 함수 
    getSession(){
			axios.get(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
			{auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						}},)
            .then(res =>{this.rooms = res.data.content})
            .catch(err => console.log(err))

    },
    
    },
  created(){
    this.getSession()
  },

  }
  

</script>

<style scoped>

</style>