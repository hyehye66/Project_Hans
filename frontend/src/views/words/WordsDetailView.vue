<template>
    <div id="session">
        <div id="session-header">
            <div class="back-title">
                <svg class="w-6 h-6" id="buttonLeaveSession" @click="leaveSession" value="Leave session" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 15l-3-3m0 0l3-3m-3 3h8M3 12a9 9 0 1118 0 9 9 0 01-18 0z">
                    </path>
                </svg>
            </div>
            <h1 id="session-title">{{ this.$route.params.sessionName }}</h1>
            <div class="total-time">
        <!--  h-30 w-40 p-2 border-2 border-gray-400 bg-gray-200 -->
            <div class="h-full w-full bg-gray-400">
                    <h1>총 진행시간 {{ trigger }} </h1>
                </div>
            </div>
        </div>
        <div id="session-header2" style="width: 100%;">
            <!-- 방안사람들 -->
            <div id="video-container" class="col-lg12">
                <WordsDetailRTCItem :stream-manager="publisher" @click.enter="updateMainVideoStreamManager(publisher)"/>
                <WordsDetailRTCItem v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click="updateMainVideoStreamManager(sub)"/>
            </div>
        </div>
        <div id="session-body-left" class="col-md-6">
            <!-- 메인화면 -->
            <div id="problem-box">
                <div>게임 시간 : {{this.$store.state.games.TimerStr}} 초</div> 
                <div v-if="!cnt">문제는 : {{ problem }} </div>
            </div>
            <!-- 캠,마이크,나가기,설정 -->
            <div class="cam-buttons">
                <!-- style="height: 100%" -->
                <div class="icon-area" @click='muteVideo'>
                    <VideoCameraIcon style="height: 40; width: 40;" />
                </div>
                <div class="icon-area" @click='muteAudio'>
                    <!-- <MicrophoneIcon style="height: 40; width: 40;"/>							 -->
                    <div v-if='!publisher.stream.audioActive'>
                        <h1>음소거제거</h1>
                        <!-- <img src="@/assets/microphone1.png" alt="mic"> -->
                    </div>
                    <div v-else>
                        <h1>음소거</h1>
                        <!-- <img src="@/assets/microphone2.png" alt="mic"> -->
                    </div>
                </div>
                <div class="icon-area" @click="leaveSession">
                    <LogoutIcon style="height: 40; width: 40;"/>
                </div>
                <!-- 설정모달창 띄우는 방법 생각하기 -->
                <div class="icon-area" @click="isOpen">
                    <CogIcon style="height: 40; width: 40;"/>
                </div>
            </div>
        </div>

        <div id="session-body-right" class="col-md-5">
            <!-- 랭크 -->
            <h1>랭크</h1>
            <div class="rank col-md-12">
            <!--  shadow-md py-60 px-50 -->						
                <ul>
                    <li>김민철 1</li>
                    <li>김지현 2</li>
                    <li>김지현 2</li>
                    <li>김지현 2</li>
                    <li>김지현 2</li>
                    <li>김지현 2</li>
                </ul>
            </div>
            <!-- <br> -->
            <!-- 현재 문제 남은 시간 타이머 -->
            <div class="problem-timer" style="width: 30%">남은 시간: 
            <div v-if="cnt">{{threecount}}</div></div>
            <!-- style="width: 40; height: 40;" -->
            <!-- 임시시작버튼 -->
            <div v-if="!start" class="leader-button">
                <button @click="sendStart" class="bg-transparent hover:bg-blue-500 text-blue-700 font-semibold hover:text-white py-2 px-2 border border-blue-500 hover:border-transparent rounded-full">
                    START
                </button>
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

                <div class="answer-send">
                    <input type="text" name="" id="answer-sheet" v-model="temp" size="30"
                    placeholder="답을 입력해주세요." @keyup.enter="sendAnswer" v-if="!isCorrect"/>
                    <PaperAirplaneIcon style="height: 35; width: 35;" @click="sendAnswer" />					
                </div>
                <!-- 정오답 알림 메시지 -->
                

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
    VideoCameraIcon,
    //MicrophoneIcon,
    LogoutIcon,
    CogIcon,
    PaperAirplaneIcon,
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
        problemNum : 1,
        temp : '',
        currentRank : {},
        trigger : true,
        playerLen : 0,
        isAll : false,
        isCorrect : false
    }
  },
  
  created(){
    this.joinSession(),
    this.socketStart()
    console.log(this.$route.params)
  },

  computed : {
    ...mapGetters(['authHeader','profile'])
  },
//this.$route.params.token.slice(39,53)
  methods : {
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
            this.stompClient.send(`/game/word-game/room/${this.$route.params.roomSequence}/owner`, undefined, {})
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
    setTimeout(() => {this.threecount = 3}, 1000)
    setTimeout(() => {this.threecount = 2}, 2000)
    setTimeout(() => {this.threecount = 1}, 3000)
    setTimeout(() => {this.threecount = 'start!'}, 4000)
    setTimeout(() => { this.cnt=false }, 4500)
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
      console.log(this.answerList)
      this.stompClient.connect({}, frame => {
          console.log(frame, '연결 성공!')
          this.stompClient.subscribe(`/topic/word-game/${this.$route.params.roomSequence}`, 
          res => {
              const response = JSON.parse(res.body)
              let key = Object.keys(response) 
              if ("gameStatus" === key[1]){
                  this.status = true
                  this.playerLen = response.players.length
                  for (let i of response.players) {
                    this.currentRank[`${i}`] = 0
                  }
                  console.log(this.currentRank)
              } else if (key[0] === 'problem') {
                  this.problem = response.problem
              } else if (key[0] === 'roomSequence') {
                  this.answer = response.answer
                  this.answerList = response.correctPlayers
                  for (let i of this.answerList) {
                    this.currentRank[`${i}`] += response.point
                  }
                  console.log(this.currentRank)
                  this.answerList= []
                  this.isCorrect = false
              } else if (key[0] === 'players') {
                  this.difficulty = response.points
              } else if (key[0] == 'correctPlayers') {
                this.answerList = response.correctPlayers
                console.log(this.answerList, '첫번째문제 answerList진짜 못보냐?')
                console.log(this.answerList.length)
                for(let i of this.answerList){
                  if (i === this.profile.nickname){
                    this.isCorrect = true
                    break
                  }
                }
              }
          })
            
      },
      )
    },
  sendStart(){
    console.log('보낼거임')
    this.start = true
    this.cnt = true
    const gameStatus = {
        total_question : 10
    }
    this.stompClient.send(`/game/word-game/${this.$route.params.roomSequence}`, JSON.stringify(gameStatus), {})
    this.threecountDown()
  },

  getProblem(){
    this.trigger = true
    this.stompClient.send(
        `/game/word-game/room/${this.$route.params.roomSequence}/problem/${this.problemNum}`, undefined, {}
    )
    this.setCorrect()
    
    },
      
  problemTrigger(){
    return new Promise(resolve => 

    setTimeout(() => {this.trigger = false; resolve(this.trigger)}, 15000)

    )
  },
  async setCorrect(){
    const result = await this.problemTrigger()
    //await this.allCorrect() 인터벌 (얼코렉트)
    let result2 = await this.allCorrect()
    if (!result || result2) {
      // this.$store.state.games.TimerChk = true
      this.sendCorrect()
    } 
    // if (result2) {console.log('!!')}
  },
  allCorrect(){
    let interval = setInterval(() => {
      console.log('정답자 수 : '+ this.answerList.length)
      console.log('참여자 수 : '+ this.playerLen)
      if (this.answerList.length == this.playerLen){
        this.$store.state.games.TimerChk = true
        clearInterval(interval)
        return true
      }
      
    }, 1000)
   
  },
 

  sendAnswer(){
    const foranswer = {
        player : this.profile.nickname,
        submit : this.temp,
        problem_num : this.problemNum
    }
    const submit = JSON.stringify(foranswer)
    this.stompClient.send(
        `/game/word-game/check/${this.$route.params.roomSequence}`, submit, {}
    )
    this.temp = ''
    },

  sendCorrect(){
        const questionNum = {question_num : this.problemNum} 
        this.stompClient.send(`/game/word-game/answer/${this.$route.params.roomSequence}`,
        JSON.stringify(questionNum), {}
        )
        this.problemNum++
        console.log(this.problemNum, '몇개 불러오는거임?')
        if (this.problemNum <= 10){
          console.log('결과까지!')
          setTimeout(() => {this.threecountDown()}, 3000)
        } else if (this.problemNum > 10) {
          this.sendResult()
        }
    },

  sendResult(){
    this.stompClient.send(
        `/game/word-game/result/${this.$route.params.roomSequence}`,
        undefined,
        {}
      )
  },
  
  }
  } 

</script>

<style scoped>
svg {
  cursor: pointer;	
}

#main-container {
	margin: auto;
}

#join + #session {
	justify-content: center;
	width: 100%;


}

#session-header {
	width: 100%;
	display: flex;
  flex-flow: row wrap;
	justify-content: space-between;
  align-self: center;

}

.icon-area {
  border: none;
  display: inline-block;
	width: 40;
	height: 40;
}

/* svg {
	width: 6;
	height: 6;
} */

#buttonLeaveSession {
  width: 30px;
  height: 30px;
  display: block;
}

#session-header2 {
	display: flex;
	/* flex-direction: row;
	flex-wrap: wrap; */
	flex-flow: row wrap;
	justify-content: space-between;
	/* justify-content: center; */
	align-self: center;
	/* right: 0%; */
	/* margin: 0 2.2vw;
  padding: 2vh; */
}

#video-container {
  /* display: flex;
  flex-direction: row;
	float: left;
  align-self: center; */

  /* justify-content: center; */
	/* right: 0%; */
}

#video-container video {
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
}

#video-container video + div {
  
  text-align: center;
  /* line-height: 75px; */
   float: left;
   width: 28%;
   position: relative;
   margin-left:-28.5%;
   /* display: flex; */
   /* justify-content: space-around; */
}

#video-container p {
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
   
   padding-top: 1vh;
   /* 맨 아래에 나오는 카메라화면 */
   /* width: ; */
    width: 100%;
   /* height: 48vh; */
   height: auto;
   position: relative;
}

#main-video p {
  /* position: absolute; */
  display: inline-block;
  background: #f8f8f8;
  padding-left: 5px;
  padding-right: 5px;
  font-size: 22px;
  color: #777777;
  font-weight: bold;
  border-radius: 5px;
}

user-video {
	/* margin-left: 20px; */
	/* width: 200;
	height: 180; */

}

#main-video {
	/* justify-content: center; */

}


/* VideoCameraIcon {
	width: 6;
	height: 6;
} */
/* MicrophoneIcon
LogoutIcon
CogIcon
PaperAirplaneIcon */

/* .left-right {
	flex-flow: row wrap;
	justify-content: space-around;
} */

#session-body-left {
	display: flex;
	flex-direction: column;
	float: left;
	justify-content: center;
	align-self: center;
	
}

.cam-buttons {
	width: 60%;
	margin: 0 auto;

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
  width : 4vw;
  height : 6.7vh;
}


#session-body-right {
	display: flex;
	flex-direction: column;
	float: right;
	/* padding-top: 5px; */
	margin-top: 10px;
	justify-content: center;
	align-self: center;	
}

.rank {
	border: thick double #32a1ce;
	padding-left: 5px;
  padding-right: 5px;
	/* width: 20rem; */
	height: auto;
	/* text-align: center; */
	justify-content: center;
	/* margin: auto; */
	font-size: 25px;
}

.problem-timer {
	align-self: center;
	/* float: right; */
	text-align: center;
	margin-top: 5%;
	margin-bottom: 3%;
	/* width: 50 !important;
	height: 50 !important; */
	/* border: 1px dotted black;
	border-radius: 100% 100% 100% 100%; */
	font-size: 30px;
	
}

.leader-button {
	font-size: 40px;
	text-align: center;
	/* justify-content: center; */
	margin-top: 3%;
	margin-bottom: 3%;
}

.leader-button button {
	width: 30%;
}

.start-box {
   position: relative;
   width: 33vw;
   height: 48vh;
   /* width: 150%; */
   background: rgba(192, 192, 199, 0.47);
   border: 3px solid white;
   border-radius:20px;
   /* margin: 0 auto 2.5vh; */
   display:flex;
   justify-content: center;
   align-items: center;
}

.answer-send {
	display: flex;
	flex-direction: row;
	/* align-self: center; */
	justify-content: center;
	margin: auto;
	
}

#answer-sheet {
	/* border: 1; */
	border: 1px dotted black;
	font-size: 30px;
}

.check-answer {
	/* border: 1; */
	/* border: 1px dotted black; */
	/* text-align: center; */
	justify-content: center;
	/* margin: auto; */
	align-self: center;
	font-size: 30px;
	color: red;
	font-style: italic;

}
</style>