<template>
<div class="navbar sticky-top bg-newGrey1 flex-nowrap">
    
  <div class="navbar-start">
    <div class="dropdown">
      <label tabindex="0" class="btn btn-ghost lg:hidden">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h8m-8 6h16" /></svg>
      </label>
      <ul tabindex="0" class="menu menu-compact dropdown-content mt-3 p-2 shadow bg-base-100 rounded-box w-52 text-black">
        <li tabindex="0">
          <router-link :to="{name:'Home'}" >
            학습
            <svg class="fill-current" xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24"><path d="M7.41,8.58L12,13.17L16.59,8.58L18,10L12,16L6,10L7.41,8.58Z"/></svg>
            </router-link>          
        <ul class="p-2 drop-box bg-newGrey1">
          <li><router-link :to="{name:'CommunicationView'}" >상황별 한국어 회화</router-link></li>
          <li><router-link :to="{name: 'DictationView'}">받아쓰기</router-link></li>
        </ul>
      </li>
      <li tabindex="0">
        <router-link :to="{name:'Home'}">
          게임
          <svg class="fill-current" xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24"><path d="M7.41,8.58L12,13.17L16.59,8.58L18,10L12,16L6,10L7.41,8.58Z"/></svg>
        </router-link>
        <ul class="p-2 drop-box bg-newGrey1">
          <li><router-link :to="{name:'WordsMainView'}">낱말 퀴즈</router-link></li>
          <li><router-link :to="{name:'BodyMainView'}">몸으로 말해요</router-link></li>
        </ul>
      </li>
      <li><router-link :to="{name:'ChatMainView'}" >대화</router-link></li>
      <li><router-link :to="{name:'RankingView'}" >랭킹</router-link></li>
      <li v-if="isLoggedIn"><router-link :to="{name:'MyPageView'}" >마이페이지</router-link></li>
      </ul>
    </div>
    <router-link :to="{name:'Home'}" ><img src="../assets/logo.png" alt="#" class="logo-image"></router-link>
  </div>
  <div class="navbar-center hidden lg:flex text-newBlue1 font-black">
    <ul class="menu menu-horizontal p-0">
      <li class="mx-4" tabindex="0">
        <router-link :to="{name:'Home'}" >
          학습
          <svg class="fill-current" xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24"><path d="M7.41,8.58L12,13.17L16.59,8.58L18,10L12,16L6,10L7.41,8.58Z"/></svg>
        </router-link>
        <ul class="p-2 drop-box bg-newGrey1">
          <li><router-link @click="comClick" :to="{name:'CommunicationView'}" >상황별 한국어 회화</router-link></li>
          <li><router-link @click="comClick" :to="{name: 'DictationView'}">받아쓰기</router-link></li>
        </ul>
      </li>
      <li class="mx-4" tabindex="0">
        <router-link :to="{name:'Home'}">
          게임
          <svg class="fill-current" xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24"><path d="M7.41,8.58L12,13.17L16.59,8.58L18,10L12,16L6,10L7.41,8.58Z"/></svg>
        </router-link>
        <ul class="p-2 drop-box bg-newGrey1">
          <li><router-link :to="{name:'WordsMainView'}">낱말 퀴즈</router-link></li>
          <li><router-link :to="{name:'BodyMainView'}">몸으로 말해요</router-link></li>
        </ul>
      </li>
      <li class="mx-4"><router-link :to="{name:'ChatMainView'}" >대화</router-link></li>
      <li class="mx-4"><router-link :to="{name:'RankingView'}" >랭킹</router-link></li>
      <li class="mx-4" v-if="isLoggedIn"><router-link :to="{name:'MyPageView'}" >마이페이지</router-link></li>
      
    </ul>
  </div>
  <div class="navbar-end">
    <button class="welcome" v-if="isLoggedIn"> <router-link :to="{name:'MyPageView'}" >{{thisNick}} 님 환영합니다</router-link></button>
      <button v-if="!isLoggedIn" 
      class="bg-newBlue1 hover:bg-newBlue2 text-white font-bold py-2 px-4 rounded"  @click="login"
      style="cursor: pointer">
      Login
      </button>
      <button v-if="isLoggedIn" 
      class="bg-newBlue1 hover:bg-newBlue2 text-white font-bold py-2 px-4 rounded" @click="logout" 
      style="cursor: pointer">
      Logout
      </button>

    <!-- <button v-if="!isLoggedIn" class="bg-newBlue1 hover:bg-newBlue2 text-white font-bold py-2 px-4 rounded" @click="isOpen" style="cursor: pointer">Login</button>
    <button v-if="isLoggedIn" class="bg-newBlue1 hover:bg-newBlue2 text-white font-bold py-2 px-4 rounded" @click="logout" style="cursor: pointer">Logout</button> -->


 
  </div>


</div>


        
</template>

<script>

import { mapActions, mapGetters } from 'vuex'
export default {
  name : 'NavBar',
  computed: {
    ...mapGetters(['isLoggedIn','profile']),
  },
  data(){
    return {
      loginOpen : false,
      thisNick : this.$store.state.member.profile.nickname
    }
  },
  methods : {
    ...mapActions(['logout']),
    login (){
      this.$router.push('/login')
    },
    comClick(){
      if(!this.isLoggedIn){
        alert('로그인이 필요합니다!!')
        this.$router.push('/login')
      }
    }
    
  },
}
</script>

<style scoped>
.logo-image{
  width : 30%;
  height: auto;
  top: 50%;
}

.drop-box{
  z-index: 999;
}

.welcome{
  padding : 20px
}


</style>
