<template>
  <div v-if="!this.$store.state.rooms.isSearch" class="bodygame-card-list py-3  grid grid-cols-2 gap-6">
    <div v-for="room in rooms"  :key="room.roomSequence" >
      <BodyMainCardListItem v-if="room.mode.modeSequence===3" :mode="room.mode.modeName" :room="room" />  
    </div>
  </div>

  <div v-if="this.$store.state.rooms.isSearch" class="bodygame-card-list py-3  grid grid-cols-2 gap-6">
    <div v-for="room in this.$store.state.rooms.searchRooms"  :key="room.roomSequence">
      <BodyMainCardListItem v-if="room.mode.modeSequence===3" :mode="room.mode.modeName" :room="room" />  
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
      getSession(page){
        console.log(this.authHeader)
        axios({
          url : `/api/body-game/rooms?page=${page}`,
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