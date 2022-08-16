<template>
  <nav-bar />
  <!-- <div class="communication-bg-img">
  </div> -->
   <div v-if="!isLoggedIn">로그인이 필요한 서비스입니다.
   <button><router-link :to="{name:'LoginView'}" >로그인하러가기!</router-link></button>
   </div>

  <communication-tab :situationSentence="situationSentence"/> 
 

</template>

<script>
// import CommunicationRecord from './components/CommunicationRecord.vue'
import CommunicationTab from './components/CommunicationTab.vue'
import NavBar from "@/components/NavBar.vue";
import axios from 'axios'
import { mapGetters } from 'vuex'


export default {
  name : 'CommunicationView',
  components : {
    // CommunicationRecord,
    CommunicationTab,
    NavBar,
  },
  data(){
    return {
      situationSentence : []
    }
  },
  methods : {
    getSituationItem (){
      axios(
        {
        url : '/api/situations',
        method : 'get',
        headers : this.authHeader
        }
      )
      .then(res => {this.situationSentence = res.data.data.situations, console.log(this.situationSentence)})
      .catch(err => console.log(err))

    }
  },
  created(){
    this.getSituationItem()
  },
  computed: {
    fetchSituation (data) {
      this.situationSentence === data
      return this.situationSentence 
    },
    ...mapGetters(['authHeader','isLoggedIn'])
  }
}
</script>

<style scoped>


</style>