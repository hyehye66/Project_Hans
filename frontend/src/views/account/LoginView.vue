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