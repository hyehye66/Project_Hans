<template>
  <nav-bar />
  <div class="container">
  <p id="communication-main-title" class="d-flex justify-content-space-around py-2 px-4 mt-8">상황별 한국어 회화</p></div>
  <communication-tab :situationSentence="situationSentence"/> 

 

</template>

<script>
import CommunicationTab from './components/CommunicationTab.vue'
import NavBar from "@/components/NavBar.vue";
import axios from 'axios'
import { mapGetters } from 'vuex'


export default {
  name : 'CommunicationView',
  components : {
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
        headers: {Authorization : this.$store.getters.authHeader.Authorization},
        }
      )
      .then(res => {this.situationSentence = res.data.data.situations, console.log(this.situationSentence)})
       .catch(err => {
          if (err.response.data.status === 'sucess'){
            console.log('성공!!!!!!')
          }
          else{
            console.log('실패!!!!!!!')
            axios({
              url : '/api/situations',
              method : 'get',
              headers: this.$store.getters.authHeader,
              
            }).then(res =>{
             this.situationSentence = res.data.data.situations
             

              const accessToken = res.headers.authorization
              this.$store.actions.member.reissuanceToken(accessToken)
            }).catch(err => {
              console.log(err.response.data)
            })
          }

        })

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
#communication-main-title {
  font-size: 3rem;
  line-height: 1;
}

</style>