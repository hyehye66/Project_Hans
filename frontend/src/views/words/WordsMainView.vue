<template>
<nav-bar />
  <div class="container">
    <p id="chat-main-title" class="d-flex justify-content-space-around py-2 px-4 mt-8">낱말퀴즈</p>
    <div class="btn-container py-4 px-4">
      <button id="word-main-create" class=" text-white font-bold py-2 px-4 rounded" @click="isWordCreateOpen" style="cursor: pointer">방 생성하기</button>
      <button id="word-random-join" class=" text-white font-bold py-2 px-4 rounded" @click="isWordRandomClick" style="cursor: pointer">빠른 입장</button>
      <svg @click="isHowtowordOpen" xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-question-circle" viewBox="0 0 16 16" style="cursor: pointer">
        <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
        <path d="M5.255 5.786a.237.237 0 0 0 .241.247h.825c.138 0 .248-.113.266-.25.09-.656.54-1.134 1.342-1.134.686 0 1.314.343 1.314 1.168 0 .635-.374.927-.965 1.371-.673.489-1.206 1.06-1.168 1.987l.003.217a.25.25 0 0 0 .25.246h.811a.25.25 0 0 0 .25-.25v-.105c0-.718.273-.927 1.01-1.486.609-.463 1.244-.977 1.244-2.056 0-1.511-1.276-2.241-2.673-2.241-1.267 0-2.655.59-2.75 2.286zm1.557 5.763c0 .533.425.927 1.01.927.609 0 1.028-.394 1.028-.927 0-.552-.42-.94-1.029-.94-.584 0-1.009.388-1.009.94z"/>
      </svg>
     <div>
  <div class="mb-3 xl:w-96">
    <div class="input-group relative flex flex-wrap items-stretch w-full mb-4">
      <select v-model="searchSelected" class="form-select" aria-label="Default select example">
        <option :value="'title'">제목</option>
        <option :value="'nickname'">방장</option>
      </select>

      <input type="search" class="form-control relative min-w-0 block w-full px-3 py-1.5 text-base font-normal text-gray-700 
      bg-white bg-clip-padding border border-solid border-gray-300 
      rounded transition ease-in-out m-0 focus:text-gray-700 
      focus:bg-white focus:border-blue-600 focus:outline-none" 
      placeholder="Search" aria-label="Search" 
      aria-describedby="button-addon2"
      v-model="input"
      @keyup.enter="searchWordRoom({input : input, searchSelected : searchSelected})">
      <button class="btn inline-block px-6 py-2.5 search-btn text-white font-medium 
      text-xs leading-tight uppercase rounded 
      shadow-md hover:bg-blue-700 hover:shadow-lg 
      focus:bg-blue-700  focus:shadow-lg focus:outline-none 
      focus:ring-0 active:bg-blue-800 active:shadow-lg transition 
      duration-150 ease-in-out flex items-center" type="button" id="button-addon2"
      @click="searchWordRoom({input : input, searchSelected : searchSelected})">
        <svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="search" class="w-4" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
          <path fill="currentColor" d="M505 442.7L405.3 343c-4.5-4.5-10.6-7-17-7H372c27.6-35.3 44-79.7 44-128C416 93.1 322.9 0 208 0S0 93.1 0 208s93.1 208 208 208c48.3 0 92.7-16.4 128-44v16.3c0 6.4 2.5 12.5 7 17l99.7 99.7c9.4 9.4 24.6 9.4 33.9 0l28.3-28.3c9.4-9.4 9.4-24.6.1-34zM208 336c-70.7 0-128-57.2-128-128 0-70.7 57.2-128 128-128 70.7 0 128 57.2 128 128 0 70.7-57.2 128-128 128z"></path>
        </svg>
      </button>
    </div>
  </div>
</div>
</div> 

    <WordsMainCardList />

  <WordsRoomCreateModal v-model:wordcreateopen="wordcreateopen"/>
    
</div> 
<div>
  <HowToWordGameModal v-model:wordhowopen="wordhowopen"/>
</div>
</template>

<script>
import WordsMainCardList from './components/WordsMainCardList.vue'
import WordsRoomCreateModal from '../modal/components/WordsRoomCreateModal.vue'
import HowToWordGameModal from '../modal/components/HowToWordGameModal.vue'
import NavBar from "@/components/NavBar.vue";
import { mapGetters, mapActions } from 'vuex';
import axios from 'axios';

export default {
  name : 'ChatMainView',
  components : {
    WordsMainCardList,
    WordsRoomCreateModal,
    HowToWordGameModal,
    NavBar,
  },
  data (){
    return {
      wordcreateopen : false,
      wordhowopen : false,
      title : '',
      searchSelected: '',
    }
  },
    computed : {
    ...mapGetters(['isLoggedIn', 'authHeader'])
  },
  methods: {
    ...mapActions(['searchWordRoom']),
    isWordCreateOpen (){
      if (this.isLoggedIn){
        return this.wordcreateopen = !this.wordcreateopen
        }
      else{
        alert('로그인이 필요합니다!')
        this.$router.push({ name: 'LoginView'})
      }
    },
    isWordRandomClick (){
      if (!this.isLoggedIn){
        alert('로그인이 필요합니다!')
        this.$router.push({ name: 'LoginView'})
      }
      else{
        axios({
            url : `/api/word-game/rooms/random`,
            method : 'post',
            headers : this.authHeader})
        .then(res => {
          console.log(res.data.data)
        this.$router.push({ name: 'WordsDetailView', 
        params: { mode : res.data.data.room.mode.modeSequence, 
        sessionName : res.data.data.room.title, token : res.data.data.token, 
        roomSequence : res.data.data.room.roomSequence,
        problemIdx : res.data.data.room.totalQuestion}})})

        .catch(err => alert('입장 가능한 방이 없습니다!'))
      }
    },
    isHowtowordOpen(){
      return this.wordhowopen = !this.wordhowopen
    },
    created(){
      this.$store.state.words.isSearch = false
    }
  }
}
</script>

<style scoped>
.container {
  display : flex;
  width : 1200px;
  height : 1000px;
  flex-direction : column;
}

.btn-container {
  display : flex;
  padding-top : 1rem;
  width : 100%;
  justify-content: space-between;
}


.card-container{
  display : flex;
  justify-content: center;
}

.search-btn{
  background-color: #f38e7b;
  border: transparent;
}

#chat-main-title {
  font-size: 3rem;
  line-height: 1;
}

#word-main-create {
  width : 200px;
  height : 50px;
  padding-top : 2rem;
  background-color: #f38e7b;
}

#word-random-join {
  width : 200px;
  height : 50px;
  padding-top : 2rem;
  background-color: #f38e7b;
}


</style>
