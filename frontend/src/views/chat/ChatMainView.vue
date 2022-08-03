<template>
  
  <div class="container">
    <h1 class="text-2xl d-flex justify-content-space-around py-2 px-4 mt-8">대화방</h1>
    <button class="bg-indigo-600  text-white font-bold py-2 px-4 rounded" @click="isChatCreateOpen" style="cursor: pointer">방 생성하기</button>
    
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
    ChatRoomCreateModal,
    ChatMainCardList
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
  flex-direction : column
}
.card-container{
  display : flex;
  justify-content: center;
}

</style>