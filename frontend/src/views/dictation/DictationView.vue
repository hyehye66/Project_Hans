<template>
    <NavBar />
    <div class="container">
      <p id="dictation-main-title" class="d-flex justify-content-space-around py-2 px-4 mt-8">받아쓰기</p>
      <button class="btn btn-outline btn-secondary" id="listen-btn" @click="startSpeechToTxt()"></button>
    </div>
</template>

<script>

import { mapActions } from 'vuex';
import NavBar from '@/components/NavBar.vue';

    export default {
      data(){
        return{
        questions: ['사과','바나나','호랑이','원숭이','돼지']
        } 
      },
        components: {
    NavBar
},
        methods: {
         startSpeechToTxt() {
        {
          for(let question of this.questions){
            for (var i = 0; i < 2; i++ ){
            let utterance = new SpeechSynthesisUtterance(question)
            utterance.rate = 0.8
            window.speechSynthesis.speak(utterance)
            setTimeout({}, 2000);

            }
          }
        }  
        
    }, 
          

        }
    }
</script>


<style scoped>
#dictation-main-title {
  font-size: 3rem;
  line-height: 1;
}
</style>