<template>
<nav-bar></nav-bar>
  <div class="container">

    <div class="box relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg bg-white rounded">       
      <div class="px-4 py-5 flex-auto" style="width: 70%;">
        <div class="content tab-space" >
          <h1>회원가입</h1>
          <br>
      
         <div class="mb-3">
            <label for="exampleFormControlInput1" class="form-label">
              <AcademicCapIcon />           
              <b>이메일</b>
              <br>
              <b>{{email}}</b>
            </label>            
          </div>
          <div class="mb-3">
            <label for="nickname" class="form-label">
              <PencilAltIcon />            
              <b>닉네임</b>
            </label>
            <input class="form-control" placeholder="닉네임을 입력해 주세요." 
            v-model="credentials.nickname" type="text" id="nickname" required/>
          </div>
          <div class="mb-3">
            <label for="introduction" class="form-label">
              <PencilAltIcon />         
              <b>자기소개</b>
              </label>
           <textarea class="form-control" placeholder="자기소개를 입력해 주세요." 
           v-model="credentials.introduction" type="text" id="introduction" required>
           </textarea>           
          </div>

           <div class="buttons">
            <button class="signup-btn" @click="signup(credentials)">
              <span class='btn-animate'>가입하기</span>
           </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import { mapActions, mapGetters } from 'vuex'
import { PencilAltIcon, AcademicCapIcon } from '@heroicons/vue/outline';

  export default {
    name: 'SignupView',
    components: {
      NavBar,
      PencilAltIcon,
      AcademicCapIcon,
    },
    data() {
      return {
        credentials: {
          nickname: '',
          introduction: '',
          provider:'google',
        },
        email : localStorage.getItem('email'),
      }
    },
    computed: {
      ...mapGetters(['authError']),

    },
    methods: {
      ...mapActions(['signup'])
    },
  }
</script>

<style>


.signup {
  font-family: 'Black Han Sans', sans-serif;
}

.form-item {
  margin: 0.5rem;
  position: relative;
  left:3%;
  
 
}

.signup-btn {
  margin: 0.6rem;
  height: 2em;
  width: 15em;
  font-size: 1.5rem;
  position: relative;
  left: 2%;
  font-family: 'Black Han Sans', sans-serif;
}

h1 {
  font-size: 3em;
}
.container {
  display: flex;
  justify-content: center;
  margin-top: 2%;
}

.box {
  width: 60%;
  display: flex;
  justify-content: center;
  
}

.text-box {
  float: right;
  flex-flow: column wrap;
}


svg {
  width: 10%;
  height: 10%;
}

b {
  font-size: 1.2em;
}

.buttons {

  display: flex;
  flex-flow: row wrap;
  float: right;  

}
button {
  display: flex;
  justify-content: right;
  padding-left: 3%;

}

.btn-animate {
  text-decoration: none;
  color: #fff;
  font-size: 14px;
  font-weight: 600;
  letter-spacing: 2px;
  cursor: pointer;
  position: relative;
  z-index: 1;
  padding: 15px 30px;
  border: none;
  border-radius: 4px;
  box-shadow: 0px 16px 47px -15px #cda6ee;
  background: none;
  transition: all 0.2s cubic-bezier(0.19, 1, 0.22, 1);
  border-radius: 8px;
  overflow: hidden;
  outline: none;
  transform: translateZ(0);
}
.btn-animate span {
  position: relative;
  z-index: 2;
}
.btn-animate:before, .btn-animate:after {
  border-radius: 8px;
  content: "";
  z-index: -1;
  background: linear-gradient(100deg, #a6c1ee, #cc96eb);
  
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
.btn-animate:after {
  
  background: linear-gradient(100deg, #560a9b, #9e158f);
  transform: scaleY(0);
  transform-origin: top;
  transition: transform 0.5s cubic-bezier(0.19, 1, 0.22, 1);
  transition-delay: 0.1s;
}
.btn-animate:hover {
  box-shadow: 0px 16px 47px -15px  #cda6ee;
}
.btn-animate:hover:after {
  transform: scaleY(1);
  transform-origin: bottom;
  transition-delay: 0s;
}
.btn-animate:active {
  transform: translateY(4px) translateZ(0);
  box-shadow: 0px 8px 10px -6px  #cda6ee;
}


</style>

