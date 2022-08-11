<template>
  <div class="wordgame-card-list py-3  grid grid-cols-3 gap-6">
    <div v-for="room in rooms"  :key="room.roomSequence">
      <BodyMainCardListItem v-if="room.mode.modeSequence===3" :mode="room.mode.modeName" :room="room" />  
    </div>
  </div>
  <div>
    <input v-model="idx" class="bg-primary" v-on:keyup.enter="getSession(changeIdx)">
    {{changeIdx}}
  </div>
</template>

<script>
import axios from 'axios';
import BodyMainCardListItem from './BodyMainCardListItem.vue';
import { mapGetters } from 'vuex';



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
    BodyMainCardListItem
  },
  methods : {
    // 모든 세션 데이터 받아오는 함수 
      getSession(){
        console.log(this.authHeader)
        axios({
          url : '/api/body-game/rooms',
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
  },
  computed:{
    // 페이지네이션에 사용할 인덱스 변경 함수
    changeIdx() {
      return this.idx
    },
    ...mapGetters(['authHeader'])
  }}
  

</script>

<style scoped>
.wordgame-card-list{
  display : flex;
  flex-direction: row ; 
  width : 1200px;
  height : auto;
  flex-wrap: wrap;

}
</style>