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
    searchStart ({state, getters},{searchNickname, modeSequence}) {
      axios ( {
          url: `/api/rankings/search?nickname=${searchNickname}&modeSequence=${modeSequence}`,
          headers: getters.authHeader,
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
      }).catch(err => {console.error(err); })

  },
},
  modules: {

  }
}