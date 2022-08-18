<template>
   <div class="bg-white p-8 rounded-lg shadow-lg relative hover:shadow-2xl transition duration-500 word-card">
      <h1 class="text-2xl text-gray-800 font-semibold mb-3">{{ room.title }}</h1>
      <p class="text-gray-600 leading-6 tracking-normal">방장 : {{ room.member.nickname }}</p>
    <p class="text-gray-600 leading-6 tracking-normal">참여인원 : {{ room.currentNum }}/{{room.restrictNum}}</p>
      
      <button class="py-2 px-4 mt-8 text-white rounded-md shadow-xl nojoin-button" @click="cantJoin" v-if="room.roomStatus || room.currentNum == room.restrictNum">입장불가</button>
      <button class="py-2 px-4 mt-8 text-white rounded-md shadow-xl join-button" @click="joinWordRoom" v-else>입장하기</button>
      <div>
        <span class="absolute py-2 px-8 text-sm text-white top-0 right-0 rounded-md transform translate-x-2 -translate-y-3 shadow-xl mode-name">{{ mode }}</span>
      </div>
    </div>
</template>

<script>
import axios from 'axios';
import { mapGetters } from 'vuex';
// <router-link :to="{ name: 'ChatDetailView', params: { mode : mode, sessionName : room.title, isChatRoomCreate : 'false'}}" :sessionName="room.sessionId" :isChatRoomCreate="false">입장하기</router-link>

axios.defaults.headers.post['Content-Type'] = 'application/json';


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
    cantJoin(){
        if (this.room.currentNum === this.room.restrictNum){
          alert('정원이 가득 찼습니다.')
        } else {
          alert('게임이 시작되었습니다.')
        }
      },
    joinWordRoom(){
      if (this.isLoggedIn){
          axios({
            url : `/api/word-game/rooms/`+this.room.roomSequence,
            method : 'post',
            headers: {Authorization : this.$store.getters.authHeader.Authorization}})
        .then(res => {console.log(res.data.data),
        this.$router.push({ name: 'WordsDetailView', 
        params: { mode : this.mode, sessionName : this.room.title, 
        token : res.data.data.token,roomSequence : this.room.roomSequence, 
        problemIdx : this.room.totalQuestion, host : this.room.member}})})
         .catch(err => {
          console.log('에러발생!')
          if (err.response.data.status === 'sucess'){
            console.log('성공!!!!!!')
          }
          else{
            console.log('실패!!!!!!!')
            axios({
              url : `/api/word-game/rooms/`+this.room.roomSequence,
              method : 'post',
              headers: this.$store.getters.authHeader,
              
            }).then(res =>{
             this.$router.push({ name: 'WordsDetailView', 
        params: { mode : this.mode, sessionName : this.room.title, 
        token : res.data.data.token,roomSequence : this.room.roomSequence, 
        problemIdx : this.room.totalQuestion, host : this.room.member}})

              const accessToken = res.headers.authorization
              this.$store.actions.member.reissuanceToken(accessToken)
            }).catch(err => {
              console.log(err.response.data)
            })
          }

        })
        }
      else{
        alert('로그인이 필요합니다!')
        this.$router.push({ name: 'LoginView'})
      }
        
      },
    },
    computed:{
        ...mapGetters(['authHeader','isLoggedIn'])
    }
    }
    
       
    


</script>

<style scoped>
.word-card {
    width : 275px;
}

.join-button {
  background-color: #f38e7b;
}
.nojoin-button {
  background-color : gray;
}
.mode-name {
  background-color: #f38e7b;
}
</style>