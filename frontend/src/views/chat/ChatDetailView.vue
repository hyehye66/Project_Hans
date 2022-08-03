<template>
<p>{{ sessionName }}</p>
  <!-- <div>
    <div id="session">
			<div id="session-header">
				<h1 id="session-title">{{ sessionName }}</h1>
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
	</div> -->

  

</template>

<script>
// import ChatDetailRTCItem from './components/ChatDetailRTCItem.vue'
// import { OpenVidu } from 'openvidu-browser';
// import axios from 'axios'

// axios.defaults.headers.post['Content-Type'] = 'application/json';

// const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
// const OPENVIDU_SERVER_SECRET = "MY_SECRET";

export default {
  name : 'ChatDetailView',
  components : {
    // ChatDetailRTCItem
  },
  data () {
	return {
		mySessionId: '',
		OV: undefined,
		session: undefined,
		mainStreamManager: undefined,
		publisher: undefined,
		subscribers: [],
	}
  },
  props : {
	sessionName :String,
  },
  created(){
	console.log(this.sessionName)
  }
}
  
//   created(){
// 	this.joinSession(this.sessionName)
//   },

//   methods : {
// 	leaveSession () {
// 			// --- Leave the session by calling 'disconnect' method over the Session object ---
// 			if (this.session) this.session.disconnect();

// 			this.session = undefined;
// 			this.mainStreamManager = undefined;
// 			this.publisher = undefined;
// 			this.subscribers = [];
// 			this.OV = undefined;

// 			window.removeEventListener('beforeunload', this.leaveSession);
// 			this.$router.push({name : 'ChatMainView'})
// 		},

// 	updateMainVideoStreamManager (stream) {
// 			if (this.mainStreamd === stream) return;
// 			this.mainStream = stream;
// 		},

// 	joinSession (sessionName) {
// 			this.mySessionId = sessionName
// 			this.OV = new OpenVidu();
// 			this.session = this.OV.initSession();
			
// 			this.session.on('streamCreated', ({ stream }) => {
// 				const subscriber = this.session.subscribe(stream);
// 				this.subscribers.push(subscriber);
// 			});


// 			this.session.on('streamDestroyed', ({ stream }) => {
// 				const index = this.subscribers.indexOf(stream.streamManager, 0);
// 				if (index >= 0) {
// 					this.subscribers.splice(index, 1);
// 				}
// 			});

// 			this.session.on('exception', ({ exception }) => {
// 				console.warn(exception);
// 			});
            
// 			this.getToken(this.mySessionId).then(token => {
// 				this.session.connect(token, { clientData: this.myUserName })
// 					.then(() => {

// 						// --- Get your own camera stream with the desired properties ---

// 						let publisher = this.OV.initPublisher(undefined, {
// 							audioSource: undefined, // The source of audio. If undefined default microphone
// 							videoSource: undefined, // The source of video. If undefined default webcam
// 							publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
// 							publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
// 							resolution: '640x480',  // The resolution of your video
// 							frameRate: 30,			// The frame rate of your video
// 							insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
// 							mirror: false       	// Whether to mirror your local video or not
// 						});

// 						this.mainStreamManager = publisher;
// 						this.publisher = publisher;

// 						// --- Publish your stream ---

// 						this.session.publish(this.publisher);
// 					})
// 					.catch(error => {
// 						console.log('There was an error connecting to the session:', error.code, error.message);
// 					});
// 			});

// 			window.addEventListener('beforeunload', this.leaveSession)
//         },
//         getToken (mySessionId) {
// 			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
// 		},
// 		createSession (sessionId) {
//       console.log(sessionId)
//       console.log(this.mySessionId)
      
// 			return new Promise((resolve, reject) => {
// 				axios
// 					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
// 						customSessionId: sessionId,
// 					}), {
// 						auth: {
// 							username: 'OPENVIDUAPP',
// 							password: OPENVIDU_SERVER_SECRET,
// 						},
// 					})
// 					.then(response => response.data)
// 					.then(data => resolve(data.id))
//           .then(sessionId => this.joinSession(sessionId))
// 					.catch(error => {
// 						if (error.response.status === 409) {
// 							resolve(sessionId);
// 						} else {
// 							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
// 							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
// 								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
// 							}
// 							reject(error.response);
// 						}
// 					});
// 			});
// 		},
// 		createToken (sessionId) {
// 			return new Promise((resolve, reject) => {
// 				axios
// 					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
// 						auth: {
// 							username: 'OPENVIDUAPP',
// 							password: OPENVIDU_SERVER_SECRET,
// 						},
// 					})
// 					.then(response => response.data)
// 					.then(data => resolve(data.token))
// 					.catch(error => reject(error.response));
// 			});
// 		},
//   } 
// }
</script>

<style>

</style>