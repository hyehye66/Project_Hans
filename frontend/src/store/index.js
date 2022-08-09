import Vuex from "vuex"

const SERVER_URL = process.env.VUE_APP_SERVER_URL

import createPersistedState from "vuex-persistedstate";
import member from './modules/member'




export default new Vuex.Store({
  modules: { member,
  },
  getters: {
  },
  mutations: {
  },
  actions: {
    joinSession: function({ commit }, contents) {
      console.log('store_joinsession')
      console.log(contents)
      commit('CONFERENCE_ID', contents)
    },
  },

  plugins: [
    createPersistedState({
      paths: ['member']
    })
  ]



})



