<template>
<div v-if="open" class="modal"  tabindex="-1" >
  <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">대화방 설정</h5>
        <!-- x 버튼-->
        <button @click="$emit('update:open', !open)" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <br>
      <div class="modal-body">
        <div class="row_box">
          <h6>방 이름 : </h6>
          <input type="text" class="title-input" v-model="contents.roomTitle" placeholder="방 이름을 입력해주세요" />
          <p v-if="!contents.roomTitle" style="color:red; font-size:13px; font-style:italic; margin-top:10px;">방 이름을 입력해주세요.</p>
        </div>
        <br>
        <div class="row_box">
          최대 정원 :  
          <select name="" id="">
            <option v-for="m in contents.maxUser" :value="m.value" :key="m.value">
              {{ m.text }}
            </option>
          </select>명
        </div>
        <br>

      <div class="modal-footer flex">
        <button class="mt-3" v-if="contents.roomTitle" @click="updateRoom">
        <!-- @click 추후수정 필요 -->
        <span class='btn-animate'>완료</span>
        </button>
        <!-- <button @click="$emit('update:open', !open)" type="button" class="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-3 rounded" data-bs-dismiss="modal">Close</button> -->
      </div>
    </div>
  </div>
</div>
</template>

<script>
import axios from "axios"
import { mapGetters } from 'vuex';
const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'ChatRoomUpdateModal',
  data () {
    return {
      maxUsercnt : 0,
      contents: {
        roomTitle: '',
        maxUser: [
          { text: '2', value: '2' },
          { text: '3', value: '3' },
          { text: '4', value: '4' },
          { text: '5', value: '5' },
          { text: '6', value: '6' },
        ],
      }
    }
  },
  props :{
    open : {
      type : Boolean,
      require : true,
      default : false,
      }
  },
  computed : {
    ...mapGetters(['authHeader'])},
  methods : {
    isClose() {
      console.log(this.open)
      this.$emit('update:open', false)
    },
    updateRoom(){
    axios(
      { url : `/api/conversations/rooms/${this.$route.params.roomSequence}`,
        method : 'put',
        data : {
        title : this.sessionName,
        restrict_num : this.maxUsercnt,
       },
       headers: {Authorization : this.$store.getters.authHeader.Authorization}
       })
    .then(res => {
      alert('방설정 변경완료!')
      this.isClose()

    })
     .catch(err => {
          console.log('에러발생!')
          if (err.response.data.status === 'sucess'){
            console.log('성공!!!!!!')
          }
          else{
            console.log('실패!!!!!!!')
            axios({
            url : `/api/conversations/rooms/${this.$route.params.roomSequence}`,
            method : 'put',
            data : {
            title : this.sessionName,
            restrict_num : this.maxUsercnt,
          },
              headers: this.$store.getters.authHeader,
              
            }).then(res =>{
               alert('방설정 변경완료!')
               this.isClose()

              const accessToken = res.headers.authorization
              this.$store.actions.member.reissuanceToken(accessToken)
            }).catch(err => {
              console.log(err.response.data)
            })
          }

        })
        
    
  },

    
  }
}
</script>


<style scoped>
.modal { 
  position: absolute;
  display : flex; 
  top: 8%;
  left: 50%;
  width: 300px;
  margin-left: -150px; 
  height : auto;
  z-index: 90;
  visibility: visible;
  opacity: 100;
  /* border: 0.05rem solid rgb(76,76,76,0.4);
  border-radius: 6% 6% 6% 6%; */
  background-color: transparent;
}

.modal-title {
  font-size: 1.2rem;
}

.title-input {
  font-size: 1.3rem;
  border: 0.1rem solid grey;
}

.roomTitle{
  text-shadow: 5px 5px 70px rgba(190, 209, 212, 0.582);
  font-size: 70px;
  background: linear-gradient(to bottom,#a769d6 ,#6f92d8);
   -webkit-background-clip: text;
   -webkit-text-fill-color: transparent;
   
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
  /* padding: 10px 0 10px 0;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25); */
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
