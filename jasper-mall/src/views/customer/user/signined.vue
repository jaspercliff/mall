<script setup lang="ts">
import {Cart16Regular, History24Regular, Star24Regular} from "@vicons/fluent";
import {getSession} from "@/utils/storage";
import router from "@/router";
const username = getSession('username')
function handleCart(){
  router.push("cart")
}
</script>

<template>
  <div class="person" >
    <div style="display: grid;place-items: center;">
      <n-avatar
          round
          src="https://07akioni.oss-cn-beijing.aliyuncs.com/07akioni.jpeg"/>
    </div>
    <span style="display: grid;place-items: center;font-size: 20px">
        <n-gradient-text type="info">
          Hi {{username}} !
        </n-gradient-text>
    </span>
    <div>
      <div class="sho">
        <div class="divIcon">
          <n-icon size="40" :depth="2">
            <Star24Regular/>
          </n-icon>
          <span>宝贝收藏</span>
        </div>
        <div class="divIcon" @click="handleCart">
          <n-icon size="40" :depth="2">
            <Cart16Regular/>
          </n-icon>
          <span>购物车</span>
        </div>
        <div class="divIcon">
          <n-icon size="40" :depth="2">
            <History24Regular/>
          </n-icon>
          <span>购买历史</span>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>

.n-avatar{
  height: 80px;
  width: 80px;
  margin: 3vw 0px;
}
.sho{
  display: grid;
  margin: 2vw 0;
  grid-template-columns: 2fr 2fr 2fr;
  place-items: center;
}
.divIcon{
  display: grid;
  place-items: center;
}
</style>
