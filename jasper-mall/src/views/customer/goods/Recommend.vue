<script setup lang="ts">
import {onMounted,ref} from "vue";
import {getRecommend} from "@/api/goods";
import {useRouter} from "vue-router";
const recommendList = ref()
const router= useRouter()
onMounted(async ()=>{
  const {data} = await getRecommend()
  recommendList.value = data
})
function handleClickGoods(id:string){
    router.push({path:'/goodsDetail',query:{id:id}})
}
</script>
<template>
  <div>
    <h1>为您推荐</h1>
  <div class="container">
    <div class="row">
      <n-card hoverable v-for="item in recommendList" :key="item.id" @click="handleClickGoods(item.id)">
        <n-image :src="item.picture" width="150" height="180" preview-disabled/>
        <br>
        {{item.name}}
        <br>
        {{item.price}}元
      </n-card>
    </div>
  </div>
  </div>
</template>

<style scoped>
h1{
  color: #333;
  font-size: 28px;
  font-family: "Helvetica Neue", Arial, sans-serif;
  font-weight: bold;
  background-color: #f2f2f2;
  text-align: center;
}
.n-card{
    width: 300px;
    height: 300px;
    margin: 0 20px;
}
.container{
  margin: 0 4vw;
}
.row {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}
</style>
