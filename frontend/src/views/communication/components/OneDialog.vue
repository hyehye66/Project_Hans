<template>
  <div>
    <b>{{sentence}}  </b>
    <button type="button" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full" id="tts1" @click="startSpeechToTxt1">
      listen 
    </button>
  </div>
</template>

<script>
export default {  
  name: 'OneDialog',
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
    sentence : String
  },
  methods: {
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

<style>

</style>