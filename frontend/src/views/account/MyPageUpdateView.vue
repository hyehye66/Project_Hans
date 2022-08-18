<template>
<nav-bar></nav-bar>
  <div class="container">

    <div class="box relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg bg-white rounded">       
      <div class="px-4 py-5 flex-auto" style="width: 70%;">
        <div class="content tab-space" >
          <h1>회원정보 변경</h1>
          <br>
          <!-- <div>
          <p>
          <img class="icon" src="@/assets/improvement.png" alt="">
          이메일  {{profile.email}}
          </p>
          <div class="form-item" >
            <label for="nickname"  >닉네임 :</label>
            <input v-model="profile.nickname" type="text" id="nickname" required />
          </div>
          <div class="form-item">
            <label for="introduction" style="margin-right:5px">자기소개 :</label>
            <input v-model="profile.introduction" type="text" id="introduction" required />
          </div> -->
  

          <div class="mb-3">
            <label for="exampleFormControlInput1" class="form-label">
              <AcademicCapIcon />           
              <!-- <span>e-mail</span> -->
              <b>이메일</b>
              <br>
              <b>{{profile.email}}</b>
            </label>            
          </div>

          <div class="mb-3">
            <label for="nickname" class="form-label">
              <PencilAltIcon />            
              <b>닉네임</b>
            </label>
            <input type="email" class="form-control" id="nickname" v-model="this.$store.state.member.profile.nickname">
          </div>
          <div class="mb-3">
            <label for="introduction" class="form-label">
              <PencilAltIcon />         
              <b>자기소개</b>
              </label>
            <textarea class="form-control" rows="3"
            v-model="profile.introduction" type="text" id="introduction" required></textarea>
          </div>




        <!-- <div class="form-control w-full max-w-xs">
          <label class="label">
            <span class="label-text">자기소개</span>
            <span class="label-text-alt"></span>
          </label>
          <input type="text" id="introduction"
            class="input input-bordered w-full max-w-xs"              
            v-model="profile.introduction" required />

          <label class="label">
            <span class="label-text-alt">Alt label</span>
            <span class="label-text-alt">Alt label</span>
          </label>
        </div> -->


        <!-- <div class="input-group">
          <span class="input-group-text">자기소개</span>
          <textarea class="form-control" aria-label="With textarea" v-model="profile.introduction" type="text" id="introduction" required></textarea>
        </div> -->

  

    
    <!-- <button class="btn glass" @click="clickUpdate">수정하기</button> -->
    <!-- <button type="button" class="btn btn-primary" @click="clickUpdate">수정하기</button> 
    <button type="button" class="btn btn-danger" @click="clickwithdrawal">탈퇴하기</button> -->




          <div class="buttons">
            <button class="mt-5" @click="clickUpdate">
              <span class='btn-animate'>수정하기</span>
            </button>
            <button class="mt-5" @click="clickwithdrawal">
              <span class='btn-animate'>탈퇴하기</span>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
  

</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import { PencilAltIcon, AcademicCapIcon } from '@heroicons/vue/outline';
import NavBar from "@/components/NavBar.vue";

export default {
  name: 'MyPageUpdateView',
  components: {
    PencilAltIcon,
    AcademicCapIcon,
    NavBar,
  },

  computed: {
    ...mapGetters(['profile'])
  },
  methods: {
    ...mapActions(['fetchProfile','withdrawal','updateProfile']),
    clickUpdate(){
      var bool = confirm('수정하시겠습니까?')
      const payload = {
            nickname : this.profile.nickname,
            introduction : this.profile.introduction
        }
      if (bool === true){
      this.updateProfile(payload)}    
    },
    clickwithdrawal(){
      var bool2 = confirm('탈퇴하시겠습니까?')
    
    if (bool2 === true){
      this.withdrawal()} 
    },
  created() {
    this.fetchProfile()
    },
  },
}
</script>

<style scoped>
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

.content {
  /* display: flex; */
  /* justify-content: space-around; */
}

.text-box {
  float: right;
  flex-flow: column wrap;
}

.form-label {

}

svg {
  width: 10%;
  height: 10%;
}

b {
  font-size: 1.2em;
}

buttons {
  margin: 3%;
  /* float: right; */
  display: flex;
  flex-flow: row wrap;
  justify-content: space-around;
  float: right;
}
button {
  padding-left: 3%;
  padding-right: 3%;
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