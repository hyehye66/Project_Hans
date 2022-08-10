<template>
  <div class="container flex flex-wrap">
    <div class="w-full">
      <ul class="flex mb-0 list-none flex-wrap pt-3 pb-4 flex-row">
        <li class="-mb-px mr-2 last:mr-0 flex-auto text-center" style="cursor:pointer">
          <a class="text-xs font-bold uppercase px-5 py-3 shadow-lg rounded block leading-normal" v-on:click="toggleTabs(1)" v-bind:class="{'text-blue-500 bg-white': openTab !== 1, 'text-white bg-blue-500': openTab === 1}">
            <i class="fas fa-space-shuttle text-base mr-1"></i> Shopping
          </a>
        </li>
        <li class="-mb-px mr-2 last:mr-0 flex-auto text-center" style="cursor:pointer">
          <a class="text-xs font-bold uppercase px-5 py-3 shadow-lg rounded block leading-normal" v-on:click="toggleTabs(2)" v-bind:class="{'text-blue-500 bg-white': openTab !== 2, 'text-white bg-blue-500': openTab === 2}">
            <i class="fas fa-cog text-base mr-1"></i> Hospital
          </a>
        </li>
        <li class="-mb-px mr-2 last:mr-0 flex-auto text-center" style="cursor:pointer">
          <a class="text-xs font-bold uppercase px-5 py-3 shadow-lg rounded block leading-normal" v-on:click="toggleTabs(3)" v-bind:class="{'text-blue-500 bg-white': openTab !== 3, 'text-white bg-blue-500': openTab === 3}">
            <i class="fas fa-briefcase text-base mr-1"></i> Restaurant
          </a>
        </li>
        <li class="-mb-px mr-2 last:mr-0 flex-auto text-center" style="cursor:pointer">
          <a class="text-xs font-bold uppercase px-5 py-3 shadow-lg rounded block leading-normal" v-on:click="toggleTabs(4)" v-bind:class="{'text-blue-500 bg-white': openTab !== 4, 'text-white bg-blue-500': openTab === 4}">
            <i class="fas fa-briefcase text-base mr-1"></i> Travel
          </a>
        </li>
        <li class="-mb-px mr-2 last:mr-0 flex-auto text-center" style="cursor:pointer">
          <a class="text-xs font-bold uppercase px-5 py-3 shadow-lg rounded block leading-normal" v-on:click="toggleTabs(5)" v-bind:class="{'text-blue-500 bg-white': openTab !== 5, 'text-white bg-blue-500': openTab === 5}">
            <i class="fas fa-briefcase text-base mr-1"></i> Business
          </a>
        </li>
      </ul>
      <div class="relative flex flex-col min-w-0 break-words bg-white w-full mb-6 shadow-lg rounded">
        <div class="px-4 py-5 flex-auto">
          <div class="tab-content tab-space">
            <div v-bind:class="{'hidden': openTab !== 1, 'block': openTab === 1}">
            <ul class="list-group" style="width: 750px" v-for="situation in situationSentence" :key="situation.situationSequence">
              <li class="list-group-item" v-if="situation.category==='제목1'">
                <one-dialog :sentence="situation.sentence"/>
              </li>
            </ul>  
            </div>
            <div v-bind:class="{'hidden': openTab !== 2, 'block': openTab === 2}">
              <ul class="list-group" style="width: 750px" v-for="situation in situationSentence" :key="situation.situationSequence">
                <li class="list-group-item" v-if="situation.category==='제목2'">
                  <one-dialog :sentence="situation.sentence"/>
                </li>
              </ul>  
            </div>
            <div v-bind:class="{'hidden': openTab !== 3, 'block': openTab === 3}">
            <ul class="list-group" style="width: 750px" v-for="situation in situationSentence" :key="situation.situationSequence">
              <li class="list-group-item" v-if="situation.category==='제목3'">
                <one-dialog :sentence="situation.sentence"/>
              </li>
            </ul>  
            </div>
            <div v-bind:class="{'hidden': openTab !== 4, 'block': openTab === 4}">
            <ul class="list-group" style="width: 750px" v-for="situation in situationSentence" :key="situation.situationSequence">
              <li class="list-group-item" v-if="situation.category==='제목4'">
                <one-dialog :sentence="situation.sentence"/>
              </li>
            </ul>  
            </div>
            <div v-bind:class="{'hidden': openTab !== 5, 'block': openTab === 5}">
            <ul class="list-group" style="width: 750px" v-for="situation in situationSentence" :key="situation.situationSequence">
              <li class="list-group-item" v-if="situation.category==='제목5'">
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

<style>
.container {
        display: flex;
        justify-content: center;
        align-items: center;
      }
</style>