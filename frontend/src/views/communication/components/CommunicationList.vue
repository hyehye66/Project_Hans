<template>
  <div>
    <!-- <input type="text"
    v-model="word"
    placeholder="여기에 입력하세요"
    @keyup="setWord">
    <button id="tts" @click="startSpeechToTxt">음성출력</button> -->

    <ul class="list-group">
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
        <button type="button" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full" id="tts2" @click="startSpeechToTxt2">
          listen 
        </button>
      </li>
      <li class="list-group-item">
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


    <!-- <p>how much is it?</p>
    <b>얼마예요?</b>
    <button id="tts1" @click="startSpeechToTxt1">듣기</button>

    <p>It's 1,0000 won each.</p>
    <b>한 개에 만 원입니다.</b>
    <button id="tts2" @click="startSpeechToTxt2">듣기</button>

    <p>Here you are.</p>
    <b>여기 있습니다.</b>
    <button id="tts3" @click="startSpeechToTxt3">듣기</button>

    <p>Thank you.</p>
    <b>감사합니다.</b>
    <button id="tts4" @click="startSpeechToTxt4">듣기</button>

    <p>Goodbye.</p>
    <b>안녕히 계세요.</b>
    <button id="tts5" @click="startSpeechToTxt5">듣기</button> -->

  </div>

</template>

<script>
/* eslint-disable */

  export default {
    name: 'CommunicationList',
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

<style scoped></style>