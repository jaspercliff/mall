<script setup lang="ts">

import Header from "@/views/container/header.vue";
import Nav from "@/views/container/Nav.vue";
import Category from "@/views/customer/goods/Category.vue";
import Recommend from "@/views/customer/goods/Recommend.vue";
</script>

<template>
  <div class="container">
  <Header/>
  <Nav/>
  <div class="home">
    <n-card>
      <category/>
<!--      <seckill/>-->
      <Recommend/>
    </n-card>
  </div>
  </div>
</template>

<style scoped>
.home{
  margin: 0 6vw;
}
.container{
  background-color: #eae8eb;

}
</style>
