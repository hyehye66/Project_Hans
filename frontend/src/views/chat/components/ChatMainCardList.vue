<template>
  <div v-if="!this.$store.state.rooms.isSearch" class="chat-card-list py-3  grid grid-cols-2 gap-6">
    <div v-for="room in rooms"  :key="room.roomSequence" >
      <ChatMainCardListItem v-if="room.mode.modeSequence===1" :mode="room.mode.modeName" :room="room" :max_user="room.restrictNum" :current_user="room.currentNum" />  
    </div>
  </div>
  <div v-if="this.$store.state.rooms.isSearch" class="chat-card-list py-3  grid grid-cols-2 gap-6">
    <div v-for="room in this.$store.state.rooms.searchRooms"  :key="room.roomSequence">
      <ChatMainCardListItem v-if="room.mode.modeSequence===1" :mode="room.mode.modeName" :room="room" :max_user="room.restrictNum" :current_user="room.currentNum"/>  
    </div>
  </div>
  <nav aria-label="Page navigation">
                <ul class="pagination">
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous" @click="getSession(0), this.page=0">
                      <span aria-hidden="true">&laquo;</span>
                    </a>
                  </li>
                  <li class="page-item"><a class="page-link" @click="getSession(0), this.page=0" href="#">1</a></li>
                  <li class="page-item"><a class="page-link" @click="getSession(1), this.page=1" href="#">2</a></li>
                  <li class="page-item"><a class="page-link" @click="getSession(2), this.page=2" href="#">3</a></li>
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next" @click="getSession(2), this.page=2">
                      <span aria-hidden="true">&raquo;</span>
                    </a>
                  </li>
                </ul>
              </nav>
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
      getSession(page){
        axios({
          url : `/api/conversation/rooms?page=${page}`,
          method : 'get',
          headers : {'Authorization':this.authHeader.Authorization}
          }
          
        )
            .then(res =>{this.rooms = res.data.data.listRooms.content,console.log(this.rooms)})
            .catch(err => {
            console.log(err.status)
            console.log(err,'error here')})
      }
            
  

  },
  created(){
    this.getSession()
    this.$store.state.rooms.isSearch = false
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
  flex-flow: row wrap; 
  width : 100%;
  height : auto;

}
</style>