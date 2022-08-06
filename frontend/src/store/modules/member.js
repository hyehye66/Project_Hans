// import router from "@/router/index.js";
import axios from 'axios'

export default {
  state: {
      host: 'localhost:8080',
      accessToken: localStorage.getItem('accessToken') || '',
      refreshToken: localStorage.getItem('refreshToken') || '',
      authError: null,
      msg : "디폴트",
  },
  mutations: {
    LOGIN: function(state, credentials){
      state.accessToken = credentials.accessToken;
    },    
    SET_ACCESS_TOKEN : (state, accessToken) => state.accessToken = accessToken,
    SET_REFRESH_TOKEN : (state, refreshToken) => state.refreshToken = refreshToken,
    SET_AUTH_ERROR: (state, error) => state.authError = error,
    SET_MSG : (state, msg) => state.msg = msg,
  },
  getters: {
    isLoggedIn: state => !!state.accessToken,
    authError: state => state.authError,
    msg : state => state.msg,
  },
  actions: {
    saveToken({ commit }, accessToken) {
      /* 
      state.token 추가 
      localStorage에 token 추가
      */
      commit('SET_ACCESS_TOKEN', accessToken)
      localStorage.setItem('accessToken',accessToken)
    },

    // login: function({commit}, credentials){
    //   console.log('hallo')
    //   commit('LOGIN',credentials)
    // },


    login({ commit, dispatch }) {
      console.log('login')  
      axios({
        url: '/api/google-login',
        method: 'get',

      })
        .then(res => {
          console.log('스토어액션')
          commit('SET_MSG',res.data)
          console.log(res.data)
          const accessToken = res.data.accessToken
          dispatch('saveToken', accessToken)
          //router.push({ name: 'DictationView' })
        })
        .catch(err => {
          console.error(err.response.data)
          commit('SET_AUTH_ERROR', err.response.data)
        })
    },
   

    // login: ({commit, dispatch}) => {
    //   return new Promise((resolve, reject) => {
    //     axios.get('/google-login').then(res => {
    //       const accessToken = res.data.accessToken
    //       dispatch('SET_ACCESS_TOKEN', accessToken)
    //       resolve(res)
    //       router.push({ name: 'DictationView' })
    //     })
    //     .catch(err => {
    //       console.log(err.message)
    //       reject(err.message)
    //       commit('SET_AUTH_ERROR', err.response.data)
    //     });
    //   })
    // },



    // refreshToken: ({commit}) => { // accessToken 재요청
    //   //accessToken 만료로 재발급 후 재요청시 비동기처리로는 제대로 처리가 안되서 promise로 처리함
    //   return new Promise((resolve, reject) => {
    //     axios.post('/v1/auth/certify').then(res => {
    //       commit('refreshToken', res.data.auth_info);
    //       resolve(res.data.auth_info);
    //     }).catch(err => {
    //       console.log('refreshToken error : ', err.config);
    //       reject(err.config.data);
    //     })
    //   })
    // },
    logout: ({commit}) => { // 로그아웃
      commit('removeToken');
      location.reload();
    }
  },

modules: {
},
}

