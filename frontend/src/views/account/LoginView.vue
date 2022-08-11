<template>
<div class="hero min-h-screen bg-base-200">
  <div class="hero-content flex-col lg:flex-row-reverse">
    <!-- <div class="main-img">
      <img src="@/assets/login4.png" alt="login3">
    </div> -->

    <div class="card flex-shrink-0 w-full max-w-sm shadow-2xl bg-base-100 col-md-4">
      <div class="card-body">
        <div class="text-center lg:text-left">
          <h1 class="upper-text text-5xl font-bold">Login now!</h1>
          <p class="lower-text py-6">재미난 게임을 통해 한국어 실력을 향상시켜보세요</p>
        </div>
        <div class="form-control">      
          <label class="label">
            소셜 로그인하기            
          </label>
        </div>
        <!-- <div class="form-control mt-6">
          <button class="btn btn-primary">Login</button>
        </div> -->
      </div>
    </div>

    <!-- <div class="text-center lg:text-left col-md-4">
      <h1 class="upper-text text-5xl font-bold">Login now!</h1>
      <p class="lower-text py-6">재미난 게임을 통해 한국어 실력을 향상시켜보세요. 상황별로 한국어 회화도 덤으로!</p>
    </div> -->
  </div>
</div>
  
</template>

<script>
export default {
  name: 'LoginView',

}
</script>

<style scoped>
.hero {
  background-image: url("@/assets/login5.png");
  /* background: #f1c4d9; */
  width: 100vw;
  height: 90vh;
  position: absolute;
  /* opacity: 0.9; */
  object-fit: cover;
}

.hero-content {
  margin: 5%;
  /* display: flex; */
  /* flex-flow: row wrap; */
  /* justify-content: space-evenly; */
  /* border: 0rem; */
  /* border-radius: 20px 20px 20px 20px; */
  /* background: #ffff; */

}

.bg-img {
  /* margin: 10%; */
  /* float: left; */
}

.card {
  margin-right: 9%;
  margin-top: 3%;
  width: 29%;
  float: right;
}

.card-body {
  padding: 5%;
}

.form-control {
  padding: 3%;
}

.text-center {
  margin: 10%;
}

.upper-text {
  margin: 3%;
  font-size: 3em;
  color: #f1c4d9;

}

.lower-text {
  font-size: 1.5em;
  color: #2657a7;

}

label {
  justify-content: center;
}

</style>