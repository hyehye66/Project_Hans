<template>
<div v-if="open" class="modal"  tabindex="-1"  >
 
  <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">로그인</h5>
        <button @click="$emit('update:loginOpen', !loginOpen)" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        구글 로그인

        <!-- <div id="signin_button"></div> -->

          <!-- <img class = 'google-img' src="@/assets/google_logo.png" width= 50px height="auto" alt="..."   @click="googleLogin" style="cursor: pointer">  -->
           <!-- <img class = 'google-img' src="@/assets/google_logo.png" width= 50px height="auto" alt="..."  style="cursor: pointer"> -->
        </div>
      <div class="modal-footer">
        <button @click="$emit('update:loginOpen', !loginOpen)" type="button" class="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded" data-bs-dismiss="modal">Close</button>        
      </div>
    </div>
</div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  props :{
    loginOpen : Boolean
  },
      methods: {
        ...mapActions(['login']),

    isClose() {
      console.log(this.open)
      this.$emit('update:open', false)
    },
           
    //         handleCredentialResponse(response) {
    //             const responsePayload = this.decodeJwtResponse(response.credential)
    //             console.log(response.credential)
    //             console.log( responsePayload)
    //             console.log("ID: " + responsePayload.sub);
    //             console.log('Full Name: ' + responsePayload.name);
    //             console.log('Given Name: ' + responsePayload.given_name);
    //             console.log('Family Name: ' + responsePayload.family_name);
    //             console.log("Image URL: " + responsePayload.picture)
    //             console.log("Email: " + responsePayload.email);
    //             this.email = responsePayload.email
    //             this.fullname = responsePayload.name
    //             this.profile = responsePayload.picture
    //         },
    //         decodeJwtResponse (token) {
    //         var base64Url = token.split('.')[1];
    //         var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    //         var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
    //         return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    //     }).join(''))

    // return JSON.parse(jsonPayload);
    //   },
    //     },
    //     mounted: function () {
            
    //         let googleScript = document.createElement('script');
    //         googleScript.src = 'https://accounts.google.com/gsi/client';
    //         document.head.appendChild(googleScript);
    //            console.log(window.google);
    //             window.google.accounts.id.initialize({
    //                 client_id: "772832939234-780cgu6oljtrf34atfes9679m50fihp9.apps.googleusercontent.com",
    //                 callback: this.handleCredentialResponse
    //             });
    //             window.google.accounts.id.renderButton(
                    
    //                 document.getElementById("signin_button")
    //                 ,
    //                 { theme: "outline", size: "medium"}  // customization attributes
    //             );

        }
  
}
</script>


<style scoped>

.loginmodal { 
  position: absolute;
  display : flex; 
  top: 20%;
  left: 50%;
  margin-left: -150px; 
  width: 300px;
  height : 235px;
  z-index: 90;
  visibility: visible;
  opacity: 100;
  
}
.google-img{
  width : 50px;
  height : auto; 
}
.roomTitle{
  text-shadow: 5px 5px 70px rgba(190, 209, 212, 0.582);
  font-size: 70px;
  background: linear-gradient(to bottom,#a769d6 ,#6f92d8);
   -webkit-background-clip: text;
   -webkit-text-fill-color: transparent;
   
}
.modal_content {
  width: 38%;
  height:58vh;
  border-radius: 15px;
  background: rgba(58,40,106,1);
  position: relative;
  margin: 0 auto;
  margin-top: 40px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content:space-around;
  padding: 10px 0 10px 0;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
.row_box {
  width: 26vw;
  height: 60px;
  /* display: flex; */
  flex-direction: row;
  align-items: center;
}

.modal-footer {
  display: flex;
  justify-content: center;
}
</style>
