<template>
   <div class="bg-white p-8 rounded-lg shadow-lg relative hover:shadow-2xl transition duration-500 chat-card">
      <h1 class="text-2xl text-gray-800 font-semibold mb-3">{{ room.title }}</h1>
      <p class="text-gray-600 leading-6 tracking-normal">방장 : {{ room.member.nickname }}</p>
    <p class="text-gray-600 leading-6 tracking-normal">참여인원 : {{ room.currentNum }}/{{room.restrictNum}}</p>
      <button class="py-2 px-4 mt-8 bg-indigo-600 text-white rounded-md shadow-xl" @click="joinChatRoom" >입장하기</button>
      <div>
        <span class="absolute py-2 px-8 text-sm text-white top-0 right-0 bg-indigo-600 rounded-md transform translate-x-2 -translate-y-3 shadow-xl">{{ mode }}</span>
      </div>
    </div>
</template>

<script>
import axios from 'axios';
import { mapGetters } from 'vuex';
// <router-link :to="{ name: 'ChatDetailView', params: { mode : mode, sessionName : room.title, isChatRoomCreate : 'false'}}" :sessionName="room.sessionId" :isChatRoomCreate="false">입장하기</router-link>

axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_SECRET = 'hans'
export default {
    props : {
        room : Object,
        mode : String
    },
    created(){
        
    },
  data () {
        return {
            token : ''
        }
    },
  methods: {
    
    joinChatRoom(){
        axios({
            url : `/api/word-game/rooms/`+this.room.roomSequence,
            method : 'post',
            headers : this.authHeader})
        .then(res => {console.log(res),
        this.$router.push({ name: 'WordsDetailView', params: { mode : this.mode, sessionName : this.room.title, token : res.data.data.token,roomSequence : this.room.roomSequence}})})
        .catch(err => console.log(err,123 ))
    }
    },
    computed:{
        ...mapGetters(['authHeader'])
    }
    }
    
       
    


</script>

<style scoped>
.chat-card {
    width : 300px;
}
</style>