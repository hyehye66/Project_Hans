<template>
    <NavBar />
       
    <div class="container">
    
      <!-- <div id="dictation-bg"> -->
        <div class="box relative flex flex-col min-w-0 break-words w-full  shadow-lg bg-white rounded"> 
           <svg style="cursor: pointer" @click="this.$router.go()" id="dictation-Leavebutton"  fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 15l-3-3m0 0l3-3m-3 3h8M3 12a9 9 0 1118 0 9 9 0 01-18 0z"/>
          
        </svg> 
      <div class="px-4 py-5 flex-auto" style="width: 100%;">
        <div class="content tab-space" >
          <h1>받아쓰기</h1>
          <br>
          

        <div>
        
          
          </div>

        <div id="selectboxs" class="mb-3" style="font-size:30px;"  v-if="!start">
          <div  class="row_box my-3" >
          난이도 : <select v-model="difficulty" name="" id="selectbox" class="pl">
            <option  v-for="lev in contents.level" :value="lev.value" :key="lev.value">
              {{ lev.text }}
            </option>
          </select>
        </div>
          <div class="row_box my-3">
          재생속도 : <select v-model="speed" name="" id="selectbox" class="pl">
            <option v-for="sp in contents.speeds" :value="sp.value" :key="sp.value">
              {{ sp.text }}
            </option>
          </select>
        </div>
          </div>

 
          <div  class="mb-3" v-if="!submitAnswer && start && !isFinal">
          <h2 class="my-3">내가 입력한 답 : {{input}}</h2>
            <br>
            <br>
            <br>
            <div id="answer-input-box" class="row_box my-3">
            <input type="text" name="" id="dictaion-answer-sheet" 
            placeholder="답을 입력해주세요." v-model="input" @keyup.enter="submitProblem">
            <button style="height:48px;" class=" bg-newBlue1 hover:bg-newBlue2 text-white font-bold px-4 rounded" @click="submitProblem">
            제출</button>
            </div>
      </div>
      <br>
      <br>
      <br>
      <br>
      
         <div class="start-btn">
            <button class="btn-lg bg-newBlue1 hover:bg-newBlue2 text-white font-bold py-2 px-4 rounded"
            style="width:200px;"
             v-if="!start" @click="threecountDown">
            Start</button>
            <h1 v-if="cnt">{{this.threecount}}</h1>
          <div v-if="isFinal">
        <h2 style="text-align: center">{{this.answercnt}} / 2 </h2> 
        <h2 text-align: center> 고생하셨습니다!! </h2>
        <br>
          <div style="display: flex; justify-content: center">
            <button class="bg-newBlue1 hover:bg-newBlue2 text-white font-bold py-2 px-4 rounded"  v-if="isFinal" @click="this.$router.go()">다시하기</button>
          </div>
        </div>
        <div class="mb-3 c" v-if="submitAnswer">
        <h1 class="my-3" style="color:red; text-align: center">{{answer}}</h1>
        <h2 class="my-3">내가 입력한 답: {{input}}</h2>
        <h2 class="my-3">정답 : {{this.finalqustions[idx]}}</h2>
        <br>
        <br>
        <br>
        <div style="margin-left:70px;">
        <button  class="bg-newBlue1 hover:bg-newBlue2 text-white font-bold py-2 px-4 rounded"  @click="repeat(this.finalqustions[idx])"> 다시듣기</button>
        <button class="mx-3 bg-newBlue1 hover:bg-newBlue2 text-white font-bold py-2 px-4 rounded" v-if="start && !isFinal" @click="nextProblem">NEXT</button>
          </div>
          </div>
          </div>
        </div>
      </div>
    </div>
  <!-- </div> -->
</div>

</template>

<script>
import axios from 'axios'
import { mapGetters } from 'vuex'
import NavBar from '@/components/NavBar.vue';


export default {
  
  name : 'DictationView',
  data(){
  return{
    idx : 0,
    start: false,
    cnt : false,
    submitAnswer : false,
    isFinal : false,
    threecount : 3,
    difficulty : 1,
    speed : 1,
    answercnt : 0,
    answer : '',
    input : '',
    questions: [],
    question : {},
    finalqustions : [],
    contents : {
       level: [
          { text: '1', value: 1 },
          { text: '2', value: 2 },
          { text: '3', value: 3 },
          { text: '4', value: 4 },
          { text: '5', value: 5 },        
        ],
        speeds: [
          { text: '0.5배속', value: 0.5 },
          { text: '0.7배속', value: 0.7 },
          { text: '1배속', value: 1 },
          { text: '1.5배속', value: 1.5 },
          { text: '2배속', value: 2 },
          { text: '5배속', value: 5 },        
        ],
    },
  }
},
  computed:{
    ...mapGetters(['authHeader'])
    },
  components: {
    NavBar,
    },
  methods: {
    repeat(sentence){
       let utterance = new SpeechSynthesisUtterance(sentence)
          utterance.rate = 1
          window.speechSynthesis.speak(utterance)
          
    },
    startSpeechToTxt(speed) {
    {
          let utterance = new SpeechSynthesisUtterance(this.finalqustions[this.idx])
          utterance.rate = speed
          window.speechSynthesis.speak(utterance)
          setTimeout(function(){window.speechSynthesis.speak(utterance)},2000)
    }
  },

    getProblem(){
      axios({
        url : `/api/dictations?difficulty=${this.difficulty}`,
        method : 'get',
        headers : {'Authorization' :this.authHeader.Authorization}
      }).then(res=> {
        console.log(res)
        this.questions = res.data.data.dictations
        this.finalqustions = []
        for(this.question of this.questions){
          if(this.question.difficulty==this.difficulty){
            this.finalqustions.push(this.question.sentence)
          }
        }
        
        }).catch(err => console.err(err))
      
    },
    threecountDown(){
    this.cnt = true
    this.start = true
    this.getProblem()
    setTimeout(() => {this.threecount = 3}, 1000)
    setTimeout(() => {this.threecount = 2}, 2000)
    setTimeout(() => {this.threecount = 1}, 3000)
    setTimeout(() => {this.threecount = 'START!'}, 3500)
    setTimeout(() => { this.cnt=false}, 4000)
    setTimeout(() => { this.startSpeechToTxt(this.speed) }, 4000)
    this.threecount = 3
       
  },
  
  submitProblem(){
    this.submitAnswer=true
    if(this.input==this.finalqustions[this.idx]){
      this.answer = '정답!!!!!'
      this.answercnt ++
    }
    else{
      this.answer = '오답!!!!'
    }
  },
 
  nextProblem(){
    this.submitAnswer = false
    this.input = ''
    if(this.idx <1){
      this.idx ++
      setTimeout(() => { this.startSpeechToTxt(this.speed) }, 2500)
    }
    else{
      this.finalResult()
    }
  },

  finalResult(){
    this.isFinal = true
  }
  }
}
</script>


<style scoped>
#answer-input-box{
  display : flex;
  justify-content: left;
}
#dictation-Leavebutton{
  width: 10%;
}
#selectbox{
  border-width : 2px;
  justify-content: center;
  align-self: center;
  align-items: center;
}
#dictation-bg{
  margin: auto;
  width: 100vw;
  height: 100vh;
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;
  opacity: 0.6;
  content: "";
  background-image : url("@/assets/1.png");
  background-size: cover;
}
/* #dictation-main-title {
  font-size: 3rem;
  line-height: 1;
}
*/
#dictaion-answer-sheet {
    border-radius: 8px;
    border-width : 2px;
    border-color : black;
    font-size: 2rem;
    width: 60%;
}
.start-btn{
  display: flex;
  justify-content: center;
  margin-right : 100px;
}


#selectboxs{
  margin-right: 10%;
  margin-top: 10%;
}
/*
#dictation-main {
  border-width: 3px;
  padding-left: 45%;  
  padding-top: 30%;  
  display: flex;
  flex-direction: column;

  justify-content: center;
  align-self: center;
  align-items: center;
  background-color: transparent;
} */
.row_box{
  justify-content: center;
  align-self: center;
  align-items: center;
}
h1 {
  font-size: 3em;
}
h2 {
  font-size: 2em;
}
.container {
  display: flex;
  justify-content: center;
  margin-top: 2%;
   
}

.box {
  width: 1200px;
  height: 600px;
  display: flex;
  justify-content: center;
  background-image : url("@/assets/dictation-bg.gif");
  background-size: 100% 100%;
}

.text-box {
  float: right;
  flex-flow: column wrap;
}

/* .pl{
    width: 200px;
    border: 1px solid #C4C4C4;
    box-sizing: border-box;
    border-radius: 10px;
    padding: 12px 13px;
    font-family: 'Roboto';
    font-weight: 400;
    line-height: 16px;
}

.pl:focus{
    border: 1px solid #9B51E0;
    box-sizing: border-box;
    border-radius: 10px;
    outline: 3px solid #F8E4FF;
    border-radius: 10px;
} */

svg {
  margin-top : 20px;
  width: 8px;
  height: 50px;
}

b {
  font-size: 1.2em;
}

.buttons {
  margin: 3%;
  /* float: right; */
  display: flex;
  flex-flow: row wrap;
  justify-content: space-around;
  float: right;
}
.button {
  padding-left: 3%;
  padding-right: 3%;
}

/* .btn-animate {
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
  background: linear-gradient(100deg, #021d6e,#021d6e);
  
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
.btn-animate:after {
  
  background: linear-gradient(100deg, #4c4c4d, #4c4c4d);
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
} */
</style>