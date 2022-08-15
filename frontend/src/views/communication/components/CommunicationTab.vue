<template>
  <div class="container flex flex-wrap">
    <div class="small-container w-full">
      <ul class="flex mb-0 list-none flex-wrap pt-3 pb-4 flex-row">
        <li class="-mb-px mr-2 last:mr-0 flex-auto text-center" style="cursor:pointer">
          <a class="category-tab text-xs font-bold uppercase px-5 py-3 shadow-lg rounded block leading-normal" v-on:click="toggleTabs(1)" v-bind:class="{'text-blue-500 bg-white': openTab !== 1, 'text-white bg-blue-500': openTab === 1}">
            <i class="fas fa-space-shuttle text-base mr-1"></i> Shopping
          </a>
        </li>
        <li class="-mb-px mr-2 last:mr-0 flex-auto text-center" style="cursor:pointer">
          <a class="category-tab text-xs font-bold uppercase px-5 py-3 shadow-lg rounded block leading-normal" v-on:click="toggleTabs(2)" v-bind:class="{'text-blue-500 bg-white': openTab !== 2, 'text-white bg-blue-500': openTab === 2}">
            <i class="fas fa-cog text-base mr-1"></i> Hospital
          </a>
        </li>
        <li class="-mb-px mr-2 last:mr-0 flex-auto text-center" style="cursor:pointer">
          <a class="category-tab text-xs font-bold uppercase px-5 py-3 shadow-lg rounded block leading-normal" v-on:click="toggleTabs(3)" v-bind:class="{'text-blue-500 bg-white': openTab !== 3, 'text-white bg-blue-500': openTab === 3}">
            <i class="fas fa-briefcase text-base mr-1"></i> Restaurant
          </a>
        </li>
        <li class="-mb-px mr-2 last:mr-0 flex-auto text-center" style="cursor:pointer">
          <a class="category-tab text-xs font-bold uppercase px-5 py-3 shadow-lg rounded block leading-normal" v-on:click="toggleTabs(4)" v-bind:class="{'text-blue-500 bg-white': openTab !== 4, 'text-white bg-blue-500': openTab === 4}">
            <i class="fas fa-briefcase text-base mr-1"></i> Travel
          </a>
        </li>
        <li class="-mb-px mr-2 last:mr-0 flex-auto text-center" style="cursor:pointer">
          <a class="category-tab text-xs font-bold uppercase px-5 py-3 shadow-lg rounded block leading-normal" v-on:click="toggleTabs(5)" v-bind:class="{'text-blue-500 bg-white': openTab !== 5, 'text-white bg-blue-500': openTab === 5}">
            <i class="fas fa-briefcase text-base mr-1"></i> Call
          </a>
        </li>
      </ul>
      <div class="content-box1 col-md-12 relative flex flex-col min-w-0 break-words bg-white w-full mb-6 shadow-lg rounded">
        <!-- 한국어문장도 같이 들어갈 경우 col-md 수정해야 -->
        <div class="content-box2 px-4 py-5 flex-auto col-md-11">
          <div class="tab-content tab-space col-md-12">
            <div v-bind:class="{'hidden': openTab !== 1, 'block': openTab === 1}">
            <ul class="list-group" v-for="situation in situationSentence" :key="situation.situationSequence">
              <li class="list-group-item" v-if="situation.category==='shopping'">
                <one-dialog :sentence="situation.sentence"/>
              </li>
            </ul>  
            </div>
            <div v-bind:class="{'hidden': openTab !== 2, 'block': openTab === 2}">
              <ul class="list-group" v-for="situation in situationSentence" :key="situation.situationSequence">
                <li class="list-group-item" v-if="situation.category==='hospital'">
                  <one-dialog :sentence="situation.sentence"/>
                </li>
              </ul>  
            </div>
            <div v-bind:class="{'hidden': openTab !== 3, 'block': openTab === 3}">
            <ul class="list-group" v-for="situation in situationSentence" :key="situation.situationSequence">
              <li class="list-group-item" v-if="situation.category==='restaurant'">
                <one-dialog :sentence="situation.sentence"/>
              </li>
            </ul>  
            </div>
            <div v-bind:class="{'hidden': openTab !== 4, 'block': openTab === 4}">
            <ul class="list-group" v-for="situation in situationSentence" :key="situation.situationSequence">
              <li class="list-group-item" v-if="situation.category==='travel'">
                <one-dialog :sentence="situation.sentence"/>
              </li>
            </ul>  
            </div>
            <div v-bind:class="{'hidden': openTab !== 5, 'block': openTab === 5}">
            <ul class="list-group" v-for="situation in situationSentence" :key="situation.situationSequence">
              <li class="list-group-item" v-if="situation.category==='call'">
                <one-dialog :sentence="situation.sentence"/>
              </li>
            </ul>  
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import OneDialog from '@/views/communication/components/OneDialog.vue'

export default {
  name: 'CommunicationTab',
  components: {
    OneDialog,
},
props : {
  situationSentence : Array
},
  data() {
    return {
      openTab: 1
    }
  },
  methods: {
    toggleTabs: function(tabNumber){
      this.openTab = tabNumber
    }
  }
}
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 70%;
}

.small-container {
  margin-top: 5%;
}

.category-tab {
  background: #f1c4d9;
  color: #dfdfdf ;
  font-size: 1.2rem;

}

.category-tab:hover {
  color: #f1c4d9;
}

.content-box1 {
  display: flex;
  justify-content: center;
  align-items: center;
}



</style>
