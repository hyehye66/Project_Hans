<template>
<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>
  <div class="carousel-inner" id="inner" style="width:auto height:auto">
    <div class="carousel-item active">
      <img src="@/assets/home3.png" class="d-block w-100"  alt="...">
      <!-- <p align="right">
        <img src="@/assets/home.png" alt="..."> -->
        <!-- class="d-block" -->
      <!-- </p> -->
      <!-- <div style="textbox1 border: 1px border: none width: 200px height: 200px"> -->
        <!-- float: left -->
        <!-- 한국어를 재밌게  배우고 싶다구요?
      </div> -->
      <div class="carousel-caption text-left col col-md-8 bg-newGrey1">
        <!-- d-none d-md-block -->
        <br>
        <br>
        <h5 class="c1-t1 font-black text-newBlue1">한국어를 재밌게 
          <p>배우고 싶다구요?</p>
        </h5>
        <br>
        <p class="c1-t2 font-black text-newBlue1">지금 바로 가입하세요!</p>
        <p class="c1-t3 font-black text-newPink1">Welcome to Korean Language Education Service!</p>
        <br>          
        <button type="button" 
        class="bg-newBlue1 hover:bg-newGrey2 text-white font-bold py-2 px-5 rounded-full" 
        id="start">
          Start
        </button>
      </div>      
    </div>
    <div class="carousel-item">
      <img src="@/assets/8.png" class="d-block w-100" alt="...">
      <!-- style="max-width: 100%; height: auto;" -->
      <div class="carousel-caption-two text-white d-none d-md-block">
        <h5 class="c2-t1 font-black">How do you say this in Korean?</h5>
        <br>
        <p class="c2-t2 font-black">적재적소 표현이 궁금하다면</p>
        <p class="c2-t3 font-black">상황별 한국어 회화
          <button type="button" 
          class="bg-newBlue1 hover:bg-newGrey2 text-white font-bold py-2 px-4 rounded-full" 
          id="start">
            학습하러 가기
          </button>
        </p>

      </div>
    </div>
    <div class="carousel-item">
      <img src="@/assets/6.png" class="d-block w-100" alt="...">
      <!-- <div class="carousel-caption d-none d-md-block">
        <h5>Third slide label</h5>
        <p>Some representative placeholder content for the third slide.</p>
      </div> -->
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>

  <!-- <carousel :items-to-show="1.5">
    <slide v-for="slide in 10" :key="slide">
      {{ slide }}
    </slide>

    <template #addons>
      <navigation />
      <pagination />
    </template>
  </carousel> -->
</template>


<script>
// If you are using PurgeCSS, make sure to whitelist the carousel CSS classes
// import 'vue3-carousel/dist/carousel.css'
// import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel'

export default {
  name: 'HomeCarousel',
  data() {
    return {
      slide: 0,
      sliding: null
    }
  },
  methods: {
    onSlideStart() {
      this.sliding = true
    },
    onSlideEnd() {
      this.sliding = false
    },
  }
//   components: {
//     Carousel,
//     Slide,
//     Pagination,
//     Navigation,
//   },
}
</script>

<style scoped>
img {
    height: auto;
    width: 100%;
    object-fit: cover;
    /* contain */
  }

.carousel-item {
  position:relative;
  /* transition: .5s ease; 필수는 아님 */
  width: 100vw;
  height: 100vh;
 }

.carousel-caption {
  /* padding: 10px 20px; */
  width: 35%;
  height: 65%;
  /* background-color: #FFEEBC; */
  text-align: center;
  position: absolute;
  top: 15%;
  left: 7%;
  border-radius: 15px 50px;
}

.carousel-caption-two {
  /* padding: 10px 20px; */
  width: 35%;
  height: 65%;
  /* background-color: #FFEEBC; */
  text-align: center;
  position: absolute;
  top: 25%;
  right: 7%;
  border-radius: 15% 50%;

}

.c1-t1 {
  font-size: 5rem;
  /* font-size: clamp(2rem, 10vw, 5rem); */
}
.c1-t2 {
  font-size: 2rem;
}
.c1-t3 {
  font-size: 2rem;
}
#start {
  font-size: 2rem;
}

.c2-t1 {
  font-size: 3rem;
  /* font-size: clamp(2rem, 10vw, 5rem); */
}
.c2-t2 {
  font-size: 2rem;
}
.c2-t3 {
  font-size: 2rem;
}

/* 기본으로 작성되는 CSS는 576px보다 작은 화면에서 작동 됩니다.  */
/* 가로모드 모바일 디바이스 (가로 해상도가 576px 보다 큰 화면에 적용)  */
@media (min-width: 576px) {
  .c1-t1 { 
  font-size: 3rem; 
  };
  .c1-t2 { 
  font-size: 0.5rem; 
  };
  .c1-t3 { 
  font-size: 0.5rem; 
  };

  .c2-t1 {
  font-size: 3rem;
  /* font-size: clamp(2rem, 10vw, 5rem); */
  }
  .c2-t2 {
    font-size: 2rem;
  }
  .c2-t3 {
    font-size: 2rem;
  }
} 
 
/* 태블릿 디바이스 (가로 해상도가 768px 보다 큰 화면에 적용)  */
@media (min-width: 768px) {

} 
 
/* 테스크탑 (가로 해상도가 992px 보다 큰 화면에 적용)  */
@media (min-width: 992px) {

} 
 
/* 큰화면 데스크탑 (가로 해상도가 1200px 보다 큰 화면에 적용)  */
@media (min-width: 1200px) {
  .c1-t1 { 
  font-size: 3rem; 
  };
  .c1-t2 { 
  font-size: 0.5rem; 
  };
  .c1-t3 { 
  font-size: 0.5rem; 
  };

  .c2-t1 {
  font-size: 3rem;
  /* font-size: clamp(2rem, 10vw, 5rem); */
  }
  .c2-t2 {
    font-size: 2rem;
  }
  .c2-t3 {
    font-size: 2rem;
  }

}





/* 세로모드 모바일 디바이스 (가로 해상도가 576px 보다 작은 화면에 적용) */
@media (max-width: 575px) {

} 
 
/* 가로모드 모바일 디바이스 (가로 해상도가 576px보다 크고 768px 보다 작은 화면에 적용)  */
@media (min-width: 576px) and (max-width: 767px) {

}
 
/*태블릿 디바이스 (가로 해상도가 768px보다 크고 991px 보다 작은 화면에 적용)  */
@media (min-width: 768px) and (max-width: 991px) {

} 
 
/* 데스크탑 (가로 해상도가 992px보다 크고 1199px 보다 작은 화면에 적용)  */
@media (min-width: 992px) and (max-width: 1199px) {

} 
 
/* 큰화면 데스크탑 (가로 해상도가 1200px 보다 큰 화면에 적용)  */
@media (min-width: 1200px) {
  
}



/* .text {
  position:absolute;
  visibility:hidden;
 }

.box:hover {
  opacity:0.7;
 }

.box:hover .text {
   visibility:visible;
 }     */
</style>