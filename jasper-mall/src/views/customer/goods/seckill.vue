<script setup lang="ts">

</script>

<template>
  <div style="background-color: #eae8eb">
  <h1>秒杀专区</h1>
    <n-card :bordered="false" >
      <div class="seckill">
        <img src="@/assets/img/seckill.png" width="200" height="270">
        <div class="carousel">
          <router-link to="" class="goods">
            <img src="@/assets/img/jiu.png" width="200" height="200">
            <span>price</span>
            <span>name</span>
          </router-link>
        </div>
        <img src="@/assets/img/supersale.png" width="200" height="270">
      </div>
    </n-card>
</div>
</template>

<style scoped>

.seckill{
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  margin: 0 6vw;
}
.goods{
  display: grid;
  grid-template-rows: 10fr 0.5fr 0.5fr;
}
h1{
  color: #333;
  font-size: 28px;
  font-family: "Helvetica Neue", Arial, sans-serif;
  font-weight: bold;
  background-color: #f2f2f2;
  text-align: center;
}
</style>
