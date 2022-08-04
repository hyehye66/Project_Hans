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
        'http://localhost:8080/api/situations'
      )
      .then(res => {this.situationSentence = res.data.situations})
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