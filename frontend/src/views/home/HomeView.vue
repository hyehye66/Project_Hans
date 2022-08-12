<template>
  <div class="home">
    <HomeCarousel />
  </div>
</template>

<script>
// @ is an alias to /src
import HomeCarousel from '@/views/home/components/HomeCarousel.vue'


export default {
  name: 'HomeView',
  components: {
    HomeCarousel
  },
  methods : {
    
  },
  created(){

  }
}
</script>
