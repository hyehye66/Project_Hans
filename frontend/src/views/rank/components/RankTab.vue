<template>
  <div class="container flex flex-wrap">
    <div class="w-full">
      <ul class="flex mb-0 list-none flex-wrap pt-3 pb-4 flex-row">
        <li class="-mb-px mr-2 last:mr-0 flex-auto text-center" style="cursor:pointer">
          <a class="category-tab text-xs font-bold uppercase px-5 py-3 shadow-lg rounded block leading-normal" 
            v-on:click="toggleTabs(1)" 
            v-bind:class="{'text-newOrange bg-white': openTab !== 1, 
            'text-white bg-newOrange': openTab === 1}"
            @click="getRanking">
            <i class="fas fa-space-shuttle text-base mr-1"></i> 낱말게임
          </a>
        </li>
        <li class="-mb-px mr-2 last:mr-0 flex-auto text-center" style="cursor:pointer">
          <a class="category-tab text-xs font-bold uppercase px-5 py-3 shadow-lg rounded block leading-normal" 
            v-on:click="toggleTabs(2)" 
            v-bind:class="{'text-blue-500 bg-white': openTab !== 2, 
            'text-white bg-blue-500': openTab === 2}"
            @click="getRanking">
            <i class="fas fa-cog text-base mr-1"></i> 몸으로 말해요
          </a>
        </li>
        <li class="-mb-px mr-2 last:mr-0 flex-auto text-center" style="cursor:pointer">
          <a class="category-tab text-xs font-bold uppercase px-5 py-3 shadow-lg rounded block leading-normal" 
            v-on:click="toggleTabs(3)" 
            v-bind:class="{'text-blue-500 bg-white': openTab !== 3, 
            'text-white bg-blue-500': openTab === 3}"
            @click="getRanking">
            <i class="fas fa-briefcase text-base mr-1"></i> 가사퀴즈
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
                      <th>순위</th>
                      <th>닉네임</th>
                      <th>점수</th>
                      <th>티어</th>
                    </tr>
                  </thead>
                  <tbody>
                    <!-- row 1 -->
                    <tr v-for="rankData in rankDataList" :key="rankData.rankingSequence">
                      <th><rank-items :rankingSequence="rankData.rankingSequence"/></th>
                      <td><rank-items :nickname="rankData.nickname"/></td>
                      <td><rank-items :score="rankData.score" /></td>
                      <td><rank-items :tier="rankData.tier" /></td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>







              <!-- 기존 리스트 -->
              <!-- <ul class="title-list-group" style="width: 750px">
                <li class="title-list-group-items">
                  <b class="title-list-group-item">순위</b>
                  <b class="title-list-group-item">닉네임</b>
                  <b class="title-list-group-item">점수</b>
                  <b class="title-list-group-item">티어</b>
                </li>
              </ul>
              <ul class="list-group" style="width: 750px" v-for="rankData in rankDataList" :key="rankData.rankingSequence">
                <li class="list-group-item">
                  <rank-items :rankingSequence="rankData.rankingSequence"/>                  
                  <rank-items :nickname="rankData.nickname"/>                  
                  <rank-items :score="rankData.score" />            
                  <rank-items :tier="rankData.tier" />
                </li>
              </ul>
            </div> -->
            <!--  -->

              <!-- <table id="table-group" cellspacing="0" cellpadding="0">
                <thead>
                  <tr>
                    <th><span>순위</span></th>
                    <th><span>닉네임</span></th>
                    <th><span>점수</span></th>
                    <th><span>티어</span></th>
                  </tr>
                </thead>
                <tbody class="list-group" style="width: 750px" 
                    v-for="rankData in rankDataList" 
                    :key="rankData.rankingSequence">
                  <tr>
                    <td class="lalign"><rank-items :rankingSequence="rankData.rankingSequence" /></td>
                    <td><rank-items :nickname="rankData.nickname" /></td>
                    <td><rank-items :score="rankData.score" /></td>
                    <td><rank-items :tier="rankData.tier" /></td>
                  </tr>
                </tbody>
              </table> -->

              
              <!-- <div class="table-title-group">
                <ul class="list-group" style="width: 750px">
                  <li class="table-title-group-item">
                    <span>순위</span>            
                    <span>닉네임</span>                              
                    <span>점수</span>                              
                    <span>티어</span>                                          
                  </li>
                </ul>
              </div> -->

              <!-- <ul class="list-group" style="width: 750px" v-for="rankData in rankDataList" :key="rankData.rankingSequence">
                <li class="list-group-item">
                  <rank-items :rankingSequence="rankData.rankingSequence" />            
                  <rank-items :nickname="rankData.nickname" />            
                  <rank-items :score="rankData.score" />            
                  <rank-items :tier="rankData.tier" />                  
                </li>
              </ul> -->
            

            
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
                  <tbody>
                    <!-- row 1 -->
                    <tr v-for="rankData in rankDataList" :key="rankData.rankingSequence">
                      <th><rank-items :rankingSequence="rankData.rankingSequence"/></th>
                      <td><rank-items :nickname="rankData.nickname"/></td>
                      <td><rank-items :score="rankData.score" /></td>
                      <td><rank-items :tier="rankData.tier" /></td>
                    </tr>
                  </tbody>
                </table>
              </div>
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


            <div v-bind:class="{'hidden': openTab !== 3, 'block': openTab === 3}" @click="getRanking()">
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
                  <tbody>
                    <!-- row 1 -->
                    <tr v-for="rankData in rankDataList" :key="rankData.rankingSequence">
                      <th><rank-items :rankingSequence="rankData.rankingSequence"/></th>
                      <td><rank-items :nickname="rankData.nickname"/></td>
                      <td><rank-items :score="rankData.score" /></td>
                      <td><rank-items :tier="rankData.tier" /></td>
                    </tr>
                  </tbody>
                </table>
              </div>
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
                

          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>

import WordsSearchBar from '@/views/rank/components/WordsSearchBar.vue'
import BodySearchBar from '@/views/rank/components/BodySearchBar.vue'
import RankItems from '@/views/rank/components/RankItems.vue'
import axios from 'axios';
import {mapGetters} from 'vuex';

export default {
  name: 'RankTab',
  components: {
    WordsSearchBar,
    BodySearchBar,
    RankItems,  
  },  
  data() {
    return {
      openTab: 1,
      index: 0, 
      rankDataList : [], 
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
    getRanking () {
      console.log(this.openTab, typeof(this.openTab))
      axios ( {
        url: `/api/rankings/${this.openTab+1}`,
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

<style>
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
  font-size: 1.2em;
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



</style>