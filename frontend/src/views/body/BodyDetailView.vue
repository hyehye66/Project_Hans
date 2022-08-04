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
				<!-- 내 세션아이디 -->
				<!-- <h1 id="session-title">{{ mySessionId }}</h1> -->
				<!-- <input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="Leave session"> -->
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
					<!-- 방 제목 issue!!! -->
					<!-- <body-detail-view v-bind:detailTitle="body-room-create-modal.roomTitle" /> -->
					<!-- <body-room-create-modal v-bind:roomTitle="bodyRoomTitle" /> -->
					<!-- {{ BodyRoomCreateModal.roomTitle }} -->
				</div>


				<!-- 총 진행시간 -->
				<div class="box-border h-20 w-32 p-2 border-2 border-gray-400 bg-gray-200">
					<div class="h-full w-full bg-gray-400">
						<h1>총 진행시간</h1>
					</div>
				</div>
			</div>
			<br>

			<div id="session-header2">
				<!-- 방안사람들 -->
				<div id="video-container" class="col-md-3">
					<!-- updateMainVideoStreamManager(publisher) : 
					현재의 publisher(나)를 mainstreamer로 지정하여 미팅방 최상단에 보이게 하겠다 -->
					<user-video :stream-manager="publisher" @click.enter="updateMainVideoStreamManager(publisher)"/>
					<user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click.enter="updateMainVideoStreamManager(sub)"/>
				</div>

				<!-- 현재 문제 남은 시간 타이머 -->
				<div class="problem-timer" style="width: 40; height: 40;">남은 시간</div>
			</div>
			<br>

			<div class="left-right">

				<div id="session-body-left">
					<!-- 메인화면 -->
					<div id="main-video" class="col-md-6">
						<user-video :stream-manager="mainStreamManager"/>
					</div>
					<!-- 캠,마이크,나가기,설정 -->
					<div class="cam-buttons">
						<!-- style="height: 100%" -->
						<div class="icon-area">
							<VideoCameraIcon style="height: 40; width: 40;" />
						</div>
						<div class="icon-area">
							<MicrophoneIcon style="height: 40; width: 40;"/>
						</div>
						<div class="icon-area">
							<LogoutIcon style="height: 40; width: 40;"/>
						</div>
						<div class="icon-area">
							<CogIcon style="height: 40; width: 40;"/>
						</div>
						<!-- h-6 w-6 text-blue-500 -->
						
						<!-- test -->
						<!-- <div class="icon-area">
							<svg class="w-6 h-6" id="buttonLeaveSession" @click="leaveSession" value="Leave session" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
								<path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 15l-3-3m0 0l3-3m-3 3h8M3 12a9 9 0 1118 0 9 9 0 01-18 0z">
								</path>
							</svg>
						</div> -->
						
						<!-- <button class="bg-transparent hover:bg-blue-500 text-blue-700 font-semibold hover:text-white py-2 px-4 border border-blue-500 hover:border-transparent rounded-full">
							캠
						</button> -->
						<!-- <div class="icon-area">
							<VideoCameraIcon class="h-6 w-6 text-blue-500"/>
						</div> -->

						<!-- <button class="bg-transparent hover:bg-blue-500 text-blue-700 font-semibold hover:text-white py-2 px-4 border border-blue-500 hover:border-transparent rounded-full">
							마이크
						</button> -->
						<!-- <div class="icon-area">
							<MicrophoneIcon class="h-6 w-6 text-blue-500"/>
						</div> -->

						<!-- <button class="bg-transparent hover:bg-blue-500 text-blue-700 font-semibold hover:text-white py-2 px-4 border border-blue-500 hover:border-transparent rounded-full">
							나가기
						</button> -->
						<!-- <div class="icon-area">
							<LogoutIcon class="h-6 w-6 text-blue-500"/>
						</div> -->

						<!-- <button class="bg-transparent hover:bg-blue-500 text-blue-700 font-semibold hover:text-white py-2 px-4 border border-blue-500 hover:border-transparent rounded-full">
							설정
						</button> -->
						<!-- <div class="icon-area">
							<CogIcon class="h-6 w-6 text-blue-500"/>						
						</div> -->
					</div>

					<!-- 정답입력란 -->
					<!-- <div class="answer-send">
						<input type="text" name="" id="answer-sheet" v-model="answerSheet" size="40">
						<PaperAirplaneIcon style="height: 30; width: 30;"/>					 -->
					<!-- <div class="icon-area">										
						<PaperAirplaneIcon class="h-6 w-6 text-blue-500"/>					
					</div> -->
					<!-- <button class="bg-transparent hover:bg-blue-500 text-blue-700 font-semibold hover:text-white py-2 px-2 border border-blue-500 hover:border-transparent rounded-full">
						>
					</button> -->
					<!-- </div> -->

					<!-- 답 입력창 -->
					<!-- 출제자 일때 -->
					<div v-if="myUserNick === mainStreamManager_nickname">
						<div class="card2">
								<p>키워드:</p>&nbsp;{{ BodyDetailRTC.keyword }}
						</div>
					</div>
					<!-- 출제자가 아닐 때 -->
					<div v-else>
						<div class="answer-send">
								<input v-model="answerSheet" class="answer-sheet" placeholder="답을 입력해주세요." type="text" @keyup.enter="check_answer"/>
								<PaperAirplaneIcon style="height: 30; width: 30;"/>
						</div> 
					</div>

				</div>

				<div id="session-body-right">
					<!-- 랭크 -->
					<div class="rank shadow-md py-60 px-50">
						<h2>랭크</h2>
					</div>
					<!-- 시작버튼 -->
					<div v-if="!start && !ready">
							<div class="main_box_2">
								<!-- 방장만 스타트 버튼 보이기 -->
								<div v-if='myUserNick === roominfo.ownerNicknames'>
										<div @click="game_start">
											<Start />
										</div>
								</div>
								<!-- 방장 아닌 사람은 준비중 -->
								<div v-else>
										<p>준비중</p>
								</div>
							</div>
					</div>
					
					<!-- 321 -->
					<div v-else-if="!ready && start ">
							<Ready />
					</div>
					<!-- <button class="bg-transparent hover:bg-blue-500 text-blue-700 font-semibold hover:text-white py-2 px-2 border border-blue-500 hover:border-transparent rounded-full">
						START
					</button> -->
					<!-- 정오답 알림 메시지 -->
					<input type="text" v-model="answerAlert" />
				</div>
			</div>


			
		</div>
	</div>
</template>

<script>
/* eslint-disable */
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '@/components/UserVideo';
import BodyRoomCreateModal from '@/views/modal/components/BodyRoomCreateModal.vue';
// import { CogIcon } from '@heroicons/vue/solid';
import { VideoCameraIcon, MicrophoneIcon, LogoutIcon, CogIcon, PaperAirplaneIcon } from '@heroicons/vue/outline';

axios.defaults.headers.post['Content-Type'] = 'application/json';
const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";
export default {
	name: 'BodyDetailView',
	components: {
		UserVideo,
		BodyRoomCreateModal,

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
			// 나
			publisher: undefined,
			// 나 이외 방 안 사람들
			subscribers: [],
			mySessionId: 'SessionA',
			myUserName: 'Participant' + Math.floor(Math.random() * 100),
			// 방이름
			detailTitle: '',

			start: false,
			ready: false,

			// 답입력값
			answerSheet: '',
			gameStatus: 0,
			picture: false,

		}
	},

	// created () {
	// 	// 스타트 버튼이 눌렸다는 신호가 오면 사람들한데도 알려줌
	// 	this.session.on('signal:start-btn', () => {
	// 			this.start = true
	// 			setTimeout(() => {
	// 				this.ready = true;
	// 				// this.picture = true
	// 				}, 3600);
					
	// 			setTimeout(() => {
	// 				this.ready = true;
	// 				// this.picture = false
	// 				}, 3900);
	// 			this.gameStatus = 1
	// 	})

	// },

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

		game_start() {
			// ----------------시작 버튼 누르고-------------------
			this.session.signal({
				data: JSON.stringify(this.ready),
				type: 'start-btn'
			})
			.then(() => {
				console.log('시작버튼')
			})
			.catch(err => {
				console.log(err)
			})
			// ----------------게임 시작할 때 참가자들 한테 시그널 보내기-------------------
			this.session.signal({
				data: JSON.stringify({
					// 게임 상태 (게임시작)
					"gameStatus": 0, 
					//라운드
					// "round": 0, 
					//방 id
					// "conferenceId": this.$route.params.roomid,
					//토큰
					// "JWT":this.$store.state.accessToken
				}),
		// 		type: 'game'
		// 	})
		// 	.then(() => {
				
		// 	})
		// 	.catch(error => {
		// 		console.log(error);
		// 	})
		// },
		// //  ----------------답 입력 맞추기---------------
		// check_answer() {

		// },
	}
	}}
}
</script>

<style>

#session-header {
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
}

#video-container {
  display: flex;
  flex-direction: row;
	float: left;
  /* justify-content: center; */
  align-self: center;
	/* right: 0%; */
}

/* user-video {
	width: 200;
	height: 180;
} */

.problem-timer {
	align-self: center;
	/* float: right; */
	/* width: 50 !important;
	height: 50 !important; */
	/* border: 1px dotted black;
	border-radius: 100% 100% 100% 100%; */
	
}

#main-video {
	/* justify-content: center; */
}

#cam-buttons {
	display: flex;
	/* flex-direction: row; */
  flex-flow: row wrap;
	justify-content: space-evenly;
  align-self: center;
	/* width: 400;
	height: 40; */

	
}

/* VideoCameraIcon {
	width: 6;
	height: 6;
} */
/* MicrophoneIcon
LogoutIcon
CogIcon
PaperAirplaneIcon */

.left-right {
	justify-content: space-around;
}

#session-body-left {
	display: flex;
	flex-direction: column;
	/* float: left; */
	/* justify-content: center; */
	align-self: center;
	
}

.answer-send {
	display: flex;
	flex-direction: row;
	/* align-self: center; */
}

#answer-sheet {
	/* border: 1; */
	border: 1px dotted black;
}

#session-body-right {
	display: flex;
  /* justify-content: flex-end; */
	float: right;
	
}




</style>