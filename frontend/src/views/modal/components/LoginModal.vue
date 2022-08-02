<template>
<div v-if="open" class="modal"  tabindex="-1" >
  <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">로그인</h5>
        <button @click="$emit('update:open', !open)" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        구글 로그인
        <a href="https://google.com">
          <img src="@/assets/google_logo.png" width="50" height="auto" alt="...">
        </a>
      </div>
      <div class="modal-footer">
        <button @click="$emit('update:open', !open)" type="button" class="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded" data-bs-dismiss="modal">Close</button>        
      </div>
    </div>
</div>
</template>

<script>
export default {
  props :{
    open : {
      type : Boolean,
      require : true,
      default : false
      }
  },
  methods : {
    isClose() {
      console.log(this.open)
      this.$emit('update:open', false)
    }
  }
}
</script>


<style scoped>
.modal { 
  position: absolute;
  display : flex; 
  top: 20%;
  left: 50%;
  width: 300px;
  margin-left: -150px; 
  height : auto;
  z-index: 90;
  visibility: visible;
  opacity: 100;
}
</style>
