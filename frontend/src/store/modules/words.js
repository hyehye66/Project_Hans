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
    searchWordRoom({dispatch},{input, searchSelected}){
      console.log(searchSelected)
      if (searchSelected == 'title'){
        dispatch('searchWordRoomTitle', input)
      }
      if (searchSelected == 'nickname'){
        dispatch('searchWordRoomNickname', input)
      }
    },

    searchWordRoomTitle({state, getters},title){
      axios ( {
          url: `/api/word-game/rooms/search-title?title=${title}`,
          headers: getters.authHeader,
          method: "get",
        })
      .then(res => {   
        state.isSearch = true     
        state.searchRooms = res.data.data.listRooms.content
      }).catch(err => console.log('실패!!!!'))
    },
    searchWordRoomNickname({state, getters},nickname){
      axios ( {
        url: `/api/word-game/rooms/search-nickname?nickname=${nickname}`,
        headers: getters.authHeader,
        method: "get",
      })
    .then(res => {   
      state.isSearch = true     
      state.searchRooms = res.data.data.listRooms.content
    }).catch(err => console.log('실패!!!!'))
    },

  },
 
  
  modules: {

  }
}