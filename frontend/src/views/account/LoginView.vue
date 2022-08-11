<template>
<account-error-list v-if="authError"></account-error-list>
<div class="hero min-h-screen bg-base-200">
  <div class="hero-content flex-col lg:flex-row-reverse">
    <!-- <div class="main-img">
      <img src="@/assets/login4.png" alt="login3">
    </div> -->

    <div class="card flex-shrink-0 w-full max-w-sm shadow-2xl bg-base-100 col-md-4">
      <div class="card-body">
        <div class="text-center lg:text-left">
          <h1 class="upper-text text-5xl font-bold">Login now!</h1>
          <p class="lower-text py-6">재미난 게임을 통해 한국어 실력을 향상시켜보세요</p>
        </div>
        <div class="form-control">      
          <label class="label">
            소셜 SNS로 시작하기
          </label>
          <div class="google-icon">
            <svg xmlns="http://www.w3.org/2000/svg" 
              xmlns:xlink="http://www.w3.org/1999/xlink" 
              viewBox="0 0 48 48"><defs><path id="a" 
              d="M44.5 20H24v8.5h11.8C34.7 33.9 30.1 37 24 37c-7.2 0-13-5.8-13-13s5.8-13 13-13c3.1 0 5.9 1.1 8.1 2.9l6.4-6.4C34.6 4.1 29.6 2 24 2 11.8 2 2 11.8 2 24s9.8 22 22 22c11 0 21-8 21-22 0-1.3-.2-2.7-.5-4z"/>
              </defs><clipPath id="b"><use xlink:href="#a" overflow="visible"/>
              </clipPath><path clip-path="url(#b)" fill="#FBBC05" d="M0 37V11l17 13z"/><path clip-path="url(#b)" fill="#EA4335" d="M0 11l17 13 7-6.1L48 14V0H0z"/>
              <path clip-path="url(#b)" fill="#34A853" d="M0 37l30-23 7.9 1L48 0v48H0z"/>
              <path clip-path="url(#b)" fill="#4285F4" d="M48 48L17 24l-4-3 35-10z"/>
            </svg>
          </div>
        </div>
      </div>
    </div>


  </div>
</div>
  
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import AccountErrorList from '@/views/account/components/AccountErrorList.vue'

export default {
  name: 'LoginView',
  components: {
    AccountErrorList,
  },
  data() {
      return {
 
    }
  },
  computed: {
    ...mapGetters(['authError'])
  },
  methods: {
    ...mapActions(['login'])
  },

}


</script>


<style scoped>
.hero {
  background-image: url("@/assets/login5.png");
  /* background: #f1c4d9; */
  width: 100vw;
  height: 90vh;
  position: absolute;
  /* opacity: 0.9; */
  object-fit: cover;
}

.hero-content {
  margin: 5%;
  /* display: flex; */
  /* flex-flow: row wrap; */
  /* justify-content: space-evenly; */
  /* border: 0rem; */
  /* border-radius: 20px 20px 20px 20px; */
  /* background: #ffff; */

}

.bg-img {
  /* margin: 10%; */
  /* float: left; */
}

.card {
  margin-right: 9%;
  margin-top: 3%;
  width: 29%;
  float: right;
  border-radius: 5% 5% 5% 5%;
}

.card-body {
  padding: 5%;  
}

.form-control {
  padding: 3%;
}

.text-center {
  margin: 10%;
}

.upper-text {
  margin: 3%;
  font-size: 3.2em;
  color: #f1c4d9;

}

.lower-text {
  font-size: 1.5em;
  color: #2657a7;

}

label {
  justify-content: center;
  font-size: 1em;
}

.google-icon {
  display: flex;
  justify-content: center;
}


svg {
  width: 13%;
  height: 13%;
}
</style>