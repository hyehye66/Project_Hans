import router from "@/router/index.js";
import state from "@/views/main/store/state";
import axios from 'axios'

export default {
  state: {
      accessToken: localStorage.getItem('accessToken') || '',
      refreshToken: localStorage.getItem('refreshToken') || '',
      authError: null,
      email : '',
      profile: {},
  },
  mutations: {
    LOGIN: function(state, credentials){
      state.accessToken = credentials.accessToken;
    },    
    SET_ACCESS_TOKEN : (state, accessToken) => state.accessToken = accessToken,
    SET_REFRESH_TOKEN : (state, refreshToken) => state.refreshToken = refreshToken,
    SET_AUTH_ERROR: (state, error) => state.authError = error,
    SET_MSG : (state, msg) => state.msg = msg,
    SET_EMAIL : (state,email) => state.email = email,
    SET_PROFILE: (state, profile) => state.profile = profile,
  },
  getters: {
    isLoggedIn: state => !!state.accessToken,
    authHeader: state => ({ Authorization: state.accessToken, refreshToken : state.refreshToken}),
    authError: state => state.authError,
    email : state => state.email,
    profile: state => state.profile,
  },
  actions: {
    saveToken({ commit }, {accessToken, refreshToken}) {
      /* 
      state.token 추가 
      localStorage에 token 추가
      */
      commit('SET_ACCESS_TOKEN', accessToken)
      commit('SET_REFRESH_TOKEN', refreshToken)
      localStorage.setItem('accessToken',accessToken)
      localStorage.setItem('refreshToken',refreshToken)
      
    },
    removeToken({ commit }) {
      /* 
      state.token 삭제
      localStorage에 token 추가
      */
      commit('SET_ACCESS_TOKEN', '')
      commit('SET_REFRESH_TOKEN', '')
      localStorage.setItem('accessToken','')
      localStorage.setItem('refreshToken','')
    },


    // login: function({commit}, credentials){
    //   console.log('hallo')
    //   commit('LOGIN',credentials)
    // },


    login({ commit, dispatch }, useremail) {
      console.log('login')  
      axios({
        url: '/api/login',
        method: 'get',
        params : {
          email : useremail,
        }
      })
        .then(res => {
          console.log('로그인완료')
          // 토큰 저장 로직

          const accessToken = res.headers.authorization
          const refreshToken = res.headers.refreshtoken
          dispatch('saveToken', {accessToken ,refreshToken}) 

          router.push({ name: 'Home' })
        })
        .catch(err => {
          localStorage.setItem('email',useremail)
          console.log('회원가입안되있습니다!')
          commit('SET_EMAIL',useremail)
          console.error(err.response.data)
          router.push({ name: 'SignUpView' })
          commit('SET_AUTH_ERROR', err.response.data.message)
        })
    },
    signup({ commit, dispatch }, credentials) {
      /* 
      POST: 사용자 입력정보를 signup URL로 보내기
        성공하면
          응답 토큰 저장
          현재 사용자 정보 받기
          메인 페이지(ArticleListView)로 이동
        실패하면
          에러 메시지 표시
      */
      credentials.email = this.state.member.email
      console.log(credentials)
      axios({
        url: '/api/members/sign-up',
        method: 'post',
        data: credentials
      })
        .then(res => {
          console.log('회원가입완료')
          alert('회원가입 되었습니다!')
          // 토큰 저장 로직
          const accessToken = res.headers.authorization
          const refreshToken = res.headers.refreshToken
          dispatch('saveToken', {accessToken ,refreshToken}) 
          commit('SET_ACCESS_TOKEN',accessToken)
          commit('SET_REFRESH_TOKEN', refreshToken)
          
          router.push({ name: 'Home' })
        })
        .catch(err => {
          console.error(err.response.data.message)
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
    logout({ dispatch }) {
      /* 
      토큰 삭제
      */
          dispatch('removeToken')
          alert('성공적으로 logout!')
          console.log('logout완료!')
          router.push({ name: 'Home' })
        
    },
    fetchProfile({ commit, getters }) {
      /*
      GET: profile URL로 요청보내기
        성공하면
          state.profile에 저장
      */
     // if(username === )
      axios({
        url: '/api/members',
        method: 'get',
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_PROFILE', res.data.data)
        })
    },
    updateProfile({commit, getters}, {nickname, introduction} ){
      axios({
        url: '/api/members',
        method: 'put',
        headers : getters.authHeader,
        data : {nickname, introduction},
      }).then(res => {
        commit('SET_PROFILE', res.data.data)
        alert('회원정보 수정완료!')
        router.push({
          name: 'MyPageView',
        })
      })
    },

    withdrawal({dispatch, getters}){
      axios({
        url:'/api/members',
        method: 'delete',
        headers: getters.authHeader,
      })
      .then(()=>{
          dispatch('removeToken')
          alert('회원탈퇴 되었습니다!')
          console.log('회원탈퇴 완료')
          router.push({ name: 'Home' })
      }).catch((err => {
        console.error(err.response.data.message)
      }))
    }

  },

modules: {
},
}

