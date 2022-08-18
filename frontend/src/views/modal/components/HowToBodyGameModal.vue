<template>
  <div v-if="bodyhowopen" class="bodymodal bg-white" tabindex="-1">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">낱말게임 설명</h5>
        <button @click="$emit('update:bodyhowopen', !bodyhowopen)" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <br>
      <div class="modal-body">
        <ul>
          <li>- 문제 수는 3문제 이상, 10 문제 이하이다.</li>
          <li>- 시간제한은 문제당 15초 이상 1분 이하 이다.</li>
          <li>- 난이도는 1에서 5까지 있다.</li>
          <li>- 각 난이도 별로 점수가 다르다. </li>
          <li>- 사용자는 제한 시간 내에 답안을 제출하지 못하면 점수를 획득하지 못한다</li>
          <li>- 시스템에서 설정한 난이도에 맞게 랜덤한 문제를 푼다.</li>
          <li>- 문제는 랜덤한 난이도로 출제된다</li>
          <li>- 사용자는 게임이 시작되면 술래로 랜덤 지정 된다.</li>
          <li>- 술래의 화면에 단어가 표시 된 이후 3초간 카운팅이 되고 문제의 제한시간이 흐르기 시작한다.</li>
          <li>- 술래가 아닌 사용자는 문제의 답안을 계속해서 제출할 수 있다.</li>
          <li>- 사용자는 게임중 점수 집계에 따라, 등수를 확인할 수 있다.</li>
          <li>- 사용자는 게임이 끝나면 게임 참여자들의 점수와 등수를 최종적으로 볼 수 있다.</li>
        </ul>
      </div>
      <div class="modal-footer">
        <span @click="$emit('update:bodyhowopen', !bodyhowopen)" type="button" class="btn-animate" data-bs-dismiss="modal">Close</span>        
      </div>
    </div>
  </div>

</template>

<script>

export default {
  name : 'HowToBodyGameModal',
  props :{
    bodyhowopen : Boolean,
  },
  data(){
    return {
    
    }
  },
    methods : {
    isBodyHowClose() {
      console.log(this.bodyhowopen)
      this.$emit('update:bodyhowopen', false)
    },

},

}
</script>
<style scoped>
.bodymodal { 
  padding: 2%;
  position: absolute;
  display : flex; 
  top: 16%;
  left: 50%;
  width: 550px;
  margin-left: -230px; 
  height : 500px;
  z-index: 90;
  visibility: visible;
  opacity: 100;
  border-radius: 3% 3% 3% 3%;
  border: 0.05rem solid rgb(76,76,76,0.4);
  /* background-color: transparent; */
  
}

.modal-title {
  font-size: 1.2rem;
}

.modal_content {
  padding: 2%;
  width: 50%;
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
  border-radius: 6% 6% 6% 6%;
  /* border: 0.05rem solid rgb(76,76,76); */
  /* padding: 10px 0 10px 0; */
  /* box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25); */
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