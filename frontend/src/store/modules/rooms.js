// import router from "@/router/index.js";
import axios from 'axios'

export default {
  state: {
    isSearch : false,
    searchRooms : '',
  },

  mutations: {

  },

  getters: {
    
  },

  actions: {
    searchRoom({dispatch},{input, searchSelected, mode}){
      console.log(mode, input, searchSelected)
      if (searchSelected == 'title'){
        dispatch('searchRoomTitle', {title : input, mode : mode})
      }
      if (searchSelected == 'nickname'){
        dispatch('searchRoomNickname', {nickname : input, mode : mode})
      }
    },

    searchRoomTitle({state, getters},{title, mode}){
      axios ( {
          url: `/api/${mode}/rooms/search-title?title=${title}`,
          headers: getters.authHeader,
          method: "get",
        })
      .then(res => {   
        state.isSearch = true
        state.searchRooms = ''
        state.searchRooms = res.data.data.listRooms.content
      }).catch(err => console.log('실패!!!!'))
    },
    searchRoomNickname({state, getters}, {nickname, mode}){
      axios ( {
        url: `/api/${mode}/rooms/search-nickname?nickname=${nickname}`,
        headers: getters.authHeader,
        method: "get",
      })
    .then(res => {   
      state.isSearch = true     
      state.searchRooms = res.data.data.listRooms.content
      console.log('ㅇㅇㅇㅇㅇ')
      console.log(state.searchRooms)
    }).catch(err => console.log('실패!!!!'))
    },

  },
 
  
  modules: {

  }
}