<template>
  <h1>마이페이지</h1>
  <div>
    <div>
      <p>이메일 : {{profile.email}}</p>
      <p>닉네임 : {{profile.nickname}}</p>
      <p>자기소개 : {{profile.introduction}}</p>
      <p>낱말게임 점수 : {{profile.rankings[1].score}} 티어 : {{profile.rankings[1].tier}}</p>
      <p>몸으로 말해요 점수 : {{profile.rankings[2].score}} 티어 : {{profile.rankings[1].tier}}</p>
    </div>
    <router-link :to="{ name: 'MyPageUpdateView'}">
          <button>수정하기</button>
        </router-link>
  </div>
  

</template>

<script>
import { mapGetters, mapActions } from 'vuex'


export default {
  name: 'MyPageView',
  computed: {
    ...mapGetters(['profile'])
  },
  methods: {
    ...mapActions(['fetchProfile',]),

  },
  created() {
    this.fetchProfile()
  },
}
</script>


<style>


.mymy{
  margin: 50px;
}
.changebtn
{
  background : #1A1D29;
  border: white;
  color: white;
}
.accordion-item{
  background : #1A1D29;
}

.accordion-button{
  background : #1A1D29;
  color: white;
}


.changepasw{
  float: right;
 
}
</style>