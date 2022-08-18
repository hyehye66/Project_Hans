<template>
  <div @click="getProblem(4)">Start</div>
</template>

<script>
import axios from 'axios'
import { mapGetters } from 'vuex'


export default {
data(){
  return{
    questions: [],
    question : {},
    finalqustions : []
  }
},
computed:{
  ...mapGetters(['authHeader'])
},

methods: {
  getProblem(difficulty){
    axios({
      url : '/api/dictations',
      method : 'get',
      headers : {'Authorization' :this.authHeader.Authorization}
    }).then(res=> {
      this.questions = res.data.data.dictations
      for(this.question of this.questions){
        if(this.question.difficulty==difficulty){
          this.finalqustions.push(this.question.sentence)
        }
      }
      console.log(this.finalqustions)
      }).catch(err => console.err(err))
    
  }
}
}
</script>

<style>

</style>