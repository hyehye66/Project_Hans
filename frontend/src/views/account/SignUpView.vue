<template>
<nav-bar></nav-bar>
  <div>
    <h1 class="signup" style="text-align:center">회원가입</h1>
    <account-error-list v-if="authError"></account-error-list>
    <div class="signup-box">
      <form class="signup-form" @submit.prevent="signup(credentials)">

        <div class="form-item" >
          <label for="nickname"  >닉네임 :</label>
          <input placeholder="닉네임을 입력해 주세요." v-model="credentials.nickname" type="text" id="nickname" required/>
        </div>

        <div class="form-item">
          <label for="introduction" style="margin-right:5px">자기소개 :</label>
          <input placeholder="자기소개를 입력해 주세요." v-model="credentials.introduction" type="text" id="introduction" required />
        </div>
   

        <div>
          <button class="signup-btn">가입하기</button>
        </div>
      </form>
    </div>
    
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import { mapActions, mapGetters } from 'vuex'
import AccountErrorList from './components/AccountErrorList.vue'

  export default {
    name: 'SignupView',
    components: {
      AccountErrorList,
      NavBar,
    },
    data() {
      return {
        credentials: {
          nickname: '',
          introduction: '',
          provider:'google',
        }
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
.signup-form {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-content: center;
  margin: 0 auto;
  width : 400px;
  height: 300px;
 

  
  
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


#nickname {
  width:18rem;
  height: 1.8rem;
}
#password1 {
  width:17.8rem;
}

</style>

