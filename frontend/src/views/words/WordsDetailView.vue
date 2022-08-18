<template>
<div class="words-detail-bg-img">
  <div id="session">
    <div id="words-detail-session-header">
      <div class="words-detail-back-title">
        <!-- 뒤로가기 -->
        <svg id="words-detail-buttonLeaveSession" @click="leaveSession" value="Leave session" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
         <!-- class="w-7 h-7"   -->
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 15l-3-3m0 0l3-3m-3 3h8M3 12a9 9 0 1118 0 9 9 0 01-18 0z">
          </path>
        </svg>
        <!-- 방제목 -->
        <div class="words-detail-session-title">
          <h1>{{ this.$route.params.sessionName }}</h1>
        </div>
      </div>

      <!-- 총 진행시간 -->
      <div class="words-detail-total-time">
      <!--  h-30 w-40 p-2 border-2 border-gray-400 bg-gray-200 -->
      <!-- <div class="h-full w-full bg-gray-400"> -->
        <!-- </div> -->
      </div>

    </div>
    <div id="words-detail-session-header2" style="width: 100%;">
      <!-- 방안사람들 -->
      <div id="words-detail-video-container" class="col-md-12">
        <WordsDetailRTCItem :stream-manager="publisher" @click.enter="updateMainVideoStreamManager(publisher)"/>
        <WordsDetailRTCItem v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click="updateMainVideoStreamManager(sub)"/>
      </div>
    </div>
    <div id="words-detail-session-body-left" class="col-md-5">
      <!-- 메인화면 -->
      <div class="words-quiz-box-timer">제한 시간 : {{this.$store.state.games.TimerStr}} 초</div>
      <div id="words-detail-quiz-box" v-if="!resultTime">
      <!-- col-md-8 -->        
        <div class="words-quiz-box-quiz" v-if="!cnt && !answerTime"> 
          <p>문제 :</p>  
          <p>{{ problem }}</p>        
        </div>
        <div class="words-quiz-box-answer" v-if="answerTime">
          <p>정답 : {{ answer }}</p>
          <!-- <div v-for="idx in answerList" :key="idx">정답자 : {{ answerList[idx] }}</div> -->
          <p>점수 : {{ point }}</p>
        </div>
      </div>
      <!-- <div class="card-body">
          <div class="words-detail-rank">
            <div class="overflow-x-auto"> -->
              <table class="table w-full" id="words-quiz-box-rank-table" v-if="resultTime">
                <!-- head -->
                <thead>
                  <tr>                        
                    <th>Nickname</th>
                    <th>Score</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- row 1 -->
                  <tr class="hover" v-for="idx of gameResult" :key="idx">
                    <td>{{idx[1]}}</td>
                    <td>{{idx[0]}}</td>
                  </tr>
                </tbody>
              </table>
            <!-- </div>
          </div>
        </div> -->
      <!-- 캠,마이크,나가기,설정 -->
      <div class="cam-buttons">
        <!-- style="height: 100%" -->
        <!-- 캠 -->
        <span class="words-detail-icon-area" @click='muteVideo'>
          <div v-if='publisher.stream.videoActive'>
            <svg xmlns="http://www.w3.org/2000/svg" style="height: 50%; width: 50%;" viewBox="0 0 20 20" fill="currentColor">
              <path d="M2 6a2 2 0 012-2h6a2 2 0 012 2v8a2 2 0 01-2 2H4a2 2 0 01-2-2V6zM14.553 7.106A1 1 0 0014 8v4a1 1 0 00.553.894l2 1A1 1 0 0018 13V7a1 1 0 00-1.447-.894l-2 1z" />
            </svg>
          </div>
          <div v-else>
            <svg xmlns="http://www.w3.org/2000/svg" style="height: 50%; width: 50%;" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M15 10l4.553-2.276A1 1 0 0121 8.618v6.764a1 1 0 01-1.447.894L15 14M5 18h8a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v8a2 2 0 002 2z" />
            </svg>          
          </div>
        </span>
        <!-- 마이크 -->
        <span class="words-detail-icon-area" @click='muteAudio'>
            <!-- <MicrophoneIcon style="height: 40; width: 40;"/>							 -->
          <div v-if='!publisher.stream.audioActive'>
            <!-- <h1>음소거제거</h1> -->
            <svg xmlns="http://www.w3.org/2000/svg" style="height: 50%; width: 50%;" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M19 11a7 7 0 01-7 7m0 0a7 7 0 01-7-7m7 7v4m0 0H8m4 0h4m-4-8a3 3 0 01-3-3V5a3 3 0 116 0v6a3 3 0 01-3 3z" />
            </svg>                
          </div>
          <div v-else>
            <!-- <h1>음소거</h1> -->
            <svg xmlns="http://www.w3.org/2000/svg" style="height: 50%; width: 50%;" viewBox="0 0 20 20" fill="currentColor">
              <path fill-rule="evenodd" d="M7 4a3 3 0 016 0v4a3 3 0 11-6 0V4zm4 10.93A7.001 7.001 0 0017 8a1 1 0 10-2 0A5 5 0 015 8a1 1 0 00-2 0 7.001 7.001 0 006 6.93V17H6a1 1 0 100 2h8a1 1 0 100-2h-3v-2.07z" clip-rule="evenodd" />
            </svg>           
            <!-- <img src="@/assets/microphone2.png" alt="mic"> -->
          </div>
        </span>
        <!-- 나가기 -->
        <span class="words-detail-icon-area" @click="leaveSession">
            <LogoutIcon style="height: 50%; width: 50%;"/>
        </span>
        <!-- 설정 -->
        <span class="words-detail-icon-area" @click="isOpen" v-if="isHost == profile.nickname">
            <CogIcon style="height: 50%; width: 50%;"/>
        </span>
      </div>
      <div class="words-detail-answer-send">
        <input type="text" name="" id="words-detail-answer-sheet" v-model="temp" tabindex="0" 
        placeholder="답을 입력해주세요." @keyup.enter="sendAnswer" v-if="!isCorrect && !answerTime"/>
        <button class="btn btn-active" @click="sendAnswer" v-if="!isCorrect && !(joker == profile.nickname) && !answerTime">submit</button>
        <!-- <PaperAirplaneIcon style="height: 8%; width: 8%;" @click="sendAnswer" v-if="!isCorrect && !answerTime" />-->
      </div>
      <!-- 정오답 알림 메시지 -->
      <div class="body-detail-check-answer">
        <!-- <input type="text" v-model="answerAlert" size="30" style="border: none; background: transparent;" /> -->
        <div v-if="isCorrect ">
          <h1 class="words-correct-answer">정답!</h1>
        </div>
        <div v-else-if="!isCorrect && !answerTime && status">
          <h1 class="words-incorrect-answer">정답을 입력해주세요.</h1>
        </div>
    </div>


    </div>


    <div id="words-detail-session-body-right" class="col-md-5">
      <!-- 랭크 -->
      <div class="card" id="words-right-card">
        <div class="card-header">
          실시간 순위
        </div>
        <div class="card-body">
          <div class="words-detail-rank">
            <div class="overflow-x-auto">
              <table class="table w-full" id="rank-table">
                <!-- head -->
                <thead>
                  <tr>                        
                    <th>Nickname</th>
                    <th>Score</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- row 1 -->
                  <tr class="hover" v-for="idx of currentRank" :key="idx">
                    <td>{{idx[1]}}</td>
                    <td>{{idx[0]}}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
        
      <!-- <div class="overflow-x-auto">
        <table class="table table-zebra w-full" id="rank-table"> -->
          <!-- head -->
          <!-- <thead>
            <tr>                  
              <th>Nickname</th>
              <th>Score</th>
            </tr>
          </thead>
          <tbody> -->
            <!-- row 1 -->
            <!-- <tr class="hover" v-for="idx of currentRank" :key="idx">
              <td>{{idx[1]}}</td>
              <td>{{idx[0]}}</td>
            </tr>
          </tbody>
        </table>
      </div> -->


      <!-- 시작버튼 & 현재 문제 남은 시간 타이머 -->
      <div class="words-detail-start-box">
        <div v-if="!status && (profile.nickname == isHost)" class="words-detail-leader-button">
          <button id="start-btn" @click="sendStart"     
          class="bg-yellow-500 hover:bg-transparent text-yellow-700 font-semibold hover:text-white 
          py-2 px-4 border border-yellow-500 hover:border-transparent rounded-full">
            START
          </button>
        </div>

        <div v-if="cnt && status" class="words-start-box-timer">
          <h1>{{threecount}}</h1>          
        </div>
      </div>

      <!-- <br> -->
      <!-- 현재 문제 남은 시간 타이머 -->
      <!-- <div class="problem-timer" style="width: 30%">남은 시간: 
        <div v-if="cnt">{{threecount}}</div>
      </div> -->
      <!-- style="width: 40; height: 40;" -->
      <!-- 임시시작버튼 -->
      <!-- <div v-if="!start" class="words-detail-leader-button">
          <button @click="sendStart" class="bg-transparent hover:bg-blue-500 text-blue-700 font-semibold hover:text-white py-2 px-2 border border-blue-500 hover:border-transparent rounded-full">
              START
          </button>
      </div> -->
      
      <!-- 시작버튼자리 -->
      <!-- <div v-if="!start && !ready">
          <div class="start-box"> -->
              <!-- 방장만 스타트 버튼 보이기 -->
              <!-- <div v-if='myUserNick === roominfo.ownerNicknames'>
                  <button @click="gameStart"
                  class="bg-transparent hover:bg-blue-500 text-blue-700 font-semibold hover:text-white py-2 px-2 border border-blue-500 hover:border-transparent rounded-full">
                      START
                  </button>
              </div> -->
              <!-- 방장 아닌 사람은 준비중 -->
              <!-- <div v-else>
                      <p>준비중</p>
              </div>
          </div>
      </div> -->                      

    </div>
  </div>   
</div>
		


	<WordsRoomUpdateModal v-model:open="open" />
  

</template>

<script>

import WordsDetailRTCItem from './components/WordsDetailRTCItem.vue';
import { OpenVidu } from 'openvidu-browser';
import axios from 'axios'
import { mapGetters, mapActions } from 'vuex';
import WordsRoomUpdateModal from '../modal/components/WordsRoomUpdateModal.vue'
import { VideoCameraIcon, MicrophoneIcon, LogoutIcon, CogIcon, PaperAirplaneIcon } from '@heroicons/vue/outline';
axios.defaults.headers.post['Content-Type'] = 'application/json';
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

// stomp url
const serverUrl = "https://i7d109.p.ssafy.io/ws/game"


export default {
  name : 'WordsDetailView',
  components : {
    WordsDetailRTCItem,
    WordsRoomUpdateModal,
    // VideoCameraIcon,
    //MicrophoneIcon,
    LogoutIcon,
    CogIcon,
    // PaperAirplaneIcon,
},
  data () {
    return {
        mySessionId: '',
        OV: undefined,
        session: undefined,
        mainStreamManager: undefined,
        publisher: undefined,
        subscribers: [],
        
        count : 5,
        threecount : 3,
        // 게임 시작 관련
        start: false, // 게임시작유무
        cnt : false, // 카운트시작유무
        ready: false,

        // 답입력값
        answerSheet: '',
        answerAlert: '',
        gameStatus: 0,
        round: 0,
        
        open : false,

        problem : '',
        answer : '',
        difficulty : '',
        answerList : [],
        isConnect : false,
        stompClient : {},
        status : null,
        problemNum : 0,
        temp : '',
        currentRank : [],
        trigger : true,
        playerLen : 0,
        isAll : false,
        isCorrect : false,
        answerTime : false,
        point : 0,
        currentPlayers : [],
        joker : '',
        isHost : '',
        totalQuestion : 0,
        isSolving : [],
        resultTime : false,
        gameResult : [],
        result : false
    }
  },
  
  created(){
    this.joinSession(),
    this.socketStart()
    this.isHost = this.$route.params.host
    console.log(this.isHost),
    this.setRoomInfo()
  },

  computed : {
    ...mapGetters(['authHeader','profile']),
    correctList (){
      return this.answerList
    }
  },
//this.$route.params.token.slice(39,53)
  methods : {
    setRoomInfo(){
    if (this.$route.params.host == this.profile.nickname){
      this.totalQuestion = this.$route.params.totalQuestion
      
    }},
    ...mapActions(['timerStart']),
    // 오픈비두 세션에 들어가기,created에 실행
    joinSession () {
            // 세션의 id는 토큰에서 잘라서 활용 
            this.getToken()
            this.mySessionId = this.token
            this.mySessionId
            this.OV = new OpenVidu();
            this.session = this.OV.initSession();

            this.session.on('streamCreated', ({ stream }) => {
                const subscriber = this.session.subscribe(stream);
                console.log(subscriber, 1234)
                this.subscribers.push(subscriber);
            });


            this.session.on('streamDestroyed', ({ stream }) => {
                const index = this.subscribers.indexOf(stream.streamManager, 0);
                if (index >= 0) {
                    this.subscribers.splice(index, 1);
                }
            });


            this.session.on('exception', ({ exception }) => {
                console.warn(exception);
            });

            // 화상 연결 만들기
            this.createPublisher()
            

            window.addEventListener('beforeunload', this.leaveSession)
        },

        // 방 나가기
        leaveSession () {
            
            // 우리 소켓 통신 연결 해제 
           
            // 타이머 강제 종료
           this.$store.state.games.TimerChk = true
           // axios로 스프링 서버의 방에서 나가기
            axios({
                url : `/api/word-game/rooms/${this.$route.params.roomSequence}`,
                method : 'delete',
                headers : this.authHeader
            })

            .then(() =>{
              // 방장 나갈 때 
            if (this.isHost === this.profile.nickname) {
              const gameInfo = {totalQuestion:10}
              this.stompClient.send(`/game/word-game/room/${this.$route.params.roomSequence}/owner/${this.totalQuestion}`, 
              JSON.stringify(gameInfo), {})
            }
            
            if(this.session) {this.session.disconnect();}
            if (this.stompClient) {this.stompClient.disconnect()}
            this.stompClient = null;

            this.session = undefined;
            this.mainStreamManager = undefined;
            this.publisher = undefined;
            this.subscribers = [];
            this.OV = undefined;
            window.removeEventListener('beforeunload', this.leaveSession);
              this.$router.push({name : 'WordsMainView'})
            })
            // 그 후 세션에서 나가기 
            
            // 나가는 일련의 과정이 끝나면 MainView로 라우터 이동
            
        },
        
        // 화상 만들기
    createPublisher(){
        this.session.connect(this.token,{ clientData: this.profile })
        .then(() => {

          // 미디어 스트림 가져오기 
                    
          let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true,      // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true,      // Whether you want to start publishing with your video enabled or not
              resolution: '640x480',  // The resolution of your video
              frameRate: 30,            // The frame rate of your video
              insertMode: 'APPEND',    // How the video is inserted in the target element 'video-container'
              mirror: false           // Whether to mirror your local video or not
          });
          
          this.mainStreamManager = publisher;
          this.publisher = publisher;

          
          this.session.publish(this.publisher);
          console.log(this.publisher)
      })
      .catch(error => {
          console.log('There was an error connecting to the session:', error.code, error.message);
      })
    },

    updateMainVideoStreamManager (stream) {
        if (this.mainStreamManager === stream) return;
        this.mainStreamManager = stream;
    },

    getToken() {
        if (this.$route.params.token) {
            this. token = this.$route.params.token
            return this.token
        } else {
            return this.token
        }
    },
    // 버튼에 해당하는 메서드
	muteVideo() {
      if (this.publisher.stream.videoActive) {
        this.publisher.publishVideo(false)
      }else {
        this.publisher.publishVideo(true)
      }
    },
	muteAudio() {
      if (this.publisher.stream.audioActive) {
        this.publisher.publishAudio(false)
      }else {
        this.publisher.publishAudio(true)
      }
    },    
	isOpen (){
      return this.open = !this.open
    },


  threecountDown(){
    this.cnt = true
    setTimeout(() => {this.threecount = 3,this.problemNum++}, 1000)
    setTimeout(() => {this.threecount = 2}, 2000)
    setTimeout(() => {this.threecount = 1}, 3000)
    setTimeout(() => {this.threecount = 'START!'}, 4000)
    setTimeout(() => { this.cnt=false,this.answerTime = false,this.trigger=false }, 4500)
    setTimeout(() => { this.timerStart(15) }, 4500)
    this.threecount = 3
    setTimeout(() => { this.getProblem() }, 1500)
  },
  // stomp 시작
  // 필요한 거 제한 시간, 방장, 제한 문제수,난이도 (word-game의 경우 제한 시간 필요 없음)
  // 게임 시작 -> 5초 카운트 전에 문제 받아옴 ->  
  // 다 맞추거나 or 시간이 다 지나가면 3초 카운트 
  socketStart(){
      this.cnt = true
      let socket = new SockJS(serverUrl)
      this.stompClient = Stomp.over(socket)
      console.log('소켓 연결하는 중')
      this.stompClient.connect({}, frame => {
          console.log(frame, '연결 성공!')
          this.stompClient.subscribe(`/topic/word-game/${this.$route.params.roomSequence}`, 
          res => {
              const response = JSON.parse(res.body)
              let key = Object.keys(response) 
              if ("gameStatus" === key[1]){
                  this.status = true
                  this.playerLen = response.players.length
                  this.currentPlayers = response.players
                  this.totalQuestion = response.totalQuestion
                  this.currentRank = [],
                  this.problemNum = 0,
                  this.isSolving = []
                  this.resultTime = false
                  this.gameResult = [],
                  this.result = false
                  for (let i of response.players) {
                    this.currentRank.push([0,i])
                  }
                  console.log(this.currentRank)
                  this.threecountDown()
              } else if (key[0] === 'problem') {
                  this.problem = response.problem
                  
              } else if (key[0] === 'roomSequence') {
                  this.answer = response.answer
                  this.answerTime = true
                  this.point = response.point
                  this.answerList = response.correctPlayers
                  if (!this.isSolving.includes(this.answer) && this.problemNum == this.totalQuestion){
                    this.sendResult()
                  } else if (!this.isSolving.includes(this.answer)&& !this.trigger){
                    console.log('여기를 두번 온다고?', this.problemNum)
                    this.isSolving.push(this.answer)
                    this.isSolving.push(this.problemNum)
                    console.log('여기임진짜?', this.problemNum)
                    setTimeout(() => {this.threecountDown()}, 3000)
                    this.trigger = true
                    if (this.answerList.length >0) {
                    for (let i of this.answerList) {
                      for ( let j of this.currentRank) {
                        if (i === j[1]){
                          console.log('1234567890')
                          j[0] += response.point
                          break
                        }
                      }
                    this.currentRank.sort(function(a, b)  {
                      return b[0] - a[0];
                    })
                  }   
                  }
                  }
                  this.answerList= []
                  this.isCorrect = false 

              } else if (key[0] === 'players') {
                  this.resultTime = true
                  this.status = false
                  this.start = false
                  this.currentRank = []
                  this.answerTime = false
                  this.resultTime = true
                  if (!this.result) {
                    this.result = true
                    for (let i of Object.keys(response.players)) {
                      console.log(this.gameResult)
                      console.log(this.gameResult.includes([response.players[`${i}`],i]))
                      this.gameResult.unshift([response.players[`${i}`],i])
                    } }
                   
                  this.gameResult.sort(function(a, b)  {
                      return b[0] - a[0];
                    })

              } else if (key[0] == 'correctPlayers') {
                this.answerList = response.correctPlayers
                for(let i in this.answerList){
                  if (i === this.profile.nickname){
                    this.isCorrect = true
                    break
                  }
                }
                
                for(let i of this.answerList){
                  if (i === this.profile.nickname){
                    this.isCorrect = true
                    break
                  }
                }
              } else if (key[0] == 'owner') {
                this.isHost = response.owner
                this.totalQuestion = response.totalQuestion
              }
          })
            
      },
      )
    },
  sendStart(){
    console.log('보낼거임')
    this.start = true
    const gameStatus = {
        total_question : this.totalQuestion
    }
    this.stompClient.send(`/game/word-game/${this.$route.params.roomSequence}`, JSON.stringify(gameStatus), {})
    
  },

  getProblem(){
    this.trigger = true
    this.stompClient.send(
        `/game/word-game/room/${this.$route.params.roomSequence}/problem/${this.problemNum}`, undefined, {}
    )
    this.setCorrect()
    
    },
      
  async setCorrect(){
    let timeout = setTimeout(() => { this.$store.state.games.all = false; this.sendCorrect();clearInterval(interval)}, 18000)
    let interval = setInterval(() => {
      this.allCorrect()
      if(this.$store.state.games.all){
        clearTimeout(timeout)
        this.sendCorrect()
        this.$store.state.games.all = false
        clearInterval(interval)
        console.log('왜 한 명 맞추면 3초후에 터지는거지?')
      }
    }, 1000)
     
    // if (!result) {
    //   this.sendCorrect()
    // } 
  },
  
  allCorrect(){
   
    // let interval = setInterval(() => {
      if (this.answerList.length == this.playerLen){
        this.$store.state.games.TimerChk = true
        this.$store.state.games.all = true
        // clearInterval(interval)
      }
      
    // }, 1000)
    
   
  },
 

  sendAnswer(){
    const foranswer = {
        player : this.profile.nickname,
        submit : this.temp,
        problem_num : this.problemNum
    }
    const submit = JSON.stringify(foranswer)
    this.stompClient.send(
        `/game/word-game/submit/${this.$route.params.roomSequence}`, submit, {}
    )
    this.temp = ''
    },

  sendCorrect(){
        if (!this.isSolving.includes(this.problemNum)){
          this.isSolving.push(this.problemNum)
          const questionNum = {question_num : this.problemNum} 
          this.stompClient.send(`/game/word-game/answer/${this.$route.params.roomSequence}`,
          JSON.stringify(questionNum), {}
          )
        }
       
    },

  sendResult(){
    this.stompClient.send(
        `/game/word-game/result/${this.$route.params.roomSequence}`,
        undefined,
        {}
      )
      this.status = false,
      this.start = false,
      this.currentRank = [],
      this.currentPlayers =[]
  },
  
  }
  } 

</script>

<style>
/* scoped 하면 적용안됨 */
svg {
  cursor: pointer;
  color: black;	
}

.words-detail-bg-img {
  margin: auto;
  background-image : url("@/assets/word-game-bg2.jpg.jpg");
  /* background-image: url("@/assets/14.png"); */
  background-size: cover;
  width: 100vw;
  height: 100vh;
  /* position: absolute; */
  /* position: relative; */
  position: absoulute;
  z-index: 1;
  /* background-color: transparent; */

}

.words-detail-bg-img::after {
  /* margin: auto;
  width: 100vw;
  height: 100vh;
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1; */
  /* opacity: 0.6; */
  /* content: ""; */
  /* background: url("@/assets/14.png"); */
  /* background-image: url("@/assets/word-game-bg.jpg"); */
  /* background-image : url("@/assets/word-game-bg2.jpg.jpg"); */
  /* background-size: cover; */
  
}

/* #main-container {
	margin: auto;
} */

#join + #session {
	justify-content: center;
  align-items: center;
	/* width: 100%; */


}

#words-detail-session-header {
  margin: auto;
  padding: 0.4%;
  width: 96%;
  height: 10%;
  display: flex;
  flex-flow: row wrap;
  justify-content: space-between;
  align-self: center;

  background-color: transparent;

}

.words-detail-back-title {
  display: flex;
  flex-flow: row wrap;
  justify-content: flex-start;
  align-self: center;

  background-color: transparent;
}

.body-detail-back-icon {
  /* width: 40%; */
}

.words-detail-icon-area {
  border: none;
  display: inline-block;
  width: 20%;
  height: 20%;

  background-color: transparent;
}

#words-detail-buttonLeaveSession {
  width: 10%;
  display: block;
}

.words-detail-session-title {
  padding-left: 2%;
  font-size: 1rem;
  display: flex;
  align-self: center;
  text-align: center;

  justify-content : center;
	align-items : center;

  color: black;

}

.words-detail-total-time {
  /* padding-right: 6%; */
  font-size: 1rem;
  display: flex;
  align-self: center;
  text-align: center;
  
  justify-content : center;
	align-items : center;

  color: black;

}

#words-detail-session-header2 {
  display: flex;
  /* flex-direction: row;
  flex-wrap: wrap; */
  flex-flow: row wrap;
  justify-content: space-between;
  /* justify-content: center; */
  align-self: center;
  align-items : center;

  background-color: transparent;

  /* right: 0%; */
  /* margin: 0 2.2vw;
  padding: 2vh; */
}

#words-detail-video-container {
  background-color: transparent;
  /* display: flex;
  flex-direction: row;
    float: left;
  align-self: center; */

  /* justify-content: center; */
    /* right: 0%; */
}

#words-detail-video-container video {
   /* position: relative; */
   float: left;
   width: 16%;
   margin-left:0.6%;
   border:3px solid;
   border-color:rgb(255, 255, 255);
   /* cursor: pointer; */
   /* margin:  2%;  */
   /* margin-left: 5%; */
   display: flex;
   align-items: center;
   justify-content: space-around;

   background-color: transparent;
}

#words-detail-video-container video + div {
  
  text-align: center;
  /* line-height: 75px; */
   float: left;
   width: 28%;
   position: relative;
   margin-left:-28.5%;
   /* display: flex; */
   /* justify-content: space-around; */

   background-color: transparent;
}

#words-detail-video-container p {
  font-family:'IM_Hyemin-Bold';
  display: inline-block;
  background: #f8f8f8;
  padding-left: 5px;
  padding-right: 5px;
  color: #3c90c9;
  font-weight: bold;
  border-radius: 8px;
    /* text-align: right; */
}

video {   
  /* padding-top: 1vh; */
  /* 맨 아래에 나오는 카메라화면 */
  /* width: ; */
  width: 100%;
  /* height: 48vh; */
  height: auto;
  position: relative;

  justify-content: center;
  align-items: center;

  border-radius: 10% 10% 10% 10%;
}

#words-detail-session-body-left {
  /* margin-left */
  margin-left: 3%;  
  /* margin-right: 1%;   */
  margin-top: 1%;  
  display: flex;
  flex-direction: column;
  float: left;
  justify-content: center;
  align-self: center;
  align-items: center;
  
  background-color: transparent;
}


#words-detail-quiz-box {
  width: 30vw;
  height: 40vh;
  padding: 3%;

  display: flex;
  flex-flow: column;
  justify-content: center;
  align-items: center;

  background-color: white;
  
  border: 0.5rem;
  border-style: solid;
  border-radius: 10% 10% 10% 10%;
  border-color: white;

}


#words-detail-quiz-box div{
  /* font-size: 1.8rem; */
  font-weight: bolder;
  color: black;
}

.words-quiz-box-timer {
  font-size: 1.6rem;
  font-weight: bolder;
  color: black;
  /* height: 16%; */
  display: flex;
  justify-content: center;
  align-items: center;
  
}

.words-quiz-box-quiz {
  font-size: 2rem;
  /* height: 80%; */
  display: flex;
  flex-flow: column;
  justify-content: center;
  align-items: center;
}



.cam-buttons {
    width: 60%;
    margin: 0 auto;
    margin-top: 2%;

    display: flex;
    /* flex-direction: row; */
    flex-flow: row wrap;
    justify-content: space-evenly;
    align-self: center;
    /* justify-content: center; */
    text-align: center;
    /* width: 400;
    height: 40; */
    vertical-align: middle;
    
}


.words-detail-answer-send {
  padding-top: 2%;
  display: flex;
  flex-direction: row;
  /* align-self: center; */
  justify-content: center;
  align-items: center;
  margin: auto;
  width: 68%;
    
}

#words-detail-answer-sheet {
    /* border: 1; */
    border: 3px solid black;
    font-size: 2rem;
    width: 90%;
}

.words-detail-check-answer {
    /* border: 1; */
    /* border: 1px dotted black; */
    /* text-align: center; */
    /* margin: auto; */

    /* justify-content: center;
    align-self: center;
    font-size: 2rem;
    color: red; */


    /* background-color: transparent;
    border: none;
    background: transparent; */

}

.words-correct-answer {
  justify-content: flex-start;
  align-self: center;
  font-size: 2rem;
  color: red;

}

.words-incorrect-answer {
  justify-content: flex-start;
  /* align-self: center; */
  font-size: 1rem;
  color: blue;
  /* font-style: italic; */

}


#words-detail-session-body-right {
  /* padding-left: 3%;  
  padding-right: 3%;  
  padding-top: 1%;   */
  margin-right: 3%;
  padding-top: 1%;
  padding-right: 5%;
  display: flex;
  flex-direction: column;
  float: right;
  justify-content: center;
  align-self: center;    

  background-color: transparent;
}

#words-right-card {
  /* background-color: transparent;
  border-color: #ffff; */
  background-color: rgba(60, 60, 60, 0.26 );
  color: #ffff;
  border: none;
}

.words-detail-rank {
  /* padding-left: 1%; */
  /* width: 20rem; */
  /* text-align: center; */
  /* margin: auto; */


  /* border: thick double #32a1ce;
  padding-right: 3%; */
  height: auto;
  justify-content: center;
  font-size: 1rem;
}

#rank-table {
  color: #ffff;
  /* 테이블 넘치면 지우기 */
  font-size: 1.3rem;
}

.words-detail-start-box {
  position: fixed;
  bottom: 2%;
  /* width: 150%; */
  /* margin: 0 auto 2.5vh; */
   
   /* position: relative;
   width: 33vw;
   height: 48vh;
   background: rgba(192, 192, 199, 0.47);
   border: 3px solid white;
   border-radius:20px; */

   display:flex;
   justify-content: center;
   align-items: center;
   align-self: center;
}

.words-detail-leader-button {
    /* font-size: 2rem; */
    font-weight: bolder;
    text-align: center;
    /* justify-content: center; */

    /* margin-top: 3%;
    margin-bottom: 3%; */
}

#start-btn {
  /* border: 0.5rem solid; */
  font-size: 3rem;
  font-weight: 900;
  text-align: center;
  background-color: rgb(166, 122, 0);
  color: #ffff;
  border: none;
}


.words-start-box-timer {
  align-self: center;
  /* float: right; */
  text-align: center;
  /* margin-top: 5%;
  margin-bottom: 3%; */
  font-size: 3rem;   

  justify-content : center;
	align-items : center;
    
}

.words-start-box-timer h1 {
  /* color: rgb(166, 122, 0); */
  font-size: 3rem;
  color: rgb(166, 122, 0);
  font-size: 3rem;
  font-weight: 900;

}

#words-quiz-box-rank-table {
  color: black;
  width: 30vw;
  height: 40vh;
  padding: 3%;

  /* display: flex;
  flex-flow: column;
  justify-content: center;
  align-items: center; */

  background-color: white;
  
  /* border: 0.5rem;
  border-style: solid;
  border-radius: 10% 10% 10% 10%;
  border-color: white; */

  font-size: 1.6rem;
}


.words-quiz-box-answer{
  font-size: 2rem;
  /* height: 80%; */
  display: flex;
  flex-flow: column;
  justify-content: center;
  align-items: center;
}


</style>