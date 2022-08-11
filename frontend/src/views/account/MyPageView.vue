<template>
  <div class="container">

    <div class="box relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg bg-white rounded">       
      <div class="px-4 py-5 flex-auto">
        <div class="content tab-space" >

          <div class="my-avatar col-md-4">
            <img src="@/assets/jackalope.png" alt="default">
            <!-- <v-select :options="items" label="title" v-model="selectedAvatar">
              <template v-slot:option="option">
                <span :class="option.icon"></span>
                <img class="avatar" :src="option.img" />
                {{ option.title }}
              </template>
            </v-select> -->


            <!-- <select v-model="onePick">
              <option v-for="avatar in avatarList" :key="avatar.value">
                {{ avatar.title }}
                <img v-if="avatar.value === 0" src="@/assets/flag.png" alt="">
              </option>
            </select> -->

            <!-- <div v-model=""></div>


            <select :options="items" class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" v-model="selectedAvatar">
              <option selected>당신의 캐릭터를 골라보세요</option>
              <option class="avatar1" value="1">
                <img class="avatar-img" :src="option.img" /> {{ option.title }}  
              </option>        
              <option class="avatar2" value="2">
                <img class="avatar-img" :src="option.img" /> {{ option.title }}
              </option>
              <option class="avatar3" value="3">avatar3</option>
              <option class="avatar4" value="4">avatar4</option>
            </select> -->

          </div>         

          
          <div class="text-box">
            <!-- <div class="update-btn"> -->
              <button @click="updateMypage">수정하기</button>
            <!-- </div> -->                
            <br>
            <br>
            <div class="card">
              <div class="card-header">                
                <p>이메일 : {{profile.email}}</p>
                <p>닉네임 : {{profile.nickname}}</p>
                <p>자기소개 : {{profile.introduction}}</p>
              </div>


              <table class="table table-striped table-hover">
                  <thead>
                    <tr>
                      <th scope="col">게임</th>
                      <!-- &nbsp; -->
                      <th scope="col">점수</th>
                      <th scope="col">순위</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <th scope="row">낱말 퀴즈</th>
                      <td>{{profile.rankings[1].score}}</td>
                      <td>{{profile.rankings[1].tier}}</td>
                    </tr>
                    <tr>
                      <th scope="row">몸으로 말해요</th>
                      <td>{{profile.rankings[2].score}}</td>
                      <td>{{profile.rankings[2].tier}}</td>
                    </tr>
                  </tbody>
                </table>

              
            </div>             
          </div>      

        </div>
      </div>
    </div>
  </div>
  

</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'MyPageView',
  data () {
    return {
      // avatarList: [
      //   {
      //   title: '캐릭터를 선택하세요',
      //   value: '0',
      //   img: require('@/assets/flag.png'),
      //   },
      //   {
      //   title: 'bear',
      //   value: '1',
      //   img: require('@/assets/bear.png'),
      //   },
      //   {
      //   title: 'cat',
      //   value: '2',
      //   img: require('@/assets/cat.png')
      //   },
      //   {
      //   title: 'dog',
      //   value: '3',
      //   img: require('@/assets/dog.png')
      //   },
      //   {
      //   title: 'jackalope',
      //   value: '4',
      //   img: require('@/assets/jackalope.png')
      //   },
      //   {
      //   title: 'rabbit',
      //   value: '5',
      //   img: require('@/assets/rabbit.png')
      //   },
      // ],
      // onePick: null,
      
    }
  },  
  computed: {
    ...mapGetters(['profile']),

    // items () {
    //   return this.options.filter(i => i.title !== this.selectedAvatar?.title)
    // },
    
  },  
  methods: {    
    ...mapActions(['fetchProfile']),
    updateMypage () {
      this.$router.push('/mypage/change');
    },
  },
  created() {
    this.fetchProfile()
  },
  mounted() {
      
  },
}
</script>


<style scoped>
.container {
  display: flex;
  justify-content: center;
  margin-top: 4%;
}

.box {
  width: 70%;
  display: flex;
  justify-content: center;
  
}

.content {
  display: flex;
  justify-content: space-around;
}

.my-avatar {
  float: left;
  margin: 6%;

}

.text-box {
  float: right;
  flex-flow: column wrap;
}

button {
  margin: 3%;
  float: right;
}

table {
  /* display: flex; */
  /* justify-content: center; */
  text-align: center;
}

.card-header {
  padding-bottom: 4%;
}

.card-header p {
  padding: 2%;
  font-size:1.3em;
}


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