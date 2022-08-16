<template>

  <div>
    <div class="flex justify-center">
      <div class="mb-3 xl:w-96">
        <div class="input-group relative flex flex-wrap items-stretch w-full mb-4 rounded">
          <!-- 검색창 -->
          <input type="search" 
            class="form-control relative flex-auto min-w-0 block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" 
            v-model="searchNickname"
            placeholder="낱말퀴즈 게임 순위를 알아보세요" 
            aria-label="Search" 
            aria-describedby="button-addon2"
            @keyup.enter="searchStart({searchNickname : searchNickname, modeSequence : 2})">
          <!-- 검색버튼 -->
          <span class="input-group-text flex items-center px-3 py-1.5 text-base font-normal text-gray-700 text-center whitespace-nowrap rounded" 
            id="basic-addon2"
            @click="searchStart({searchNickname : searchNickname, modeSequence : 2})" >
            <svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="search" class="w-4" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
              <path fill="currentColor" d="M505 442.7L405.3 343c-4.5-4.5-10.6-7-17-7H372c27.6-35.3 44-79.7 44-128C416 93.1 322.9 0 208 0S0 93.1 0 208s93.1 208 208 208c48.3 0 92.7-16.4 128-44v16.3c0 6.4 2.5 12.5 7 17l99.7 99.7c9.4 9.4 24.6 9.4 33.9 0l28.3-28.3c9.4-9.4 9.4-24.6.1-34zM208 336c-70.7 0-128-57.2-128-128 0-70.7 57.2-128 128-128 70.7 0 128 57.2 128 128 0 70.7-57.2 128-128 128z"></path>
            </svg>
          </span>
        </div>
      </div>
    </div>

    <!-- modeSequence 부분 확인요망 -->
    <!-- 기존 modeSequence와 받아온 데이터의 modeSequence 비교 -->
    <!-- this.modeSequece === resultData.mode.modeSequence -->
    <!-- :modeSequece === resultData.mode.modeSequence -->

    <!-- <div class="nickname-rank" v-if="this.$store.state.ranks.searchFinish===true && rankings.mode.modeSequence === this.modeSequence"> -->
      <!-- <search-result :rank="rank" />등
      <search-result :nickname="resultData.nickname" />
      <search-result :score="resultData.score" />
      <search-result :tier="resultData.tier" />                 -->
      
      <!-- <p>등수 : {{this.resultData.rank}}</p>
      <p>닉네임 : {{this.resultData.nickname}}</p>
      <p>점수 : {{this.resultData.score}}</p>
      <p>티어 : {{this.resultData.tier}}</p> -->
      
    <!-- </div> -->

  </div>
</template>

<script>
// import SearchResult from '@/views/rank/components/SearchResult.vue'
// import axios from 'axios'
import {mapGetters, mapActions, mapState} from 'vuex'

export default {
  name: 'WordsSearchBar',
  data() {
    return {
      ...mapState(['rankings']),
      searchNickname:'',
      modeSequence: 2,
      nickname: '',
      rank : 0,
      status : '',
    }
  },
  components: {
    // SearchResult,
  },
  props: {



  },
  methods: {
    ...mapActions(['searchStart']),
  },

    // searchStart () {
    //   axios ( {
    //       url: `/api/rankings/search?nickname=${this.searchNickname}&modeSequence=${this.modeSequence}`,
    //       headers: this.authHeader,
    //       method: "get",
    //       data: {
    //         nickname: this.searchNickname,
    //         modeSequence: this.modeSequence,
    //       },
    //     },
    //   ).then(res => {        
    //     this.resultData = res.data.data
    //     this.searchFinish = true
    //     this.searchNickname = ''
    //     this.status = res.data.status
    //     this.$store.state.ranks.isSearch = true
    //   }).catch(err => console.error(err))



      // if (this.searchNickname == '') {
      //   alert('존재하지 않는 닉네임입니다.')
      // } else {        
      //   axios ( {
      //     url: `/api/rankings/search?nickname=${this.nickname}&id=${this.modeSequence}`,
      //     headers: this.authHeader,
      //     method: "get",
      //     data: {
      //       searchNickname: this.searchNickname,
      //       modeSequence: this.modeSequence,
      //     },
      //   },
      // ).then(res => {        
      //   console.log(res.data.data)
      //   this.resultData = res.data.data
      //   this.searchFinish = true
      //   this.searchNickname = ''

      // }).catch(err => console.error(this.authHeader))

      // }      
  //   },
  // },

  // mounted () {
  //   this.searchStart()
  // },
  computed: {
    ...mapGetters(["authHeader"]),
    // 닉네임 갱신 함수

  }
}
</script>

<style scoped>
.nickname-rank {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 70%;
  margin: 3%;
}

.input-group {
  cursor: pointer;
}


</style>