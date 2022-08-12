// import router from "@/router/index.js";
// import axios from 'axios'

export default {
    state: {
      Timer: null,
      TimeCounter: 0,
      TimerStr: '',
      TimerChk: false,
    },
    mutations: {
    SET_TIMER: (state,timer) => state.timer = timer,
    },
    getters: {
      timer : state => state.timer,
      counter : state => state.counter,
      countStr : state => state.countStr
    },
    actions: {
      timerStart({state, dispatch},time) {
        // 1초에 한번씩 start 호출
        state.TimeCounter = time;
        console.log(state.TimeCounter)
        console.log('카운트시작!')
        var interval = setInterval(() => {
          state.TimeCounter--; //1초씩 감소
          dispatch('prettyTime').then((res)=> {
            state.TimerStr = res
            console.log('시간:' + state.TimerStr)
            console.log(state.TimerChk)
          })
          if (state.TimeCounter <= 0 || state.TimerChk) {
            dispatch('timerStop',interval)
            state.TimerChk=false
          }       
        }, 1000);
        return interval;
      },
      timerStop({state}, Timer) {
        clearInterval(Timer);
        state.TimeCounter = 0;
      },
      prettyTime({state}) {
        // 시간 형식으로 변환 리턴
        let time = state.TimeCounter / 60;
        let minutes = parseInt(time);
        let seconds = Math.round((time - minutes) * 60);
        return (
          seconds.toString().padStart(2, "0")
        );
      },
      // async startCounter({ getters,state }, time) {
      //   console.log('카운팅시작!!')
  
      //   state.counter = true;
      //   state.timer = time
      //   while (state.timer > 0 && state.counter) {
      //     await new Promise(resolve => setTimeout(resolve, 1000));
      //     if (state.counter)
      //     state.timer--;
      //   }
      //   state.counter = false;
      // },
      // resetCounter(context) {
      //   state.count = 5;
      //   state.counter = false;
      // }
  
    },
    modules: {
  
    }
  }