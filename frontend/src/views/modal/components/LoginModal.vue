<template>
<div v-if="open" class="modal"  tabindex="-1" >
 
  <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">로그인</h5>
        <button @click="$emit('update:open', !open)" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        구글 로그인
          <!-- <img class = 'google-img' src="@/assets/google_logo.png" width= 50px height="auto" alt="..."   @click="googleLogin" style="cursor: pointer">  -->
          <div>
        <div id="signin_button"></div>
    </div>
          <!-- <img class = 'google-img' src="@/assets/google_logo.png" width= 50px height="auto" alt="..."  style="cursor: pointer"> -->
        </div>
      <div class="modal-footer">
        <button @click="$emit('update:open', !open)" type="button" class="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded" data-bs-dismiss="modal">Close</button>        
      </div>
    </div>
</div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  props :{
    open : {
      type : Boolean,
      require : true,
      default : false
      }
  },
  methods : {
    ...mapActions(['login']),

    isClose() {
      console.log(this.open)
      this.$emit('update:open', false)
    },
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
            let googleScript = document.createElement('script');
            googleScript.src = 'https://accounts.google.com/gsi/client';
            document.head.appendChild(googleScript);
            
            window.addEventListener('load', () => {
                // console.log(window.google);
                window.google.accounts.id.initialize({
                    client_id: "772832939234-780cgu6oljtrf34atfes9679m50fihp9.apps.googleusercontent.com",
                    callback: this.handleCredentialResponse
                });
                window.google.accounts.id.renderButton(
                    document.getElementById("signin_button"),
                    { theme: "outline", size: "large" }  // customization attributes
                );
            })
        }
}
</script>


<style scoped>
.modal { 
  position: absolute;
  display : flex; 
  top: 20%;
  left: 50%;
  width: 300px;
  margin-left: -150px; 
  height : auto;
  z-index: 90;
  visibility: visible;
  opacity: 100;
}
.google-img{
  width : 50px;
  height : auto; 
}
</style>
