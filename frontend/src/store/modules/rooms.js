// import router from "@/router/index.js";
import axios from 'axios'

export default {
  state: {
    isSearch : false,
    searchRooms : '',
    rooms : '',
    totalRoomPage : 0,
    currentPage : 0,
    mode : '',
    range : [],
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
    }).catch(err => console.log('실패!!!!'))
    },

    getSession({state, dispatch, getters},{mode, page}){

      state.currentPage = page
      state.mode = mode
      axios({
        url : `/api/${mode}/rooms?page=${page}`,
        method : 'get',
        headers : {'Authorization': getters.authHeader.Authorization}
        })
          .then(res =>{state.rooms = res.data.data.listRooms.content, state.totalRoomPage =res.data.data.listRooms.totalPages
          if(page==0){
            dispatch('getRange',state.totalRoomPage)
        }
          })
          .catch(err => {
          console.log(err.status)
          console.log(err,'error here')})
    },
    getRange({state}, n){
      state.range = []
      for(let i =0 ; i < n; i++ ){
        state.range.push(i)
      }
      console.log(state.range)
    },
    chkRange({dispatch, state}, page){
      if(page < 0 || page > state.totalRoomPage-1){
        return
      }
      else{
        dispatch('getSession',{page : page , mode : state.mode})
      }
    }

  },
 
  
  modules: {

  }
}