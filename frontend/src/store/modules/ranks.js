// import router from "@/router/index.js";
import axios from 'axios'

export default {
  state: {
    isSearch : false,
    rankings: [],
    searchFinish : false,
    notMember : false,
  },
  mutations: {

  },
  getters: {
    
  },
  actions: {
    searchStart ({state, getters, dispatch},{searchNickname, modeSequence}) {
      axios ( {
          url: `/api/rankings/search?nickname=${searchNickname}&modeSequence=${modeSequence}`,
          headers: {Authorization : getters.authHeader.Authorization},
          method: "get",
          data: {
            nickname: searchNickname,
            modeSequence: modeSequence,
          },
        },
      ).then(res => {        
        if(res.data.status=='ERROR'){
          state.notMember = true
          console.log(state.notMember)
        }
        state.rankings = res.data.data
        state.searchFinish = true
        state.isSearch = true
      }).catch(err => 
      {
        if (err.response.data.status === 'sucess'){
          console.log('성공!!!!!!')
        }
        else{
          console.log('실패!!!!!!!')
          axios ({
            url: `/api/rankings/search?nickname=${searchNickname}&modeSequence=${modeSequence}`,
            headers: {Authorization : getters.authHeader.Authorization},
            method: "get",
            data: {
              nickname: searchNickname,
              modeSequence: modeSequence,
            },
          },
        ).then(res => {        
          if(res.data.status=='ERROR'){
            state.notMember = true
            console.log(state.notMember)
          }
          state.rankings = res.data.data
          state.searchFinish = true
          state.isSearch = true

            const accessToken = res.headers.authorization
            dispatch('reissuanceToken', accessToken)
          }).catch(err => {
            console.log(err.response.data)
          })
        }
    })
  }
  
},
  modules: {

  }
}