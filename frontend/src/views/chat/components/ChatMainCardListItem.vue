<template>
   <div class="bg-white p-8 rounded-lg shadow-lg relative hover:shadow-2xl transition duration-500 chat-card">
      <h1 class="text-2xl text-gray-800 font-semibold mb-3">{{ room.title }}</h1>
      <p class="text-gray-600 leading-6 tracking-normal">방장 : {{ room.member.nickname }}</p>
    <p class="text-gray-600 leading-6 tracking-normal">참여인원 : {{ room.currentNum }}/{{room.restrictNum}}</p>
      <button class="py-2 px-4 mt-8 bg-indigo-600 text-white rounded-md shadow-xl join-button" @click="joinChatRoom" >입장하기</button>
      <button class="py-2 px-4 mt-8 bg-indigo-600 text-white rounded-md shadow-xl join-button" @click="cantJoin" v-if="room.currentNum==room.restrictNum" >입장불가</button>
      <div>
        <span class="absolute py-2 px-8 text-sm text-white top-0 right-0 bg-indigo-600 rounded-md transform translate-x-2 -translate-y-3 shadow-xl">{{ mode }}</span>
      </div>
    </div>
</template>

<script>
import axios from 'axios';
import { mapGetters } from 'vuex';


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
    
    joinChatRoom(){
      if(this.isLoggedIn){
        axios({
            url : `/api/conversation/rooms/`+this.room.roomSequence,
            method : 'post',
            headers: {Authorization : this.$store.getters.authHeader.Authorization}})
        .then(res => {
        this.$router.push({ name: 'ChatDetailView', params: { mode : this.mode, sessionName : this.room.title, token : res.data.data.token,roomSequence : this.room.roomSequence,restrict_num : res.data.data.restrictNum}})})
        .catch(err => {
          if (err.response.data.status === 'sucess'){
            console.log('성공!!!!!!')
          }
          else{
            console.log('실패!!!!!!!')
            axios({
              url : `/api/conversation/rooms/`+this.room.roomSequence,
              method : 'post',
              headers: this.$store.getters.authHeader,
              
            }).then(res =>{
             this.$router.push({ name: 'ChatDetailView', params: { mode : this.mode, sessionName : this.room.title, token : res.data.data.token,roomSequence : this.room.roomSequence,restrict_num : res.data.data.restrictNum}})

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
    cantJoin(){
        if (this.room.currentNum === this.room.restrictNum){
          alert('정원이 가득 찼습니다.')
        } 
      }
  },
    computed:{
        ...mapGetters(['authHeader', 'isLoggedIn'])
    }
    }
    
       
    


</script>

<style scoped>
.chat-card {
    width : 275px;
}
</style>