<template>
<nav-bar></nav-bar>
  <div class="container flex flex-wrap">
    <div class="w-full">
      <ul class="flex mb-0 list-none flex-wrap pt-3 pb-4 flex-row">
        <li class="-mb-px mr-2 last:mr-0 flex-auto text-center" style="cursor:pointer">
          <a class="category-tab text-xs font-bold uppercase px-5 py-3 shadow-lg rounded block leading-normal" 
            v-on:click="toggleTabs(1)" 
            v-bind:class="{'text-newOrange bg-white': openTab !== 1, 
            'text-white bg-newOrange': openTab === 1}"
            @click="getRanking(0)">
            <i class="fas fa-space-shuttle text-base mr-1"></i> 낱말게임
          </a>
        </li>
        <li class="-mb-px mr-2 last:mr-0 flex-auto text-center" style="cursor:pointer">
          <a class="category-tab text-xs font-bold uppercase px-5 py-3 shadow-lg rounded block leading-normal" 
            v-on:click="toggleTabs(2)" 
            v-bind:class="{'text-blue-500 bg-white': openTab !== 2, 
            'text-white bg-blue-500': openTab === 2}"
            @click="getRanking(0)">
            <i class="fas fa-cog text-base mr-1"></i> 몸으로 말해요
          </a>
        </li>
      </ul>

      <div class="tab-box relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded">
       <!-- bg-white -->
        <div class="px-4 py-5 flex-auto" style="width: 80%">
          <div class="tab-content tab-space" >          
            <div v-bind:class="{'hidden': openTab !== 1, 'block': openTab === 1}">
              <words-search-bar />

              <!-- 새 리스트 -->
              <div class="overflow-x-auto">
                <table class="table table-zebra w-full">
                  <!-- head -->
                  <thead>
                    <tr>
                      <!-- <th></th> -->
                      <th style="font-size: 1.1rem">순위</th>
                      <th style="font-size: 1.1rem">닉네임</th>
                      <th style="font-size: 1.1rem">점수</th>
                      <th style="font-size: 1.1rem">티어</th>
                    </tr>
                  </thead>
                  <tbody v-if="!this.$store.state.ranks.isSearch">
                    <!-- row 1 -->
                    <tr v-for="rankData in rankDataList" :key="rankData.rankingSequence">
                      <th><rank-items :rankingSequence="(this.page*10)+rankDataList.indexOf(rankData)+1"/></th>
                      <td><rank-items :nickname="rankData.nickname"/></td>
                      <td><rank-items :score="rankData.score" /></td>
                      <td><rank-items :tier="rankData.tier" /></td>
                    </tr>
                  </tbody>
                  <tbody v-if="this.$store.state.ranks.isSearch">
                    <!-- row 1 -->
                    <tr>
                      <th><rank-items :rankingSequence="this.$store.state.ranks.rankings.rank"/></th>
                      <td><rank-items :nickname="this.$store.state.ranks.rankings.nickname"/></td>
                      <td><rank-items :score="this.$store.state.ranks.rankings.score" /></td>
                      <td><rank-items :tier="this.$store.state.ranks.rankings.tier" /></td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <nav aria-label="Page navigation">
                <ul class="pagination">
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous"  @click="getRanking(0), this.page=0">
                      <span aria-hidden="true">&laquo;</span>
                    </a>
                  </li>
                  <li class="page-item"><a class="page-link" @click="getRanking(0), this.page=0" href="#">1</a></li>
                  <li class="page-item"><a class="page-link" @click="getRanking(1), this.page=1" href="#">2</a></li>
                  <li class="page-item"><a class="page-link" @click="getRanking(2), this.page=2" href="#">3</a></li>
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next" @click="getRanking(2), this.page=2">
                      <span aria-hidden="true">&raquo;</span>
                    </a>
                  </li>
                </ul>
              </nav>
            </div>

            
            <div v-bind:class="{'hidden': openTab !== 2, 'block': openTab === 2}">
              <body-search-bar />
              <div class="overflow-x-auto">
                <table class="table table-zebra w-full">
                  <!-- head -->
                  <thead>
                    <tr>
                      <!-- <th></th> -->
                      <th>순위</th>
                      <th>닉네임</th>
                      <th>점수</th>
                      <th>티어</th>
                    </tr>
                  </thead>
                  <tbody v-if="!this.$store.state.ranks.isSearch">
                    <!-- row 1 -->
                    <tr v-for="rankData in rankDataList" :key="rankData.rankingSequence">
                      <th><rank-items :rankingSequence="(this.page*10)+rankDataList.indexOf(rankData)+1"/></th>
                      <td><rank-items :nickname="rankData.nickname"/></td>
                      <td><rank-items :score="rankData.score" /></td>
                      <td><rank-items :tier="rankData.tier" /></td>
                    </tr>
                  </tbody>
                  <tbody v-if="this.$store.state.ranks.isSearch">
                    <!-- row 1 -->
                    <tr>
                      <th><rank-items :rankingSequence="this.$store.state.ranks.rankings.rank"/></th>
                      <td><rank-items :nickname="this.$store.state.ranks.rankings.nickname"/></td>
                      <td><rank-items :score="this.$store.state.ranks.rankings.score" /></td>
                      <td><rank-items :tier="this.$store.state.ranks.rankings.tier" /></td>
                    </tr>
             
                  </tbody>
                  
                </table>
              </div>
              <nav aria-label="Page navigation">
                <ul class="pagination">
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous" @click="getRanking(0), this.page=0">
                      <span aria-hidden="true">&laquo;</span>
                    </a>
                  </li>
                  <li class="page-item"><a class="page-link" @click="getRanking(0), this.page=0" href="#">1</a></li>
                  <li class="page-item"><a class="page-link" @click="getRanking(1), this.page=1" href="#">2</a></li>
                  <li class="page-item"><a class="page-link" @click="getRanking(2), this.page=2" href="#">3</a></li>
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next" @click="getRanking(2), this.page=2">
                      <span aria-hidden="true">&raquo;</span>
                    </a>
                  </li>
                </ul>
              </nav>
            </div>



              <!-- <ul class="list-group" style="width: 750px" v-for="rankData in rankDataList" :key="rankData.rankingSequence">
                <li class="list-group-item">
                  <rank-items :rankingSequence="rankData.rankingSequence"/>                  
                  <rank-items :nickname="rankData.nickname"/>                  
                  <rank-items :score="rankData.score" />            
                  <rank-items :tier="rankData.tier" />
                </li>
              </ul>  
            </div> -->


            <!-- <div v-bind:class="{'hidden': openTab !== 3, 'block': openTab === 3}" @click="getRanking()">
              <body-search-bar />
              <div class="overflow-x-auto">
                <table class="table table-zebra w-full">
                  <thead>
                    <tr>
                      <th>순위</th>
                      <th>닉네임</th>
                      <th>점수</th>
                      <th>티어</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="rankData in rankDataList" :key="rankData.rankingSequence">
                      <th><rank-items :rankingSequence="rankData.rankingSequence"/></th>
                      <td><rank-items :nickname="rankData.nickname"/></td>
                      <td><rank-items :score="rankData.score" /></td>
                      <td><rank-items :tier="rankData.tier" /></td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div> -->

          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import NavBar from "@/components/NavBar.vue";
import WordsSearchBar from '@/views/rank/components/WordsSearchBar.vue'
import BodySearchBar from '@/views/rank/components/BodySearchBar.vue'
import RankItems from '@/views/rank/components/RankItems.vue'
import axios from 'axios';
import {mapGetters, mapState} from 'vuex';

export default {
  name: 'RankTab',
  components: {
    WordsSearchBar,
    BodySearchBar,
    RankItems,  
    NavBar,
  },  
  data() {
    return {
      ...mapState(['rankings']),
      openTab: 1,
      index: 0, 
      rankDataList : [], 
      isSearch : true,
      page : 0,
    }
  },
  // props : {
  //   rankDataList : Array,
  // },
  methods: {
    toggleTabs: function(tabNumber){
      this.openTab = tabNumber
    },
    // firstShowRanking () {
      

    // },

    // 탭을 누를 때마다 axios요청하기
    getRanking (page) {
      this.$store.state.ranks.isSearch = false
      this.$store.state.ranks.notMember = false
      console.log(this.$store.state.ranks.notMember)
      axios ( {
        url: `/api/rankings/${this.openTab+1}?page=${page}`,
        // ?page=${this.index}
        headers: this.authHeader,
        method: "get",
        // 수정
        // modeSequence별로 데이터 받아오기
        data: {
          modeSequence: this.openTab+1,
          
        }
      }
        

      )

      // .then (res=>console.log(res.data))

      //인자로 넣어주는 함수니 콜백함수. 함수가 메서드가 아니므로 this는 method다. 콜백함수는 무조건 화살표쓴다
      //.then(res => this.photos = res.data ) //리턴 없고 인자도 하나니 이렇게 가능하다
      // .then((res) => {
      //   this.rankData = res.data.data
      //   }) 
      //   .catch(err => console.error(err)) 
        
      .then (res => {
        this.rankDataList = res.data.data.rankings.content       
        console.log(this.rankDataList)} )
      
      .catch(err => console.error(this.authHeader))               
      
    },


  },
  created () {
    this.getRanking()
  },
  computed: {
    ...mapGetters(["authHeader"])
  }

}
</script>

<style scoped>
.container {  
  display: flex;
  justify-content: center;
  align-items: center;
  width: 70%;
  margin-top: 5%;
}

.category-tab {
  background: #dfdfdf;
  color: #f16b48;
  font-size: 1.2rem;
}

.category-tab:hover {
  color: #f38e7b;
}

.tab-box {
  /* background: #f16b48; */

  display: flex;
  flex-flow: row wrap;
  justify-content: center;
  align-self: center;
}

title-list-group {
  display: flex;
  flex-flow: row wrap;
  justify-content: space-between;
  align-self: center;

}

words-search-bar {  
  /* display: flex; */
	/* flex-direction: row; */
	/* flex-flow: row wrap; */
	/* justify-content: space-evenly; */
	/* align-self: center; */
	/* justify-content: center; */
	/* text-align: center; */
	/* width: 400;
	height: 40; */
  /* float: right; */

}

/* search-rank svg {
  vertical-align: middle;
} */

.pagination {
  margin-top: 4%;
  display:flex;
  justify-content: center;
  align-items: center;
  align-self: center;
  
}

.page-item {
  color: #f16b48;

}



</style>