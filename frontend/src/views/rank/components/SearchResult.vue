<template>
  <div>
    <b>{{nickname}} | {{score}} | {{tier}}</b>
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

  }

}
</script>

<style>

</style>