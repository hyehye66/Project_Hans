<template>
  
  <communication-record />
  <communication-tab />

</template>

<script>
import CommunicationRecord from './components/CommunicationRecord.vue'
import CommunicationTab from './components/CommunicationTab.vue'

export default {
  name : 'CommunicationView',
  components : {
    CommunicationRecord,
    CommunicationTab,
  },
}
</script>

<style>

</style>