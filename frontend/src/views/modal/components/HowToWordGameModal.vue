<template>
  <div v-if="wordhowopen" class="modal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">낱말게임 설명</h5>
        <button @click="$emit('update:wordhowopen', !wordhowopen)" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <ul>
          <li>- 낱말에 대한 설명을 보고 정답을 맞추는 게임입니다.</li>
          <li>- 문제는 랜덤하게 출제되며, 문제 난이도 별 1 ~ 5 점까지 획득할 수 있습니다. </li>
          <li>- Player는 15초 제한 시간 내에 정답을 맞춰야 합니다. 제한 시간 내에 맞추지 못한다면 점수를 획득할 수 없습니다.</li>
          <li>- 한 문제가 끝난 후, 문제 정답자와 난이도를 확인할 수 있습니다.</li>
          <li>- 낱말 게임의 고수가 되어보세요!!</li>
        </ul>
      </div>
      <div class="modal-footer">
        <span @click="$emit('update:wordhowopen', !wordhowopen)" type="button" class="btn-animate" data-bs-dismiss="modal">Close</span>        
      </div>
    </div>
  </div>
</div>
</template>

<script>

export default {
  name : 'HowToWordGameModal',
  props :{
    wordhowopen : Boolean,
  },
  data(){
    return {
    
    }
  },
    methods : {
    isWordHowClose() {
      console.log(this.wordhowopen)
      this.$emit('update:wordhowopen', false)
    },

},

}
</script>
<style scoped>
.modal { 
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
  background-color: transparent;


}

.modal-title {
  font-size: 1.2rem;
}

.modal_content {
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
  padding: 10px 0 10px 0;
  /* box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25); */
  /* background-color: transparent; */
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