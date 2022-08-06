<template>
  
  <communication-record />
  <communication-tab :situationSentence="situationSentence" />

</template>

<script>
import CommunicationRecord from './components/CommunicationRecord.vue'
import CommunicationTab from './components/CommunicationTab.vue'
import axios from 'axios'


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
      axios.get(
        // 'http://localhost:8080/api/situations'
        'https://i7d109.p.ssafy.io/api/situations'
      )
      .then(res => {this.situationSentence = res.data.situations, console.log(res.data)})
    }
  },
  created(){
    this.getSituationItem()
  },
  computed: {
    fetchSituation (data) {
      this.situationSentence === data
      return this.situationSentence 
    }
  }
}
</script>

<style>

</style>