<template>
  
  <communication-record />
  <communication-tab :situationSentence="situationSentence" />

</template>

<script>
import CommunicationRecord from './components/CommunicationRecord.vue'
import CommunicationTab from './components/CommunicationTab.vue'
import axios from 'axios'
import { mapGetters } from 'vuex'


export default {
  name : 'CommunicationView',
  components : {
    CommunicationRecord,
    CommunicationTab,
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
    ...mapGetters(['authHeader'])
  }
}
</script>

<style>

</style>