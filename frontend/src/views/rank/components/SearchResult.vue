<template>
  <div>
    
    <b>{{rank}} | {{nickname}} | {{score}} | {{tier}}</b>
  </div>
</template>

<script>
export default {
  name: 'SearchResult',
  data () {
    return {

    }
  },
  props: {
    nickname: String,
    score: Number,
    tier: String,
    rank : Number,
  }

}
</script>

<style>

</style>