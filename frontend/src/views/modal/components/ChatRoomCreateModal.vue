<template>
  <div v-if="chatcreateopen" class="chatmodal bg-white"  tabindex="-1" >
  <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">대화방 방 생성</h5>
        <button @click="$emit('update:chatcreateopen', !chatcreateopen)" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <br>
      <div class="modal-body">
        <div class="row_box">
          <h6>제목 : </h6>
          <input type="text" v-model="sessionName" placeholder="방 이름을 입력해주세요" class="title-input" />
          <!-- style="height: 5vh" -->
          <!-- <p v-if="!sessionName" style="color:red; font-size:13px; font-style:italic; margin-top:10px;">방 이름을 입력해주세요.</p> -->
        </div>
        <br>
        <div class="row_box">
          최대 정원 :  
          <select name="" id="">
            <option v-for="m in maxUser" :value="m.value" :key="m.value">
              {{ m.text }}
            </option>
          </select>명          
        </div>
        <br>
      </div>
      <div class="modal-footer">
        <span class="mt-3 btn-animate" data-bs-dismiss="modal" type="button" cursor="pointer"  @click="createRoom" >시작</span>
        <!-- <span @click="$emit('update:chatcreateopen', !chatcreateopen)" type="button" class="btn-animate" data-bs-dismiss="modal">Close</span>        -->
      </div>
    </div>
</div>
</template>

<script>
import axios from 'axios';

import { mapGetters } from 'vuex';


axios.defaults.headers.post['Content-Type'] = 'application/json';


export default {
  props :{
    chatcreateopen : Boolean,
  },
  data(){
    return {
    sessionName: '',
    myUserName: '영택임' + Math.floor(Math.random() * 100),
    mode : 'chat',
    maxUser: [
          { text: '2', value: 2 },
          { text: '3', value: 3 },
          { text: '4', value: 4 },
          { text: '5', value: 5 },
          { text: '6', value: 6 },]
        }
    },
    methods : {
    isChatCreateClose() {
      console.log(this.chatcreateopen)
      this.$emit('update:chatcreateopen', false)
    },
  
  
    createRoom(){
    axios(
      { url : `api/conversation/rooms`,
        method : 'post',
        data : {
        title : this.sessionName,
        restrict_num : 6
       },
       headers : this.authHeader}, 
    ).
    then(res => {

      console.log(res)
      this.$router.push({ name: 'ChatDetailView', params: { mode : this.mode, sessionName : this.sessionName, token : res.data.data.token, roomSequence : res.data.data.roomSequence,restrict_num : res.data.data.restrictNum}})
      

    })
    .catch(err => console.log(err,1234))
        
    
  },
},
computed : {...mapGetters(['authHeader','profile'])}
}
</script>
<style scoped>
.chatmodal { 
  position: absolute;
  display : flex; 
  top: 16%;
  left: 50%;
  width: 300px;
  margin-left: -150px; 
  height : auto;
  z-index: 90;
  visibility: visible;
  opacity: 100;
  border: 0.05rem solid rgb(76,76,76,0.4);
  border-radius: 6% 6% 6% 6%;

}

.modal-title {
  font-size: 1.2rem;
}

.modal-content {
  padding: 8%;
}

.roomTitle{
  text-shadow: 5px 5px 70px rgba(190, 209, 212, 0.582);
  font-size: 70px;
  background: linear-gradient(to bottom,#a769d6 ,#6f92d8);
   -webkit-background-clip: text;
   -webkit-text-fill-color: transparent;
   
}

.title-input {
  font-size: 1.3rem;
}

.modal_content {
  width: 38%;
  height:58vh;
  border-radius: 15px;
  background: rgba(58,40,106,1);
  position: relative;
  margin: 0 auto;
  margin-top: 40px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content:space-around;
  padding: 10px 0 10px 0;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
.row_box {
  width: 26vw;
  height: 60px;
  /* display: flex; */
  flex-direction: row;
  align-items: center;
}

.modal-footer {
  display: flex;
  justify-content: center;
}

.btn-animate {
  text-decoration: none;
  color: #fff;
  font-size: 14px;
  font-weight: 600;
  letter-spacing: 2px;
  cursor: pointer;
  position: relative;
  z-index: 1;
  padding: 15px 30px;
  border: none;
  border-radius: 4px;
  box-shadow: 0px 16px 47px -15px #cda6ee;
  background: none;
  transition: all 0.2s cubic-bezier(0.19, 1, 0.22, 1);
  border-radius: 8px;
  overflow: hidden;
  outline: none;
  transform: translateZ(0);
}
.btn-animate span {
  position: relative;
  z-index: 2;
}
.btn-animate:before, .btn-animate:after {
  border-radius: 8px;
  content: "";
  z-index: -1;
  background: linear-gradient(100deg, #a6c1ee, #cc96eb);
  
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
.btn-animate:after {
  
  background: linear-gradient(100deg, #560a9b, #9e158f);
  transform: scaleY(0);
  transform-origin: top;
  transition: transform 0.5s cubic-bezier(0.19, 1, 0.22, 1);
  transition-delay: 0.1s;
}
.btn-animate:hover {
  box-shadow: 0px 16px 47px -15px  #cda6ee;
}
.btn-animate:hover:after {
  transform: scaleY(1);
  transform-origin: bottom;
  transition-delay: 0s;
}
.btn-animate:active {
  transform: translateY(4px) translateZ(0);
  box-shadow: 0px 8px 10px -6px  #cda6ee;
}

</style>