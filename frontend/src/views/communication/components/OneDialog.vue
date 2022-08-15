<template>
  <div class="one-row">
    <div class="icon-sentence col-md-10">
      <div class="dialog-front-icon">
        <!-- chat-alt -->
        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
          <path fill-rule="evenodd" d="M18 5v8a2 2 0 01-2 2h-5l-5 4v-4H4a2 2 0 01-2-2V5a2 2 0 012-2h12a2 2 0 012 2zM7 8H5v2h2V8zm2 0h2v2H9V8zm6 0h-2v2h2V8z" clip-rule="evenodd" />
        </svg>{{english_sentence}}
        <!-- >> -->
        <!-- <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
          <path fill-rule="evenodd" d="M10.293 15.707a1 1 0 010-1.414L14.586 10l-4.293-4.293a1 1 0 111.414-1.414l5 5a1 1 0 010 1.414l-5 5a1 1 0 01-1.414 0z" clip-rule="evenodd" />
          <path fill-rule="evenodd" d="M4.293 15.707a1 1 0 010-1.414L8.586 10 4.293 5.707a1 1 0 011.414-1.414l5 5a1 1 0 010 1.414l-5 5a1 1 0 01-1.414 0z" clip-rule="evenodd" />
        </svg> -->
        
      </div>
      
      <div class="dialog col-md-12">
        {{sentence}}
      </div>
      <!-- <span class="dialog col-md-12">{{sentence}}  </span> -->
    </div>
    <div class="listen-record col-md-2">
      <!-- <button type="button" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full" @click="startSpeechToTxt1">
        listen 
      </button> -->
      <button class="btn btn-outline btn-secondary" @click="startSpeechToTxt(this.sentence)">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
          <path stroke-linecap="round" stroke-linejoin="round" d="M15.536 8.464a5 5 0 010 7.072m2.828-9.9a9 9 0 010 12.728M5.586 15H4a1 1 0 01-1-1v-4a1 1 0 011-1h1.586l4.707-4.707C10.923 3.663 12 4.109 12 5v14c0 .891-1.077 1.337-1.707.707L5.586 15z" />
        </svg>
      </button>
      <button class="btn btn-secondary" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasScrolling" aria-controls="offcanvasScrolling">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
          <path stroke-linecap="round" stroke-linejoin="round" d="M19 11a7 7 0 01-7 7m0 0a7 7 0 01-7-7m7 7v4m0 0H8m4 0h4m-4-8a3 3 0 01-3-3V5a3 3 0 116 0v6a3 3 0 01-3 3z" />
        </svg>
      </button>

      <div class="offcanvas offcanvas-end" data-bs-scroll="true" data-bs-backdrop="false" tabindex="-1" id="offcanvasScrolling" aria-labelledby="offcanvasScrollingLabel">
        <div class="offcanvas-header">
          <div class="title-group">
            <h5 class="offcanvas-title" id="offcanvasScrollingLabel">녹음하고 들으면서</h5>
            <p class="offcanvas-title" id="offcanvasScrollingLabel">한국어를 익혀보세요!</p>
          </div>
          <div class="close-btn">
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close">
              <XIcon style="height: 35; width: 35;" />
            </button>
          </div>
        </div>
        <div class="offcanvas-body">
          <!-- <p>Try scrolling the rest of the page to see this option in action.</p> -->
          <communication-record />
        </div>
      </div>
      

    </div>
    <!-- <div class="tts-btn col-md-2">
      <button type="button" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full" @click="startSpeechToTxt1">
        listen 
      </button>
    </div>     -->
  </div>
</template>

<script>
import CommunicationRecord from '@/views/communication/components/CommunicationRecord.vue'
import { XIcon } from '@heroicons/vue/outline';

export default {  
  name: 'OneDialog',
  components: {
    CommunicationRecord,
    XIcon,
  },
  data () {
    return {
      lang: "ko-KR",
      speaking: "",
      // word: "",
      // dialoglist: ["얼마예요?", 
      // "한 개에 만 원입니다", 
      // "여기 있습니다", 
      // "감사합니다", 
      // "안녕히 계세요"],

    }
  },
  props: {
    sentence : String,
    english_sentence : String,
  },
  methods: {
     startSpeechToTxt(sentence) {
      // start speech to txt
      let utterance = new SpeechSynthesisUtterance(sentence)
      window.speechSynthesis.speak(utterance)
    },
    startSpeechToTxt1() {
      // start speech to txt
      let utterance = new SpeechSynthesisUtterance("얼마예요?")
      window.speechSynthesis.speak(utterance)
    },
    startSpeechToTxt2() {
      // start speech to txt
      let utterance = new SpeechSynthesisUtterance("한 개에 만 원입니다")
      window.speechSynthesis.speak(utterance)
    },
    startSpeechToTxt3() {
      // start speech to txt
      let utterance = new SpeechSynthesisUtterance("여기 있습니다")
      window.speechSynthesis.speak(utterance)
    },
    startSpeechToTxt4() {
      // start speech to txt
      let utterance = new SpeechSynthesisUtterance("감사합니다")
      window.speechSynthesis.speak(utterance)
    },
    startSpeechToTxt5() {
      // start speech to txt
      let utterance = new SpeechSynthesisUtterance("안녕히 계세요")
      window.speechSynthesis.speak(utterance)
    },
    // setWord(e){
    //   this.word = e.target.value
    // }
  },


}
</script>

<style scoped>
.one-row {
  /* margin-right */
  display: flex;
  flex-flow: row wrap;
	justify-content: space-between;
	align-items: center;

}

.icon-sentence {
  display: flex;
  flex-flow: row wrap;
	justify-content: flex-start;
	align-items: center;
}

.dialog-front-icon {
  padding: 1%;
  color: #f1c4d9;
}

.dialog {
  padding: 1%;
  font-size: 1.1rem;

}

.listen-record {
  display: flex;
  flex-flow: row wrap;
	justify-content: space-around;

}

button {
  /* margin- */
}

.offcanvas-header {
  display: flex;
  flex-flow: row wrap;
	justify-content: space-between;
	/* align-items: center; */
  /* text-align: center; */
  
}

.offcanvas-title {
  font-size: 1.6rem;
  display: flex;
  flex-flow: column wrap;
	justify-content: flex-start;
}



</style>