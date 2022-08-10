<template>
	<div id="main-container" class="container">
		<div id="join" v-if="!session">
			<div id="img-div"><img src="resources/images/openvidu_grey_bg_transp_cropped.png" /></div>
			<div id="join-dialog" class="jumbotron vertical-center">
				<h1>Join a video session</h1>
				<div class="form-group">
					<p>
						<label>Participant</label>
						<input v-model="myUserName" class="form-control" type="text" required>
					</p>
					<p>
						<label>Session</label>
						<input v-model="mySessionId" class="form-control" type="text" required>
					</p>
					<p class="text-center">
						<button class="btn btn-lg btn-success" @click="joinSession()">Join!</button>
					</p>
				</div>
			</div>
		</div>

		<div id="session" v-if="session">
			<div id="session-header">
				<!-- <h1 id="session-title">{{ mySessionId }}</h1>
				<input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="Leave session"> -->
				<div class="back-title">
					<!-- 뒤로가기 -->
					<!-- <div class="icon-area"> -->
						<svg class="w-6 h-6" id="buttonLeaveSession" @click="leaveSession" value="Leave session" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
							<path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 15l-3-3m0 0l3-3m-3 3h8M3 12a9 9 0 1118 0 9 9 0 01-18 0z">
							</path>
						</svg>
					<!-- </div> -->
					<!-- <input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="Leave session">				 -->
					<!-- <button class="bg-transparent hover:bg-blue-500 text-blue-700 font-semibold hover:text-white py-2 px-2 border border-blue-500 hover:border-transparent rounded-full">
						back
					</button> -->
					<!-- 방 제목 issue! -->
					<!-- {{ BodyRoomCreateModal.roomTitle }} -->
				</div>


				<!-- 총 진행시간 -->
				<div class="total-time">
				<!--  h-30 w-40 p-2 border-2 border-gray-400 bg-gray-200 -->
					<div class="h-full w-full bg-gray-400">
						<h1>총 진행시간</h1>
					</div>
				</div>
			</div>


			<div id="session-header2" style="width: 100%;">
				<!-- 방안사람들 -->
				<div id="video-container" class="col-lg-12">
					<!-- col-md-3  -->
					<!-- updateMainVideoStreamManager(publisher) : 
						현재의 publisher(나)를 mainstreamer로 지정하여 미팅방 최상단에 보이게 하겠다 -->
					<user-video :stream-manager="publisher" @click.enter="updateMainVideoStreamManager(publisher)"/>
					<user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click.enter="updateMainVideoStreamManager(sub)"/>
				</div>
			</div>				


			<!-- <div class="left-right"> -->
				<div id="session-body-left" class="col-md-6">
					<!-- 메인화면 -->
					<div id="main-video">
						<user-video :stream-manager="mainStreamManager"/>
					</div>
					<!-- 캠,마이크,나가기,설정 -->
					<div class="cam-buttons">
						<!-- style="height: 100%" -->
						<div class="icon-area" @click='muteVideo'>
							<VideoCameraIcon style="height: 40; width: 40;" />
							<!-- <div v-if='publisher.stream.videoActive'>
								<img src="@/assets/videocamera1.png" alt="cam">
							</div>
							<div v-else>
								<img src="@/assets/videocamera2.png" alt="cam">
							</div> -->
						</div>
						<div class="icon-area" @click='muteAudio'>
							<!-- <MicrophoneIcon style="height: 40; width: 40;"/>							 -->
							<div v-if='publisher.stream.audioActive'>
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
					<div class="problem-timer" style="width: 30%">남은 시간</div>
					<!-- style="width: 40; height: 40;" -->
					<!-- 임시시작버튼 -->
					<div class="leader-button">
						<button class="bg-transparent hover:bg-blue-500 text-blue-700 font-semibold hover:text-white py-2 px-2 border border-blue-500 hover:border-transparent rounded-full">
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


					<!-- 출제자한테는 문제카드 띄울 예정 -->
					<!-- <div v-if="myUserNick === roominfo.ownerNicknames" class="body-quiz-card col-md-9">
						<h1>문제카드</h1>
					</div> -->
					<!-- 출제자가 아닐 때 -->
					<!-- <div v-else> -->
						<!-- 정답입력란 -->
						<div class="answer-send">
							<input type="text" name="" id="answer-sheet" v-model="answerSheet" size="30"
							placeholder="답을 입력해주세요." @keyup.enter="checkAnswer" />
							<PaperAirplaneIcon style="height: 35; width: 35;" @click="checkAnswer" />					
						</div>
						<!-- 정오답 알림 메시지 -->
						<div class="check-answer">
							<input type="text" v-model="answerAlert" size="30" />
						</div>
					<!-- </div> -->
				</div>
		</div>
	</div>

	<BodyRoomUpdateModal v-model:open="open" />

</template>

<script>
/* eslint-disable */
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '@/components/UserVideo';
import BodyRoomUpdateModal from '@/views/modal/components/BodyRoomUpdateModal.vue';
// import { CogIcon } from '@heroicons/vue/solid';
import { VideoCameraIcon, MicrophoneIcon, LogoutIcon, CogIcon, PaperAirplaneIcon } from '@heroicons/vue/outline';

axios.defaults.headers.post['Content-Type'] = 'application/json';
const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";
export default {
	name: 'BodyDetailView',
	components: {
		UserVideo,
		BodyRoomUpdateModal,

		VideoCameraIcon,
		MicrophoneIcon,
		LogoutIcon,
		CogIcon,
		PaperAirplaneIcon,

	},
	data () {
		return {
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],
			mySessionId: 'SessionA',
			myUserName: 'Participant' + Math.floor(Math.random() * 100),
			// 방이름
			detailTitle: '',

			// 게임 시작 관련
			start: false,
			ready: false,

			// 답입력값
			answerSheet: '',
			answerAlert: '',
			gameStatus: 0,
			round: 0,

			open : false,
		}
	},

	props: {
    // publisher: Object,
    // roominfo: Object,
    // session: Object,
    // myUserNick: String,
  },

	methods: {
		joinSession () {
			// --- Get an OpenVidu object ---
			this.OV = new OpenVidu();
			// --- Init a session ---
			this.session = this.OV.initSession();
			// --- Specify the actions when events take place in the session ---
			// On every new Stream received...
			this.session.on('streamCreated', ({ stream }) => {
				const subscriber = this.session.subscribe(stream);
				this.subscribers.push(subscriber);
			});
			// On every Stream destroyed...
			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.subscribers.splice(index, 1);
				}
			});
			// On every asynchronous exception...
			this.session.on('exception', ({ exception }) => {
				console.warn(exception);
			});
			// --- Connect to the session with a valid user token ---
			// 'getToken' method is simulating what your server-side should do.
			// 'token' parameter should be retrieved and returned by your own backend
			this.getToken(this.mySessionId).then(token => {
				this.session.connect(token, { clientData: this.myUserName })
					.then(() => {
						// --- Get your own camera stream with the desired properties ---
						let publisher = this.OV.initPublisher(undefined, {
							audioSource: undefined, // The source of audio. If undefined default microphone
							videoSource: undefined, // The source of video. If undefined default webcam
							publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
							publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
							resolution: '640x480',  // The resolution of your video
							frameRate: 30,			// The frame rate of your video
							insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
							mirror: false       	// Whether to mirror your local video or not
						});
						this.mainStreamManager = publisher;
						this.publisher = publisher;
						// --- Publish your stream ---
						this.session.publish(this.publisher);
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});
			});
			window.addEventListener('beforeunload', this.leaveSession)
		},
		leaveSession () {
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			if (this.session) this.session.disconnect();
			this.session = undefined;
			this.mainStreamManager = undefined;
			this.publisher = undefined;
			this.subscribers = [];
			this.OV = undefined;
			window.removeEventListener('beforeunload', this.leaveSession);
		},
		updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},
		/**
		 * --------------------------
		 * SERVER-SIDE RESPONSIBILITY
		 * --------------------------
		 * These methods retrieve the mandatory user token from OpenVidu Server.
		 * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
		 * the API REST, openvidu-java-client or openvidu-node-client):
		 *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
		 *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
		 *   3) The Connection.token must be consumed in Session.connect() method
		 */
		getToken (mySessionId) {
			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},
		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
		createSession (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
						customSessionId: sessionId,
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.id))
					.catch(error => {
						if (error.response.status === 409) {
							resolve(sessionId);
						} else {
							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response);
						}
					});
			});
		},
		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
		createToken (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
			});
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

		},


	// 정오답 확인
	checkAnswer() {

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