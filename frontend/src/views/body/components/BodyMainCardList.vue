<template>
  <div class="bodygame-card-list py-3  grid grid-cols-3 gap-6">
    <div v-for="room in rooms"  :key="room.roomSequence">
      <BodyMainCardListItem v-if="room.mode.modeSequence===3" :mode="room.mode.modeName" :room="room" />  
    </div>
  </div>
  <div>
    <input v-model="idx" class="bg-primary" v-on:keyup.enter="getSession(changeIdx)">
    {{changeIdx}}
  </div>

  <!-- <div class="btn-cover">
    <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
      이전
    </button>
    <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
    <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
      다음
    </button>
  </div> -->




  <!-- bootstrap 페이지네이션 틀 -->
  <!-- <nav aria-label="Page navigation example">
    <ul class="pagination">
      <li class="page-item"><a class="page-link" href="#">Previous</a></li>
      <li class="page-item"><a class="page-link" href="#">1</a></li>
      <li class="page-item"><a class="page-link" href="#">2</a></li>
      <li class="page-item"><a class="page-link" href="#">3</a></li>
      <li class="page-item"><a class="page-link" href="#">Next</a></li>
    </ul>
  </nav> -->

  <!-- daisyUI 페이지네이션 틀 -->
  <!-- <div class="btn-group">
    <button class="btn">«</button>
    <button class="btn">1</button>
    <button class="btn">2</button>
    <button class="btn">3</button>
    <button class="btn">»</button>
  </div> -->

  

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
    BodyMainCardListItem,

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
            .then(res =>{this.rooms = res.data.data.listRooms.content,console.log(this.rooms)})
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
.bodygame-card-list{
  display : flex;
  flex-flow: row wrap; 
  width : 100%;
  height : auto;

}
</style>