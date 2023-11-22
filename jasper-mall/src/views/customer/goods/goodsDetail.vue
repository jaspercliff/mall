<script setup lang="ts">
import Header from "@/views/container/header.vue";
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {getGoodDetail} from "@/api/goods";
import {addCart} from "@/api/cart";
import {getSession} from "@/utils/storage";
import {useMessage} from "naive-ui"
const route = useRoute()
const router = useRouter()
const message = useMessage()
let goodDetail = ref({})
onMounted(async ()=>{
  let id = route.query.id
  const {data} = await getGoodDetail(id)
  goodDetail.value = data
  console.log(goodDetail)
})
const username = getSession("username")
async function handleClick(){
    const response = await addCart({
      productId:goodDetail.value.id,
      quantity: 1
    },username)
    message.success("添加购物车成功")
    await router.push("/cart")
}
</script>

<template>
  <Header/>
<div>
  <div class="container">
    <n-card style="display:grid;height: 40vw;place-items:center " :bordered="false">
      <n-image :src="goodDetail.picture" width="400"/>
    </n-card>
    <n-card :bordered="false" >
      <h1>{{goodDetail.name}}</h1>
      <div style="font-size: 25px">月销量:<span>{{goodDetail.sale}}</span></div> <br>
      <div style="font-size: 25px">成长值:<span>{{goodDetail.giftGrowth}}</span></div> <br>
      <div style="font-size: 25px">积分:<span>{{goodDetail.giftPoint}}</span></div> <br>
      <div style="font-size: 25px">
      <span style="font-size: 20px;color: #ec5e2a"> ￥:{{goodDetail.price}}</span>
      <span class="price" v-if="goodDetail.promotionPrice!=null">折后:{{goodDetail.promotionPrice}}</span>
      </div>
      <br>
      <div style="font-size: 25px">
        库存：{{goodDetail.stock}}
      </div>
      <div>
          <n-button @click="handleClick" color="#eb5428" style="margin-top: 20px">加入购物车</n-button>
      </div>
    </n-card>
  </div>
  </div>
</template>

<style scoped>
.container{
  display: grid;
  grid-template-columns: 1fr 1fr;
}
.n-card{
  margin: 1vw 3vw;
}
.price{
font-size: 20px;
  color: #ec5e2a;
}
</style>
