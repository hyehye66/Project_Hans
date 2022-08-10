<template>
  <div class="chat-card-list py-3  grid grid-cols-3 gap-6">
    <div v-for="room in rooms"  :key="room.roomSequence">
      <ChatMainCardListItem v-if="room.mode.modeSequence===1" :mode="room.mode.modeName" :room="room" />  
    </div>
  </div>
  <div>
    <input v-model="idx" class="bg-primary" v-on:keyup.enter="getSession(changeIdx)">
    {{changeIdx}}
  </div>
</template>

<script>
import axios from 'axios';
import ChatMainCardListItem from './ChatMainCardListItem.vue'
import { mapGetters } from 'vuex';

// const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
// const OPENVIDU_SERVER_SECRET = "MY_SECRET";


export default {
  data () {
    return {
      rooms : '',
      temp : '',
      mode : '',
      totalRoomPage : {},
      getRoom : true,
      idx : 0
    }
  },
  components : {
    ChatMainCardListItem
  },
  methods : {
    // 모든 세션 데이터 받아오는 함수 
      getSession(){
        axios({
          url : '/api/conversation/rooms',
          method : 'get',
          headers : {'Authorization':this.authHeader.Authorization}
          }
          
        )
            .then(res =>{this.rooms = res.data.data.listRooms.content})
            .catch(err => {
            console.log(err.status)
            console.log(err,'error here')})
      }
            
  

  },
  created(){
    this.getSession()
    console.log(this.authHeader.Authorization)
  },
  computed:{
    changeIdx() {
      return this.idx
    },
    ...mapGetters(['authHeader'])
  }}
  

</script>

<style scoped>
.chat-card-list{
  display : flex;
  flex-direction: row ; 
  width : 1200px;
  height : auto;
  flex-wrap: wrap;

}
</style>