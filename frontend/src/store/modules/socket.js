import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {
state : {
    problem : '',
    problemList : [],
    answer : '',
    answerUserList : [],
    difficulty : '',
    stompClient : undefined,
},
getters : {},
mutations  : {},   
actions : {
    async socketCreate(connectCallback){
        let socket = await SockJS("https://i7d109.p.ssafy.io/ws/game")
        this.stompClient = Stomp.over(socket)
        console.log('소켓 연결하는 중')
        this.stompClient.connect({}, ()=> {
            console.log('연결성공')
            this.isConnect = true
            connectCallback()
        })
    },
}
}
