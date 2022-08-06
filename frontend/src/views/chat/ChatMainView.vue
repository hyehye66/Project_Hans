<template>
  
  <div class="container">
    <p id="chat-main-title" class="d-flex justify-content-space-around py-2 px-4 mt-8">대화방</p>
    <div class="btn-container py-4 px-4">
      <button id="chat-main-create" class="bg-indigo-600  text-white font-bold py-2 px-4 rounded" @click="isChatCreateOpen" style="cursor: pointer">방 생성하기</button>
    </div>
    <div class="card-container">
    <ChatMainCardList /></div>
  </div>
  <ChatRoomCreateModal v-model:chatcreateopen="chatcreateopen"/>
  

</template>

<script>
import ChatMainCardList from './components/ChatMainCardList.vue'
import ChatRoomCreateModal from '../modal/components/ChatRoomCreateModal.vue'

export default {
  name : 'ChatMainView',
  components : {
    ChatMainCardList,
    ChatRoomCreateModal
  },
  data (){
    return {
      chatcreateopen : false
    }
  },
  methods: {
    isChatCreateOpen (){
      
      return this.chatcreateopen = !this.chatcreateopen
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