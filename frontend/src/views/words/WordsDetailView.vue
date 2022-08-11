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
                    <h1>총 진행시간</h1>
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
            <div id="main-video">
                <WordsDetailRTCItem :stream-manager="mainStreamManager"/>
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
          <button class="btn btn-primary" @click="getProblem(problemNum)">문제 줄 거임</button>
          <button class="btn btn-primary" @click="sendCorrect"> 정답 줄거임</button>
          <button class="btn btn-primary" @click="sendResult">결과 줄거임</button>
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
            <div v-if="cnt">{{count}}</div></div>
            <!-- style="width: 40; height: 40;" -->
            <!-- 임시시작버튼 -->
            <div v-if="!start" class="leader-button">
                <button @click="sendStart(this.$route.params.roomSequence)" class="bg-transparent hover:bg-blue-500 text-blue-700 font-semibold hover:text-white py-2 px-2 border border-blue-500 hover:border-transparent rounded-full">
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
                    <input type="text" name="" id="answer-sheet" v-model="answer" size="30"
                    placeholder="답을 입력해주세요." @keyup.enter="sendAnswer" />
                    <PaperAirplaneIcon style="height: 35; width: 35;" @click="sendAnswer" />					
                </div>
                <!-- 정오답 알림 메시지 -->
                <div class="check-answer">
                    <input type="text" v-model="answerAlert" size="30" />
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
        temp : ''
    }
  },
  
  created(){
    this.joinSession(),
    this.socketStart(),
    console.log(this.profile)
  },

  computed : {
    ...mapGetters(['authHeader','profile'])
  },
//this.$route.params.token.slice(39,53)
  methods : {
    
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
            // 일단 axios로 스프링 서버의 방에서 나가기

            axios({
                url : `/api/word-game/rooms/${this.$route.params.roomSequence}`,
                method : 'delete',
                headers : this.authHeader
            })

            .then(() =>{
            if(this.session) {this.session.disconnect();}


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

	gameStart() {
      this.start = true
      this.cnt = true
      this.countDown()

		},
  countDown()  {
      setTimeout(() => { this.count = 4 }, 1000)
      setTimeout(() => { this.count = 3 }, 2000)
      setTimeout(() => { this.count = 2 }, 3000)
      setTimeout(() => { this.count = 1 }, 4000)
      setTimeout(() => { this.count = 'START!!!!'}, 4500)
      setTimeout(() => { this.cnt=false}, 4800)
      this.count = 5
    },
  
  // stomp 시작
  socketStart(){
      let socket = new SockJS(serverUrl)
      this.stompClient = Stomp.over(socket)
      console.log('소켓 연결하는 중')
      this.stompClient.connect({}, frame => {
          console.log(frame, '연결 성공!')
          this.stompClient.subscribe(`/topic/word-game/${this.$route.params.roomSequence}`, 
          res => {
              console.log(res)
              const response = JSON.parse(res.body)
              console.log(Object.keys(response)[0])
              let key = Object.keys(response)[0]
              if (key ==="gameStatus"){
                  this.status = true
              } else if (key === 'problem') {
                  this.problem = response.problem
              } else if (key === 'roomSequence') {
                  this.answer = response.answer
              } else if (key === 'players') {
                  this.difficulty = response.points
              }
          })
            
      },
      )
    },
  sendStart(roomSequence){
    console.log('보낼거임')
    this.start = true
    this.cnt = true
    this.countDown()
    const gameStatus = {
        total_question : 10
    }
    this.stompClient.send(`/game/word-game/${roomSequence}`, JSON.stringify(gameStatus), {}) 
  },
   getProblem(problemNum){
      this.stompClient.send(
          `/game/word-game/room/${this.$route.params.roomSequence}/problem/${problemNum}`, undefined, {}
      )
  },
  sendAnswer(){
    console.log(this.profile.nickname)
    const foranswer = {
        player : this.profile.nickname,
        submit : this.answer
    }
    const submit = JSON.stringify(foranswer)
    this.stompClient.send(
        `/game/word-game/check/${this.$route.params.roomSequence}`, submit, {}
    )},
  sendCorrect(){
            const questionNum = {question_num : 1} 
            this.stompClient.send(`/game/word-game/answer/${this.$route.params.roomSequence}`,
            JSON.stringify(questionNum), {}
            )
        },
  sendResult(){
    this.stompClient.send(
        `/game/word-game/result/${this.$route.params.roomSequence}`,
        undefined,
        {}
      )
  }
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