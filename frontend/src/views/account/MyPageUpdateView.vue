<template>
  <h1>회원정보 변경</h1>
  <div>
    <div>
    <p>이메일 : {{profile.email}}</p>
       <div class="form-item" >
          <label for="nickname"  >닉네임 :</label>
          <input v-model="profile.nickname" type="text" id="nickname" required />
        </div>
        <div class="form-item">
          <label for="introduction" style="margin-right:5px">자기소개 :</label>
          <input v-model="profile.introduction" type="text" id="introduction" required />
        </div>
    </div>
    <button type="button" class="btn btn-primary" @click="clickUpdate">수정하기</button> 
    <button type="button" class="btn btn-danger" @click="clickwithdrawal">탈퇴하기</button>

  </div>
  

</template>

<script>

import { mapGetters, mapActions } from 'vuex'


export default {
  name: 'MyPageUpdateView',

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