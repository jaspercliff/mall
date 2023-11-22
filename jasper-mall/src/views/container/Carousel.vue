<template>
<div>
  <n-carousel :autoplay="true">
    <img class="carousel-img" src="@/assets/img/slide1.jpg" >
    <img class="carousel-img" src="@/assets/img/slide2.jpg" >
    <img class="carousel-img" src="@/assets/img/slide3.jpg" >
  </n-carousel>
</div>
</template>
<script setup>

</script>
<style>
.carousel-img {
  width: 100%;
  height: 60vh;
  object-fit: cover;
}
</style>