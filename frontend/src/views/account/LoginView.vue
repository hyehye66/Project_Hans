<template>
<nav-bar />
<!-- <account-error-list v-if="authError"></account-error-list> -->
<!-- <img :src="require(`@/assets/65837-clouds-in-the-sky (1).gif`)" alt=""> -->

<!-- <img src="@/assets/login_24.jpg" alt="..." id="login_screen"> -->

<div class="hero min-h-screen bg-base-200">
  <div class="hero-content flex-col lg:flex-row-reverse">
    <!-- <div class="main-img">
      <img src="@/assets/login4.png" alt="login3">
    </div> -->

    <div class="card flex-shrink-0 w-full max-w-sm shadow-2xl bg-base-100 col-md-5">
      <div class="card-body">
        <div class="text-center lg:text-left">
          <h1 class="upper-text text-5xl font-bold">Login now!</h1>
          <p class="lower-text py-6">재미난 게임을 통해 한국어 실력을 향상시켜보세요.</p>
          <!-- <p class="lower-text py-6">재미난 게임을 통해 </p>
          <p class="lower-text py-6">한국어 실력을 향상시켜보세요</p> -->
        </div>
          <label class="label">
            소셜 SNS로 시작하기
          </label>
        <div class="form-control">      
          <div class="google-icon">
            <div>
              <div id="signin_button"></div>
            </div>
          </div>
        </div>
      </div>
    </div>


  </div>
</div>
  
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import NavBar from "@/components/NavBar.vue";
import AccountErrorList from '@/views/account/components/AccountErrorList.vue'

export default {
  name: 'LoginView',
  components: {
    NavBar,
    // AccountErrorList,
  },
  data() {
      return {
 
    }
  },
  computed: {
    ...mapGetters(['authError'])
  },
  methods: {
    ...mapActions(['login']),

          handleCredentialResponse(response) {
                const responsePayload = this.decodeJwtResponse(response.credential)
                this.login(responsePayload.email)
                this.email = responsePayload.email
                this.fullname = responsePayload.name
                this.profile = responsePayload.picture

            },

            decodeJwtResponse (token) {
            var base64Url = token.split('.')[1];
            var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''))

    return JSON.parse(jsonPayload);
      },
        },
        mounted: function () {
            
            let googleScript = document.createElement('script');
            googleScript.src = 'https://accounts.google.com/gsi/client';
            document.head.appendChild(googleScript);
               console.log(window.google);
                window.google.accounts.id.initialize({
                    client_id: "772832939234-780cgu6oljtrf34atfes9679m50fihp9.apps.googleusercontent.com",
                    callback: this.handleCredentialResponse
                });
                window.google.accounts.id.renderButton(
                    
                    document.getElementById("signin_button")
                    ,
                    { theme: "outline", size: "medium"}  // customization attributes
                );

        },

}


</script>


<style scoped>


.hero {
  background-image: url("@/assets/login5.png");
  /* background-image : url("@/assets/login_24.jpg"); */
  background-size: cover;
  /* background: #f1c4d9; */
  width: 100vw;
  height: 90vh;
  position: absolute;
  /* opacity: 0.9; */
  /* object-fit: cover; */
}

.hero-content {
  margin: 3%;
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
  margin-right: 8%;
  margin-top: 5%;
  width: 29%;
  float: right;
  border-radius: 5% 5% 5% 5%;
  /* background-color: rgba( 201, 212, 231, 0.6 ); */
  background-color: transparent;
}

.card-body {
  padding: 5%;
  flex-flow: column wrap;
  display: flex;
	justify-content: center;
	align-self: center;	
}

.form-control {
  /* left: 50%;
  right: 50%; */
  padding: 3%;
  /* background-color: rgba( 201, 212, 231, 0.3 ); */
  background-color: transparent;
  width: 80%;
  flex-flow: column wrap;
  display: flex;
	justify-content: center;
	align-self: center;
  border: transparent;
}

.text-center {
  margin: 10%;
}

.upper-text {
  margin-top: 3%;
  margin-bottom: 5%;
  font-size: 3.5em;
  color: #f1c4d9;

}

.lower-text {
  /* margin-top: 3%;
  margin-bottom: 3%; */
  font-size: 1.7em;
  color: #2657a7;

}

label {
  justify-content: center;
  font-size: 1.2em;
  color: #ffff;
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