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
import { mapGetters } from 'vuex';

axios.defaults.headers.post['Content-Type'] = 'application/json';



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

  computed : {
    ...mapGetters(['authHeader'])
  },
//this.$route.params.token.slice(39,53)
  methods : {
    // 오픈비두 세션에 들어가기,created에 실행
    joinSession () {
            // 세션의 id는 토큰에서 잘라서 활용 
            this.getToken()
            this.mySessionId = this.token
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
                url : `/api/conversation/rooms/${this.$route.params.roomSequence}`,
                method : 'delete',
                headers : this.authHeader
            }).then(this.$router.push({name : 'ChatMainView'}))
            // 그 후 세션에서 나가기 
            if (this.session) this.session.disconnect();

            this.session = undefined;
            this.mainStreamManager = undefined;
            this.publisher = undefined;
            this.subscribers = [];
            this.OV = undefined;
            window.removeEventListener('beforeunload', this.leaveSession);
            // 나가는 일련의 과정이 끝나면 MainView로 라우터 이동
            
        },
        
        // 화상 만들기
        createPublisher(){
            console.log(this.getToken())
            this.session.connect(this.token, { clientData: this.myUserName })
            .then(() => {

                        // 미디어 스트림 가져오기 
                        console.log(123)
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
                        console.log(this.publisher,345)
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
    }
        
        
  }} 

</script>

<style>

</style>