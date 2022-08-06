<template>
   <div class="bg-white p-8 rounded-lg shadow-lg relative hover:shadow-2xl transition duration-500">
      <h1 class="text-2xl text-gray-800 font-semibold mb-3">{{ room.sessionId }}</h1>
      <p class="text-gray-600 leading-6 tracking-normal">방장 : {{ room.connections.content[0].clientData.slice(15,-2) }}</p>
	<p class="text-gray-600 leading-6 tracking-normal">참여인원 : {{ room.connections.numberOfElements }}/6</p>
      <button class="py-2 px-4 mt-8 bg-indigo-600 text-white rounded-md shadow-xl" ><router-link :to="{ name: 'ChatDetailView', params: { mode : mode, sessionName : room.sessionId, isChatRoomCreate : 'false'}}" :sessionName="room.sessionId" :isChatRoomCreate="false">입장하기</router-link></button>
      <div>
        <span class="absolute py-2 px-8 text-sm text-white top-0 right-0 bg-indigo-600 rounded-md transform translate-x-2 -translate-y-3 shadow-xl">{{ mode }}</span>
      </div>
    </div>
</template>

<script>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';

axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";

export default {
	props : {
		room : Object,
		mode : String,
	},
	created(){
		console.log(this.room)
	},
  data () {
		return {
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],
			mySessionId: '',
			myUserName: '영택임' + Math.floor(Math.random() * 100),
		}
	},
  methods: {
		
		thisMode(){
		return this.mode
	}
	}}
	
       
	


</script>

<style scoped>

</style>
