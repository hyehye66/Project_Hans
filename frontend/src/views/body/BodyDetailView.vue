<template>
<div class="body-detail-bg-img">
<div id="session">
  <div id="body-detail-session-header">
    <div class="body-detail-back-title">
      <!-- 뒤로가기 -->
      <!-- <div class="back-icon"> -->
        <svg id="body-detail-buttonLeaveSession" @click="leaveSession" value="Leave session" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 15l-3-3m0 0l3-3m-3 3h8M3 12a9 9 0 1118 0 9 9 0 01-18 0z">
          </path>
        </svg>
      <!-- </div> -->
      <!-- 방제목 -->
      <div class="body-detail-session-title">
        <h1>{{ this.$route.params.sessionName }}</h1>
      </div>      
    </div>
    

    <div class="body-detail-total-time">
    <!--  h-30 w-40 p-2 border-2 border-gray-400 bg-gray-200 -->
      <!-- <div class="h-full w-full bg-gray-400"> -->
        
        
      <!-- </div> -->
    </div>
  </div>

  <div id="body-detail-session-header2" style="width: 100%;">
    <!-- 방안사람들 -->
    <div id="body-detail-video-container" class="col-lg-12">
      <user-video :stream-manager="publisher" @click.enter="updateMainVideoStreamManager(publisher)"/>
      <user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click="updateMainVideoStreamManager(sub)"/>
      <!-- <div class="my-screen">
        <BodyDetailRTCItem :stream-manager="publisher" @click.enter="updateMainVideoStreamManager(publisher)"/>
      </div>
      <div class="others-screen">
        <BodyDetailRTCItem v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click="updateMainVideoStreamManager(sub)"/>
      </div> -->
    </div>
  </div>

  <div id="body-detail-session-body-left" class="col-md-5">
    <div class="body-quiz-limit-timer" v-if="status">제한 시간 : {{this.$store.state.games.TimerStr}} 초</div> 
     <!-- v-if="status && !answerTime" -->
    <!-- 메인화면 -->
    <div id="body-detail-main-video">
    <!-- col-md-8 -->
      <user-video :stream-manager="mainStreamManager" v-if="!answerTime && status"/>
      <span id="the-answer" v-if="answerTime"> 정답 : {{answer}} <br> 난이도 : {{point}}</span>
      <!-- &nbsp; &nbsp; -->
      <!-- <span id="difficulty" v-if="answerTime"> </span> -->
      <!-- <div class="card-body" >
          <div class="words-detail-rank"> -->
            <!-- <div class="overflow-x-auto"> -->
              <table class="table w-full" id="main-video-rank-table" v-if="resultTime">

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
            <!-- </div> -->
          <!-- </div>
        </div> -->
    </div>
    <!-- 캠,마이크,나가기,설정 -->
    <div class="cam-buttons">
    <!-- style="height: 100%" -->
       <!-- 캠 -->
      <span class="body-detail-icon-area" @click='muteVideo'>
        <!-- <VideoCameraIcon style="height: 50%; width: 50%;" /> -->
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
      <span class="body-detail-icon-area" @click='muteAudio'>
        <!-- <MicrophoneIcon style="height: 40; width: 40;"/>	-->
        <div v-if="!(joker == profile.nickname)">
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
        </div>
      </span>
      <!-- 나가기 -->
      <span class="body-detail-icon-area" @click="leaveSession">
          <LogoutIcon style="height: 50%; width: 50%;"/>
      </span>
      <!-- 설정 -->
      <span class="body-detail-icon-area" @click="isOpen" v-if="isHost == profile.nickname">
          <CogIcon style="height: 50%; width: 50%;"/>
      </span>
    </div>

    <!-- 정답 적는 란 -->
    <div>
      <!-- <div v-if="!cnt">제한 시간 : {{this.$store.state.games.TimerStr}} 초</div> -->      
      <div v-if="!cnt && (joker == profile.nickname)&& !answerTime" class="body-joker-quiz">
        문제 : {{ problem }}
      </div>
      <!-- <div v-else-if="cnt && !answerTime" class="body-joker-quiz">
        술래 : {{ joker }}
      </div> -->

      <div v-else class="body-detail-answer-send">
      
        <input type="text" name="" id="body-detail-answer-sheet" v-model="temp" size="26"
        placeholder="답을 입력해주세요." @keyup.enter="sendAnswer" v-if="!isCorrect && !(joker == profile.nickname) &&!answerTime"/>
        <button class="btn btn-active" @click="sendAnswer" v-if="!isCorrect && !(joker == profile.nickname) && !answerTime">submit</button>
        <!-- <PaperAirplaneIcon style="height: 35; width: 35;" @click="sendAnswer" v-if="!isCorrect && !(joker == profile.nickname)&&!answerTime"/> -->
        <p v-if="answerTime"></p>
      </div>
    </div>
    <!-- 정오답 알림 메시지 -->
    <div class="body-detail-check-answer">
        <!-- <input type="text" v-model="answerAlert" size="30" style="border: none; background: transparent;" /> -->
        <div v-if="isCorrect ">
          <h1 class="body-correct-answer">정답!</h1>
        </div>
        <div v-else-if="!isCorrect && !answerTime && status &&!(joker == profile.nickname)">
          <h1 class="body-incorrect-answer">정답을 입력해주세요.</h1>
        </div>
    </div>

  </div>

  <!-- <div id="body-detail-session-body-middle" class="col-md-2">
    <div v-if="!cnt && (joker == profile.nickname)" class="body-joker-quiz">
      <p>문제 :</p>
      <p>{{ problem }}</p>
    </div>
  </div> -->

  <div id="body-detail-session-body-right" class="col-md-5">
    <!-- 랭크 -->
    <div class="card" id="body-right-card">
      <div class="card-header">
        실시간 순위
      </div>
      <div class="card-body">
        <div class="body-detail-rank">
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
    
    <!-- <br> -->

    <!-- 현재 문제 남은 시간 타이머 -->
    <!-- <div class="body-detail-problem-timer" > -->
    <!-- style="width: 30%" -->
    <!-- style="width: 40; height: 40;" -->
      <!-- 남은 시간:awegfads
      <div v-if="cnt">{{count}}</div>
    </div> -->
    
    <!-- 시작버튼 & 현재 문제 남은 시간 타이머 -->
    <div class="body-detail-start-box">
      <div v-if="!status && (profile.nickname == isHost)" class="body-detail-leader-button">
        <button id="start-btn" @click="sendStart"     
        class="bg-yellow-500 hover:bg-transparent text-yellow-700 font-semibold hover:text-white 
        py-2 px-4 border border-yellow-500 hover:border-transparent rounded-full">
          START
        </button>
        <!-- <button class="btn btn-primary" id="start-btn" @click="sendStart">START</button> -->
      </div>
      <div v-if="cnt && status" class="body-start-box-timer">
        <h1>{{threecount}}</h1>
      </div>
    </div>
      
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

<BodyRoomUpdateModal v-model:open="open" />  

</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
// import BodyDetailRTCItem from './components/BodyDetailRTCItem.vue';
import UserVideo from './components/UserVideo.vue';
import { OpenVidu } from 'openvidu-browser';
import axios from 'axios'
import { mapGetters,mapActions } from 'vuex';
import BodyRoomUpdateModal from '../modal/components/BodyRoomUpdateModal.vue'
import { VideoCameraIcon, MicrophoneIcon, LogoutIcon, CogIcon, PaperAirplaneIcon } from '@heroicons/vue/outline';
axios.defaults.headers.post['Content-Type'] = 'application/json';

const serverUrl = "https://i7d109.p.ssafy.io/ws/game"


export default {
  name : 'BodyDetailView',
  components : {
    // BodyDetailRTCItem,
    UserVideo,
    BodyRoomUpdateModal,

    // VideoCameraIcon,
    // MicrophoneIcon,
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
      myUserName: ``,
      
      count : 5,
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
      problemNum : 1,
      temp : '',
      currentRank : [],
      trigger : true,
      playerLen : 0,
      isAll : false,
      isCorrect : false,
      answerTime : false,
      threecount : 3,

      point : 0,
      currentPlayers : [],
      joker : '',
      isHost : this.$route.params.host,
      changeTagger : false,
      totalQuestion : 0,
      timeLimit : 0,
      tempRankList : {},
      isSolving : [],
      resultTime : false,
      gameResult : [],
      result : false
    }
  },
  
  created(){
    this.joinSession(),
    this.socketStart(),
    this.setRoomInfo(),
    this.isHost = this.$route.params.host,
    this.$store.state.games.TimerStr = ''
  },

  computed : {
    ...mapGetters(['authHeader','profile'])
  },
//this.$route.params.token.slice(39,53)
  methods : {
    setRoomInfo(){
    if (this.$route.params.host == this.profile.nickname){
      this.difficulty = this.$route.params.difficulty
      this.timeLimit = this.$route.params.timeLimit
      this.totalQuestion = this.$route.params.totalQuestion
      
    }},
    ...mapActions(['timerStart']),
    // 오픈비두 세션에 들어가기,created에 실행
    joinSession () {
      // 세션의 id는 토큰에서 잘라서 활용 
      this.getToken()
      this.mySessionId = this.token
      this.myUserName = this.profile.nickname
      this.OV = new OpenVidu();
      this.session = this.OV.initSession();

      this.session.on('streamCreated', ({ stream }) => {
          const subscriber = this.session.subscribe(stream);
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
            this.$store.state.games.all = false

            this.$store.state.games.TimerChk = true

           // axios로 스프링 서버의 방에서 나가기
            axios({
                url : `/api/body-game/rooms/${this.$route.params.roomSequence}`,
                method : 'delete',
                headers : this.authHeader
            })

            .then(() =>{

            if (this.isHost == this.profile.nickname) {
              const gameInfo = {
                difficulty : this.difficulty,
                timeLimit : this.timeLimit,
                totalQuestion : this.totalQuestion
                }
              
              this.stompClient.send(`/game/body-game/room/${this.$route.params.roomSequence}/owner`,  
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
              this.$router.push({name : 'BodyMainView'})
            })
            // 그 후 세션에서 나가기 
            
            // 나가는 일련의 과정이 끝나면 MainView로 라우터 이동
            
        },
  
  // 화상 만들기
  createPublisher(){
      this.session.connect(this.token,{ clientData: this.myUserName })
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

  gameStart() {
      this.start = true
      this.cnt = true
      this.countDown()

    },
  threecountDown(){
    this.cnt = true
    setTimeout(() => {this.threecount = 3}, 1000)
    setTimeout(() => {this.threecount = 2}, 2000)
    setTimeout(() => {this.threecount = 1}, 3000)
    setTimeout(() => {this.threecount = 'START!'}, 4000)
    setTimeout(() => { this.cnt=false, this.answerTime = false,this.trigger=false }, 4500)
    setTimeout(() => { this.timerStart(this.timeLimit), console.log(this.$store.state.games.TimerStr) }, 4500)
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
          this.stompClient.subscribe(`/topic/body-game/${this.$route.params.roomSequence}`, 
          res => {
              const response = JSON.parse(res.body)
              let key = Object.keys(response) 
              if ("gameStatus" === key[1]){
                  
                  this.status = true
                  this.currentPlayers =[],
                  this.problemNum = 1,
                  this.isSolving = []
                  this.timeLimit = response.timeLimit
                  this.totalQuestion = response.totalQuestion
                  this.resultTime = false
                  this.gameResult = []
                  this.result = false
                  for (let i of response.players) {
                    this.currentRank.push([0,i])
                  }
                  for (let j of response.players) {
                    this.currentPlayers.push(j)

                  } 
                  
                  this.playerLen = Object.keys(response.players).length
                  console.log(this.currentPlayers, this.playerLen)

                  this.joker = this.currentPlayers.shift()
                  console.log(this.joker, '술래확인용')
                  this.currentPlayers.push(this.joker)

                  this.getSub(this.joker)

                  this.threecountDown()

              } else if (key[0] === 'problem') {
                  this.problem = response.problem
                  this.changeTagger = false
                  console.log(this.problemNum, '몇 번 문제임?')

              } else if (key[0] === 'roomSequence') {
                  this.answer = response.answer
                  this.answerList = response.correctPlayers
                  this.answerTime = true
                  this.point = response.point
                  
                  if (!this.isSolving.includes(this.answer) && this.problemNum == this.totalQuestion){
                    // this.problemNum++
                    
                    this.sendResult()
                  } else if (!this.isSolving.includes(this.answer)&& !this.trigger){
                    console.log('여기를 두번 온다고?', this.problemNum)
                    this.isSolving.push(this.answer)
                    this.isSolving.push(this.problemNum)
                    this.problemNum++
                    console.log('여기임진짜?', this.problemNum)
                    setTimeout(() => {this.threecountDown()}, 3000)
                    this.trigger = true
                    if (Object.keys(this.answerList).length >0) {
                    for (let i of Object.keys(this.answerList)) {
                      for ( let j of this.currentRank) {
                        if (i === j[1]){
                          console.log('1234567890')
                          j[0] += this.answerList[i]
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
                  
                 if (!this.changeTagger){
                  this.joker = this.currentPlayers.shift()
                  
                  this.getSub(this.joker)
                  this.currentPlayers.push(this.joker)    
                  this.changeTagger = true
                 }

              } else if (key[0] == 'correctPlayers') {
                this.answerList = response.correctPlayers
                for(let i in this.answerList){
                  if (i === this.profile.nickname){
                    this.isCorrect = true
                    break
                  }
                }
              } else if (key[0] == 'owner') {
                this.isHost = response.owner
                this.difficulty = response.difficulty
                this.totalQuestion = response.totalQuestion
                this.timeLimit = response.timeLimit

              } else if (key[0] == 'players'){
                this.status = false
                this.start = false
                this.currentRank = []
                this.answerTime = false
                this.resultTime = true
                this.joker = ''
                if (!this.result) {
                  this.result = true
                  for (let i of Object.keys(response.players)) {
                  console.log(i)
                  console.log(response.players[`${i}`])
                  this.gameResult.unshift([response.players[`${i}`],i])
                }
                }
                
                this.gameResult.sort(function(a, b)  {
                    return b[0] - a[0];
                  })
              }
          })
            
      },
      )
    },
  sendStart(){
    this.start = true
    
    const gameStatus = {
        total_question : this.totalQuestion,
        difficulty : this.difficulty,
        timeLimit : this.timeLimit
    }
    this.stompClient.send(`/game/body-game/${this.$route.params.roomSequence}`, JSON.stringify(gameStatus), {})
    
  },

  getProblem(){
    console.log('여기 몇 번 오나요?', this.problemNum)
    if (this.problemNum <= this.totalQuestion) {
      this.stompClient.send(
      `/game/body-game/room/${this.$route.params.roomSequence}/problem/${this.problemNum}`, undefined, {}
      )
    this.setCorrect()
    }
    },
      
  async setCorrect(){
    console.log('셋 커렉트 몇번 함?', this.problemNum)
    let timeout = setTimeout(() => { this.$store.state.games.all = false; this.sendCorrect();clearInterval(interval),console.log('여기서 터짐?')}, this.timeLimit * 1000 + 3000)
    let interval = setInterval(() => {
      this.allCorrect()
      if(this.$store.state.games.all){
        console.log('여기서 터지냐?')
        clearTimeout(timeout)
        this.sendCorrect()
        this.$store.state.games.all = false
        clearInterval(interval)
      }

    }, 1000)
     
  },
  
  allCorrect(){
   
    // let interval = setInterval(() => {
      if (Object.keys(this.answerList).length == this.playerLen-1){
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
        problem_num : this.problemNum,
        time : this.$store.state.games.TimeCounter
    }
    const submit = JSON.stringify(foranswer)
    this.stompClient.send(
        `/game/body-game/submit/${this.$route.params.roomSequence}`, submit, {}
    )
    this.temp = ''
    },

  sendCorrect(){

    if (!this.isSolving.includes(this.problemNum)){
      
      console.log(this.isSolving, '몇 번 받아오는 중?', this.totalQuestion)

      const questionNum = {question_num : this.problemNum} 
      this.stompClient.send(`/game/body-game/answer/${this.$route.params.roomSequence}`,
      JSON.stringify(questionNum), {}
      )
      

      console.log(this.problemNum, 'e다음 문제 찍어주세용')
      
    }
        
    },

  sendResult(){
    this.stompClient.send(
        `/game/body-game/result/${this.$route.params.roomSequence}`,
        undefined,
        {}
      )
      
  },     

  getSub(Sulae){
    if (Sulae != this.profile.nickname) {
      for (let i of this.subscribers) {
      let data = i.stream.connection.data
      let toJson = data.replace('}%/%{', ",")
      let tagger = JSON.parse(toJson)
      if (tagger.clientData == Sulae) {
        this.mainStreamManager = i
        break
      }
    }
    } else {
      this.mainStreamManager = this.publisher
      this.publisher.stream.audioActive = false
    }
    this.mainStreamManager.stream.audioActive = false
  }
  },
  }
  
  


</script>



<style>
svg {
  cursor: pointer;
  color: black;
  /* width: 40;
  height: 40;  */
}

.body-detail-bg-img {
  margin: auto;
  /* background-image: url("@/assets/12.png"); */
  
  /* background-size: cover; */
  width: 100vw;
  height: 100vh;
  /* position: absolute; */
  position: relative;
  z-index: 1;
  /* background-color: transparent; */

}

.body-detail-bg-img::after {
  margin: auto;
  width: 100vw;
  height: 100vh;
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;
  opacity: 0.6;
  content: "";
  /* background: url("@/assets/12.png"); */
  background-image : url("@/assets/body-game-bg.jpg");
  background-size: cover;
  
}

#join + #session {
  /* margin: auto; */
  /* width: 90%; */

  justify-content: center;
  align-items: center;
  /* width: 100%; */

  /* background-image: url("@/assets/bubble1.png");
  background-size: cover;
  width: 100vw;
  height: 100vh;
  position: absolute; */

  /* background-color: transparent; */
}

#body-detail-session-header {
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

.body-detail-back-title {
  display: flex;
  flex-flow: row wrap;
  justify-content: flex-start;
  align-self: center;

  background-color: transparent;
}

.body-detail-back-icon {
  /* width: 40%; */
}

.body-detail-icon-area {
  border: none;
  display: inline-block;
  width: 20%;
  height: 20%;

  background-color: transparent;
}

#body-detail-buttonLeaveSession {
  width: 10%;
  /* height: 15%; */
  display: block;
}

.body-detail-session-title {
  padding-left: 2%;
  font-size: 1rem;
  display: flex;
  align-self: center;
  text-align: center;

  justify-content : center;
	align-items : center;

  color: black;

}

.body-detail-total-time {
  /* padding-right: 6%; */
  font-size: 1rem;
  display: flex;
  align-self: center;
  text-align: center;
  
  justify-content : center;
	align-items : center;

  color: black;

}

#body-detail-session-header2 {
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

#body-detail-video-container {
  background-color: transparent;
  /* display: flex;
  flex-direction: row;
    float: left;
  align-self: center; */

  /* justify-content: center; */
    /* right: 0%; */
}

#body-detail-video-container video {
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

#body-detail-video-container video + div {
  
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

#body-detail-video-container p {
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

#body-detail-main-video p {
  /* position: absolute; */
  /* display: inline-block;
  background: transparent;
  padding-left: 5px;
  padding-right: 5px;
  font-size: 2.6rem; */
  color: white;
  font-weight: bold;
  /* border-radius: 5px; */
  display: flex;
  flex-flow: column wrap;
}

user-video {
    /* margin-left: 20px; */
    /* width: 200;
    height: 180; */

}

#body-detail-main-video {
  width: 24vw;
  height: 38vh;
  /* width: 73%; */
  display: flex;
  justify-content: center;
  align-items: center;

  background-color: transparent;
  
  border: 0.5rem;
  border-style: solid;
  border-radius: 10% 10% 10% 10%;
  border-color: white;
  background-color: black;

}

/* .left-right {
    flex-flow: row wrap;
    justify-content: space-around;
} */

.body-quiz-limit-timer {
  font-size: 1.6rem;
  font-weight: bolder;
  color: black;
  /* height: 16%; */
  display: flex;
  justify-content: center;
  align-items: center;
  
}

#body-detail-session-body-left {
  /* margin-left */
  padding-left: 3%;  
  /* padding-right: 3%;  */
  padding-top: 1%;  
  display: flex;
  flex-direction: column;
  float: left;
  justify-content: center;
  align-self: center;
  align-items: center;
  
  background-color: transparent;
}

#the-answer {
  font-size: 1.8rem;
  color: white;
}

#difficulty {
  font-size: 1.8rem;
  color: white;

}

#main-video-rank-table {
  color: white;
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

img {
  /* width : 4vw;
  height : 6.7vh; */
}

.body-detail-answer-send {
  padding-top: 2%;
  display: flex;
  flex-flow: row;
  /* align-self: center; */
  justify-content: center;
  align-items: center;
  margin: auto;
  width: 68%;
    
}

.body-joker-quiz {
  padding-top: 1%;
  font-size: 3rem;
  /* height: 80%; */
  display: flex;
  flex-flow: column;
  justify-content: center;
  align-items: center;

  font-weight: bolder;
  color: black;

  
}



#body-detail-answer-sheet {
    /* border: 1; */
    border: 3px solid black;
    font-size: 2rem;
    width: 88%;
}

.body-detail-check-answer {
    /* border: 1; */
    /* border: 1px dotted black; */
    /* text-align: center; */
    /* margin: auto; */

    /* background-color: transparent;
    border: none;
    background: transparent; */

    /* justify-content: center;
    align-self: center;
    font-size: 1.6rem;
    color: red;
    font-style: italic; */

}

.body-correct-answer {
  justify-content: flex-start;
  align-self: center;
  font-size: 2rem;
  color: red;

}

.body-incorrect-answer {
  justify-content: flex-start;
  align-self: center;
  font-size: 1rem;
  color: blue;
  /* font-style: italic; */

}



#body-detail-session-body-right {
  /* padding-left: 3%;  
  padding-right: 3%;  
  padding-top: 1%;   */
  margin-right: 3%;
  padding-top: 1%;
  /* padding-right: 5%; */
  display: flex;
  flex-direction: column;
  float: right;
  justify-content: center;
  align-self: center;    

  background-color: transparent;
}

#body-right-card {
  /* background-color: transparent;
  border-color: #ffff; */
  background-color: rgba(60, 60, 60, 0.26 );
  color: #ffff;
  border: none;
}

.body-detail-rank {
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
}



.body-detail-leader-button {
    /* font-size: 2rem; */
    text-align: center;
    /* justify-content: center; */
    /* margin-top: 3%;
    margin-bottom: 3%; */
}

#start-btn {
  /* border: 1rem solid; */
  font-size: 3rem;
  font-weight: bolder;
  text-align: center;
  background-color: #f1c4d9;
  color: #ffff;
  border: none;
  
}

.body-detail-start-box {
  /* width: 150%; */
   /* margin: 0 auto 2.5vh; */

   /* position: relative;
   width: 33vw;
   height: 48vh;
   background: rgba(192, 192, 199, 0.47);
   border: 3px solid white;
   border-radius:20px; */
  position: fixed;
  bottom: 2%;
  display:flex;
  justify-content: center;
  align-items: center;
  align-self: center;
}

.body-start-box-timer {
  align-self: center;
  /* float: right; */
  text-align: center;
  /* margin-top: 5%;
  margin-bottom: 3%; */
  font-size: 3rem;   

  justify-content : center;
	align-items : center;
    
}

.body-start-box-timer h1 {
  /* color: rgb(166, 122, 0); */
  color: rgb(166, 122, 0);
  font-size: 3rem;
  font-weight: 900;
}

</style>
