<template>
    <div>
        <div id="signin_button">구글로 로그인</div>
        
    </div>

    <div>
    <p>로그인하신 아이디는 : {{email}}</p>
    <p>사용자 이름은 : {{fullname}}</p>
    <p>로그인하신 프로필이미지는 : <img :src="profile"></p>
      </div>
</template>

<script>

    export default {
      data(){
        return{
        email : '',
        fullname : '',
        profile : '',
        } 
      },
        components: {

        },
        methods: {
           
            handleCredentialResponse(response) {
                const responsePayload = this.decodeJwtResponse(response.credential)
                console.log(response.credential)
                console.log( responsePayload)
                console.log("ID: " + responsePayload.sub);
                console.log('Full Name: ' + responsePayload.name);
                console.log('Given Name: ' + responsePayload.given_name);
                console.log('Family Name: ' + responsePayload.family_name);
                console.log("Image URL: " + responsePayload.picture)
                console.log("Email: " + responsePayload.email);
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
            
        }
    }
</script>




<!-- <template>
  <div>
    <h1>받아쓰기</h1>
    <button @click="googleLogin">로그인버튼</button>
    <div>

      <a :href = $store.state.member.msg>로그인</a>
      
    </div>
  </div>
  

</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name : 'DictationView',
  components : {

  },

  computed: {

    ...mapState([{msg : 'member.msg'}])
  },
  methods : {
    ...mapActions(['login']),
    googleLogin() {
      console.log('클릭이벤트')
      this.$store.dispatch('login')
    },
     
  },

}
</script>

<style>

</style>
 -->


<!-- <template>
<GoogleLogin class="big-button" :params="params" :onSuccess="GoogleLoginSuccess" :onFailure="GoogleLoginFailure">구글 로그인</GoogleLogin>
  <GoogleLogin class="external_item" :params="params" :onSuccess="googleSuccess">
  <img src="@/assets/google_logo.png" />
  <b> Continue with Google</b>
</GoogleLogin>
</template>

<script>
import GoogleLogin from 'vue3-google-login'
const Google = {
  async GoogleLoginSuccess(googleUser){
    const req_body = {
      googleAccessToken: googleUser.Zi.access_token,
      name : googleUser.w3.ig,
      id: googleUser.w3.Eea,
      email: googleUser.w3.U3,
      profileIMG: googleUser.w3.paa,
      source: 'g',
    }
    await social_login(req_body)
  },
  GoogleLoginFailure(){
    LoginFailure();
    console.log('fail')
  },
}
export default {
  name: "DictationView",
  
  
  data() {
    return {
      params:{
        client_id: "908845611506-17n1k3isuiniproahm42389k4a381ge1.apps.googleusercontent.com",
      }
    }
  },
  methods: {
    GoogleLoginSuccess(googleUser){
      if (localStorage.getItem('JWT_token')) return alert('이미 로그인 되어 있습니다.')
      Google.GoogleLoginSuccess(googleUser)
    },
    GoogleLoginFailure(){
      Google.GoogleLoginFailure()
    }
  }
}
</script>

<style scoped>
 
</style> -->

<!-- <template> -->

    <!-- <button class="g-signin2" id="google-signin-btn" data-onsuccess="onSignIn">로그인</button> -->
    <!-- <div>
      <h1>받아쓰기</h1>
      <div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>
    </div> -->
  <!-- <button @click="onSignIn" data-onsuccess="onSignIn">구글로그인</button> -->
<!--   
</template> -->

<!-- <script>

export default {
  name:'DictationView',
  props:{
    msg:String
  },
  created(){
    window.onSignIn = this.onSignIn
  },
  methods:{
    onSignIn(googleUser) {
        // Useful data for your client-side scripts:
        var profile = googleUser.getBasicProfile();
        console.log("ID: " + profile.getId()); // Don't send this directly to your server!
        console.log('Full Name: ' + profile.getName());
        console.log('Given Name: ' + profile.getGivenName());
        console.log('Family Name: ' + profile.getFamilyName());
        console.log("Image URL: " + profile.getImageUrl());
        console.log("Email: " + profile.getEmail());

        // The ID token you need to pass to your backend:
        var id_token = googleUser.getAuthResponse().id_token;
        console.log("ID Token: " + id_token);
 
  }
},
}
</script>

<style scoped>

</style> -->

<!-- <template>
  <section class="test">
    
    <div id="g_id_onload"
     data-client_id="772832939234-780cgu6oljtrf34atfes9679m50fihp9.apps.googleusercontent.com"
     data-callback= handleCredentialResponse
     data-context="signin"
     data-login_uri="http://localhost:8080"
     data-auto_select="true"
     data-auto_prompt="false">
</div>
    <div class="g_id_signin"
     data-type="standard"
     data-size="large"
     data-theme="outline"
     data-text="sign_in_with"
     data-shape="rectangular"
     data-logo_alignment="left">
</div>
  </section>
</template>

<script>
export default {

methods:{
  handleCredentialResponse(response) {
     // decodeJwtResponse() is a custom function defined by you
     // to decode the credential response.
     const responsePayload = decodeJwtResponse(response.credential);

     console.log("ID: " + responsePayload.sub);
     console.log('Full Name: ' + responsePayload.name);
     console.log('Given Name: ' + responsePayload.given_name);
     console.log('Family Name: ' + responsePayload.family_name);
     console.log("Image URL: " + responsePayload.picture);
     console.log("Email: " + responsePayload.email);
  
},
mounted: function(){
  google.accounts.id.initialize({
   client_id:
    '772832939234-780cgu6oljtrf34atfes9679m50fihp9.apps.googleusercontent.com',
     callback: this.handleCredentialResponse,
   })
    google.accounts.id.prompt()
    }

  }
}
 
</script>

<style>

</style> -->

