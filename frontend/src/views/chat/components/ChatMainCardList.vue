<template>
  <div v-if="!this.$store.state.rooms.isSearch" class="chat-card-list py-3  grid grid-cols-2 gap-6">
    <div v-for="room in this.$store.state.rooms.rooms"  :key="room.roomSequence" >
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
                    <a class="page-link" href="#" aria-label="Previous" @click="chkRange(this.$store.state.rooms.currentPage-1)">
                      <span aria-hidden="true">&laquo;</span>
                    </a>
                  </li>
                  <ul v-for="idx in this.$store.state.rooms.range" :key="idx">
                    <li class="page-item"><a class="page-link" @click="getSession({page : idx, mode : 'conversation'})" href="#">{{idx+1}}</a></li>
                  </ul>
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next" @click="chkRange(this.$store.state.rooms.currentPage+1)">
                      <span aria-hidden="true">&raquo;</span>
                    </a>
                  </li>
                </ul>
              </nav>
</template>

<script>
import ChatMainCardListItem from './ChatMainCardListItem.vue'
import { mapActions } from 'vuex';


export default {
  data () {
    return {
    }
  },
  components : {
    ChatMainCardListItem
  },
  methods : {
    ...mapActions(['chkRange', 'getSession']),
           
 

  },
  created(){
    this.getSession({page: 0, mode : 'conversation'})
    this.$store.state.rooms.isSearch = false
  },
}
  

</script>

<style scoped>
.chat-card-list{
  display : flex;
  flex-flow: row wrap; 
  width : 100%;
  height : auto;

}
.pagination {
  margin-top: 4%;
  display:flex;
  justify-content: center;
  align-items: center;
  align-self: center;
  
}
</style>