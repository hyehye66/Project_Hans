<template>
<div class="chat-detail-bg-img">
    <div id="session">
        <div id="chat-detail-session-header">
            <div class="chat-detail-back-title">
                <!-- 뒤로가기 -->
                <!-- <div class="back-icon"> -->
                <svg id="chat-detail-buttonLeaveSession" @click="leaveSession" value="Leave session" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 15l-3-3m0 0l3-3m-3 3h8M3 12a9 9 0 1118 0 9 9 0 01-18 0z">
                </path>
                </svg>
                <!-- </div> -->
                <!-- 방제목 -->
                <div class="chat-detail-session-title">
                    <h1>{{ this.$route.params.sessionName }}</h1>
                </div>      
            </div>
                

            <div class="chat-detail-total-time">
            <!--  h-30 w-40 p-2 border-2 border-gray-400 bg-gray-200 -->
            <!-- <div class="h-full w-full bg-gray-400"> -->
                <h1>총 진행시간</h1>
            </div>
        </div>

        
        <div class="chat-detail-session-left col-md-8">
            <!-- 화상 -->
            <div class="chat-detail-video-container1 col-lg-12">
              <div class="chat-detail-video-container2 col-lg-12">
                  <ChatDetailRTCItem :stream-manager="publisher" @click="updateMainVideoStreamManager"/>
                  <ChatDetailRTCItem v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click="updateMainVideoStreamManager"/>
              </div>
            </div>
            <!-- 캠,마이크,나가기,설정 -->
            <div class="cam-buttons col-md-6">
                <!-- style="height: 100%" -->
                <!-- 캠 -->
                <span class="chat-detail-icon-area" @click='muteVideo'>
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
                <span class="chat-detail-icon-area" @click='muteAudio'>
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
                <span class="chat-detail-icon-area" @click="leaveSession">
                    <LogoutIcon style="height: 50%; width: 50%;"/>
                </span>
                <!-- 설정 -->
                <span class="chat-detail-icon-area" @click="isOpen">
                    <CogIcon style="height: 50%; width: 50%;"/>
                </span>
            </div>
        </div>

        <div class="chat-detail-session-right col-md-4">
          <div class="chat-box">
            <div data-bs-spy="scroll" data-bs-target="#navbar-example2" data-bs-offset="0" class="scrollspy-example" tabindex="0">
              <!-- <h4 id="scrollspyHeading1">First heading</h4>
              <p>...</p>
              <h4 id="scrollspyHeading2">Second heading</h4>
              <p>...</p>
              <h4 id="scrollspyHeading3">Third heading</h4>
              <p>...</p>
              <h4 id="scrollspyHeading4">Fourth heading</h4>
              <p>...</p>
              <h4 id="scrollspyHeading5">Fifth heading</h4>
              <p>...</p> -->

              <ul v-for="idx in chattingList" :key="idx">
                <li class="one-chat">{{idx}}</li>
              </ul>
            </div>                
          </div>
          <div class="my-chat-input">
              <input v-model="myChat" type="text" @keyup.enter="startChatting"  class="my-chatting"/>
              <PaperAirplaneIcon style="height: 35; width: 35;" @click="startChatting" />
          </div>            
        </div>

        <!-- <div id="main-video" class="col-md-6">
            <ChatDetailRTCItem :stream-manager="mainStreamManager"/>
        </div>
        <div id="video-container" class="col-md-6">

            <ChatDetailRTCItem :stream-manager="publisher" @click="updateMainVideoStreamManager"/>
            <ChatDetailRTCItem v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click="updateMainVideoStreamManager"/>

            <input v-model="myChat" type="text" @keyup.enter="startChatting"/>
        </div>
        <ul v-for="idx in chattingList" :key="idx">
            <li>{{idx}}</li>
        </ul> -->
    </div>
</div>

<ChatRoomUpdateModal v-model:open="open" />   

</template>

<script>
import ChatDetailRTCItem from './components/ChatDetailRTCItem.vue'
import { OpenVidu } from 'openvidu-browser';
import axios from 'axios'
import { mapGetters } from 'vuex';
import { VideoCameraIcon, MicrophoneIcon, LogoutIcon, CogIcon, PaperAirplaneIcon } from '@heroicons/vue/outline';
import ChatRoomUpdateModal from '@/views/modal/components/ChatRoomUpdateModal.vue'

axios.defaults.headers.post['Content-Type'] = 'application/json';



export default {
  name : 'ChatDetailView',
  components : {
    ChatDetailRTCItem,
    ChatRoomUpdateModal,
    // VideoCameraIcon,
    // MicrophoneIcon,
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
        myUserName: '영택임' + Math.floor(Math.random() * 100),
        myChat : '',
        chattingList : [],

        open : false,
    }
  },
  
  created(){
    this.joinSession()
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
                        this.session.on('signal', (event) => {
                            this.chattingList.push(event.data)
                        });

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
    startChatting(){
        this.session.signal({
            data: `${this.profile.nickname} : ${this.myChat}`,  // Any string (optional)
            to: [],                     // Array of Connection objects (optional. Broadcast to everyone if empty)
            type: 'my-chat'             // The type of message (optional)
            })
    .then(() => {
        console.log('Message successfully sent');
        this.myChat = ''
    })
    .catch(error => {
        console.error(error);
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
        
        
  }} 

</script>

<style>
/* scoped 금물 */
svg {
  cursor: pointer;
  color: #ffff;
  /* width: 40;
  height: 40;  */
}

.chat-detail-bg-img {
  margin: auto;
  /* background-image: url("@/assets/15.png"); */
  /* background-size: cover; */
  width: 100vw;
  height: 100vh;
  /* position: absolute; */
  position: relative;
  z-index: 1;
  /* background-color: transparent; */

}

.chat-detail-bg-img::after {
  margin: auto;
  width: 100vw;
  height: 100vh;
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;
  opacity: 0.6;
  content: "";
  background: url("@/assets/15.png");
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

#chat-detail-session-header {
  margin: auto;
  padding: 0.3%;
  width: 96%;
  height: 10%;
  display: flex;
  flex-flow: row wrap;
  justify-content: space-between;
  align-self: center;

  background-color: transparent;

}

.chat-detail-back-title {
  display: flex;
  flex-flow: row wrap;
  justify-content: flex-start;
  align-self: center;

  background-color: transparent;
}

.chat-detail-back-icon {
  /* width: 40%; */
}

.chat-detail-icon-area {
  border: none;
  display: inline-block;
  width: 10%;
  height: 10%;

  background-color: transparent;
}

#chat-detail-buttonLeaveSession {
  width: 10%;
  /* height: 15%; */
  display: block;
}

.chat-detail-session-title {
  padding-left: 2%;
  font-size: 1rem;
  display: flex;
  align-self: center;
  text-align: center;

  justify-content : center;
	align-items : center;

  color: #ffff;

}

.chat-detail-total-time {
  /* padding-right: 6%; */
  font-size: 1rem;
  display: flex;
  align-self: center;
  text-align: center;
  
  justify-content : center;
	align-items : center;

  color: #ffff;

}

.chat-detail-video-container1 {
  width: 100%;
  display: flex;
  /* flex-direction: row;
  flex-wrap: wrap; */
  flex-flow: row wrap;
  justify-content: space-around;
  /* justify-content: center; */
  align-self: center;
  align-items : center;

  background-color: transparent;

  /* right: 0%; */
  /* margin: 0 2.2vw;
  padding: 2vh; */
}

.chat-detail-video-container2 {
  background-color: transparent;
  width: 100%;

}

.chat-detail-video-container2 video {
   /* position: relative; */
   float: left;
   width: 30%;
   margin:0.6%;
   border:0.46rem solid;
   border-color:rgb(255, 255, 255);
   /* cursor: pointer; */
   /* margin:  2%;  */
   /* margin-left: 5%; */
   display: flex;
   align-items: center;
   justify-content: space-evenly;

   background-color: transparent;
}

.chat-detail-video-container2 video + div {
  
  text-align: center;
  /* line-height: 75px; */
   float: left;
   width: 28%;
   position: relative;
   margin-left:-28.5%;
   /* display: flex; */
   /* justify-content: space-around; */

   background-color: transparent;

   /* display: flex;
   align-items: center;
   justify-content: space-around; */

}

.chat-detail-video-container2 p {
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

.chat-detail-main-video2 p {
  /* position: absolute; */

  /* display: inline-block;
  background: #f8f8f8;
  padding-left: 5px;
  padding-right: 5px;
  font-size: 22px;
  color: #777777;
  font-weight: bold;
  border-radius: 5px; */
}

user-video {
    /* margin-left: 20px; */
    /* width: 200;
    height: 180; */

}

#chat-detail-main-video {
  /* width: 73%;
  display: flex;
  justify-content: center;
  align-items: center;

  background-color: transparent;
  
  border: 0.5rem;
  border-style: solid;
  border-radius: 10% 10% 10% 10%;
  border-color: white; */

}

/* .left-right {
    flex-flow: row wrap;
    justify-content: space-around;
} */

.chat-detail-session-left {
  /* margin-left */
  padding-left: 3%;  
  padding-right: 3%;  
  padding-top: 1%;  
  display: flex;
  flex-direction: column;
  float: left;
  justify-content: center;
  align-self: center;
  align-items: center;
  
  background-color: transparent;

  /* height: 80%; */
}

.cam-buttons {
    /* top: 80%; */
    width: 50%;
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
    
    position: fixed;
    bottom: 10%;
}

img {
  /* width : 4vw;
  height : 6.7vh; */
}

.chat-detail-session-right {
  /* padding-left: 3%;  
  padding-right: 3%;  
  padding-top: 1%;   */
  /* margin-right: 3%; */
  padding-top: 1%;
  padding-right: 3%;
  display: flex;
  flex-direction: column;
  float: right;
  justify-content: center;
  align-self: center;
  /* align-items: center;  */

  background-color: transparent;
}

.chat-box {
  /* background-color: transparent;
  border-color: #ffff; */


  /* background-color: rgba(60, 60, 60, 0.26 );
  color: #ffff;
  border: none;
  border-radius: 5% 5% 5% 5%;
  padding: 3%;

  width: 25rem;
  height: 33rem; */

}

.chat-box ul{
  /* padding-left: 1%; */
  /* width: 20rem; */
  /* text-align: center; */
  /* margin: auto; */


  /* border: thick double #32a1ce;
  padding-right: 3%; */

  /* 이거 살리기 */
  /* height: auto;
  justify-content: center;
  font-size: 1rem; */
}

.my-chat-input {
  /* border: 1; */
  /* border: 1px dotted black; */
  border: none;
  font-size: 1.6rem;
  /* width: 90%; */

  display: flex;
  flex-direction: row;
  /* align-self: center; */
  justify-content: center;
  align-items: center;
    
}

.my-chatting {
  width: 80%;
}

.chat-detail-chat-series {
  padding-top: 2%;
  display: flex;
  flex-direction: row;
  /* align-self: center; */
  justify-content: center;
  align-items: center;
  margin: auto;
    
}

/* #rank-table {
  color: #ffff;
} */


.chat-box {
  margin-top: 3%;
  padding:6%;
  overflow-y: scroll;
  /* border: 3px solid rgb(255, 255, 255); */
  border-radius:20px;
  align-items: center;
  height: 73vh;
  margin-right: 2.2vw;
  background: rgba(0, 0, 0, 0.2);
  color:white;
 
}
.chat-box::-webkit-scrollbar {
  width: 8px; 
  height: 8px;
 
}
.chat-box::-webkit-scrollbar-track {
   background: #3f3150;
   border-radius: 20px;
}
.chat-box::-webkit-scrollbar-corner {
   background: #3f3150;
   border-radius: 20px;
  
}
.chat-box::-webkit-scrollbar-thumb {
   background:  #b0a2c8;
   border-radius: 20px;
}
.chat-box::-webkit-scrollbar-button {
  background-color: #dccbe0;
  border-radius: 20px;
}

.one-chat {
  font-size: 1.2rem;
}





</style>