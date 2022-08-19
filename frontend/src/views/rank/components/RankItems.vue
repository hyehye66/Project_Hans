<template>
  <div>
    <span>{{rankingSequence}}</span>      
    <span>{{nickname}}</span>
    <span>{{score}}</span>
    <span>{{tier}}</span>
  </div>
</template>

<script>
export default {
  name: 'RankItems',
  data () {
    return {

    }
  },
  props: {
    rankingSequence: Number,
    nickname: String,
    score: Number,
    tier: String,

  }

}
</script>

<style>

</style>