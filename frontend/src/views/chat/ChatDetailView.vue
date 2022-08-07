<template>
  <div>
    <div id="session">
			<div id="session-header">
				<h1 id="session-title">{{ this.$route.params.sessionName }}</h1>
				<input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="Leave session">
			</div>
			<div id="main-video" class="col-md-6">
				<ChatDetailRTCItem :stream-manager="mainStreamManager"/>
			</div>
			<div id="video-container" class="col-md-6">
				<ChatDetailRTCItem :stream-manager="publisher" @click="updateMainVideoStreamManager()"/>
				<ChatDetailRTCItem v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click="updateMainVideoStreamManager()"/>
			</div>
		</div>
	</div>

  

</template>

<script>
import ChatDetailRTCItem from './components/ChatDetailRTCItem.vue'
import { OpenVidu } from 'openvidu-browser';
import axios from 'axios'

axios.defaults.headers.post['Content-Type'] = 'application/json';

// const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
// const OPENVIDU_SERVER_SECRET = "MY_SECRET";

const OPENVIDU_SERVER_URL = "https://i7d109.p.ssafy.io";
const OPENVIDU_SERVER_SECRET = "hans";

export default {
  name : 'ChatDetailView',
  components : {
    ChatDetailRTCItem
  },
  data () {
	return {
		mySessionId: '',
		OV: undefined,
		session: undefined,
		mainStreamManager: undefined,
		publisher: undefined,
		subscribers: [],
		myUserName: '영택임' + Math.floor(Math.random() * 100)
	}
  },
  
  created(){
	this.joinSession()
  },



  methods : {
	joinSession () {
			// this.myUserName = 
			this.mySessionId = this.$route.params.sessionName
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

			this.createPublisher()

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
			this.$router.push({name : 'ChatMainView'})
		},

		createPublisher(){
			this.token = this.$route.params.token
			console.log(this.token)
			console.log(this.session)
			this.session.connect(this.token, { clientData: this.myUserName })
			.then(() => {

						// 미디어 스트림 가져오기 

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
						console.log(publisher,1234)
						this.mainStreamManager = publisher;
						this.publisher = publisher;

						// --- Publish your stream ---

						this.session.publish(this.publisher);
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});

		},

		updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},

	getOpenVidu() {
		axios.get()
	}
		
		
  }} 

</script>

<style>

</style>
