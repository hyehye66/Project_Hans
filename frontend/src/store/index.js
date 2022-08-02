import Vue from 'vue'
import { createStore } from 'vuex'
import axios from "axios"

const SERVER_URL = process.env.VUE_APP_SERVER_URL

// Vue.use(Vuex)

export default createStore({
  state: {
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
  modules: {
  }
})
