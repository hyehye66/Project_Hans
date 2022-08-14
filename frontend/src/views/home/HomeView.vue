<template>
  <nav-bar />
  <div class="home">
    <HomeCarousel />
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar.vue";
import HomeCarousel from '@/views/home/components/HomeCarousel.vue'


export default {
  name: 'HomeView',
  components: {
    HomeCarousel,
    NavBar,
  },
  methods : {
    
  },
  created(){

  }
}
</script>
