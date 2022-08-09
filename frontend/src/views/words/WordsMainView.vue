<template>
  <div class="container">
    <p id="chat-main-title" class="d-flex justify-content-space-around py-2 px-4 mt-8">낱말퀴즈</p>
    <div class="btn-container py-4 px-4">
      <button id="chat-main-create" class="bg-indigo-600  text-white font-bold py-2 px-4 rounded" @click="isWordCreateOpen" style="cursor: pointer">방 생성하기</button>
     <div>
  <div class="mb-3 xl:w-96">
    <div class="input-group relative flex flex-wrap items-stretch w-full mb-4">
      <input type="search" class="form-control relative min-w-0 block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" placeholder="Search" aria-label="Search" aria-describedby="button-addon2">
      <button class="btn inline-block px-6 py-2.5 bg-blue-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700  focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out flex items-center" type="button" id="button-addon2">
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
</template>

<script>
import WordsMainCardList from './components/WordsMainCardList.vue'
import WordsRoomCreateModal from '../modal/components/WordsRoomCreateModal.vue'

export default {
  name : 'ChatMainView',
  components : {
    WordsMainCardList,
    WordsRoomCreateModal
  },
  data (){
    return {
      wordcreateopen : false
    }
  },
  methods: {
    isWordCreateOpen (){
      return this.wordcreateopen = !this.wordcreateopen
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

#chat-main-title {
  font-size: 3rem;
  line-height: 1;
}

#chat-main-create {
  width : 200px;
  height : 50px;
  padding-top : 2rem
}
</style>
