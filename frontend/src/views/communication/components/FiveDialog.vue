<template>
  <div>
    <ul class="list-group" style="width: 750px">
      <li class="list-group-item">
        how much is it? |
        <b>얼마예요?   </b>
        <button type="button" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full" id="tts1" @click="startSpeechToTxt1">
          listen 
        </button>
      </li>
      <li class="list-group-item">
        It's 1,0000 won each. |
        <b>한 개에 만 원입니다.   </b>

        <button type="button" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full" id="tts2" @click="startSpeechToTxt2" />
      </li>
      <li>
        Here you are. |
        <b>여기 있습니다.   </b>
        <button type="button" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full" id="tts3" @click="startSpeechToTxt3">
          listen 
        </button>
      </li>
      <li class="list-group-item">
        Thank you. |
        <b>감사합니다.   </b>
        <button type="button" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full" id="tts4" @click="startSpeechToTxt4">
          listen 
        </button>
      </li>
      <li class="list-group-item">
        Goodbye. |
        <b>안녕히 계세요.   </b>
        <button type="button" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full" id="tts5" @click="startSpeechToTxt5">
          listen 
        </button>
      </li>
    </ul>
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