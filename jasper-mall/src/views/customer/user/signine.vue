<script setup lang="ts">
import {Star24Regular,Cart16Regular,History24Regular} from "@vicons/fluent";
import {useRouter} from "vue-router";
const router = useRouter()
function handleSignIn(){
  router.push("/signin")
}
function handleSignUp(){
  router.push("/signUp")
}

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
    <div>
      <n-button-group style="display: grid;grid-template-columns: 1fr 1fr; gap: 30px ;margin:0 50px">
        <n-button  @click="handleSignIn" color="#ec5f2a">登录</n-button>
        <n-button @click="handleSignUp" color="#ef9235">注册</n-button>
      </n-button-group>
      <div class="sho">
        <div class="divIcon">
          <n-icon size="40" :depth="2">
            <Star24Regular/>
          </n-icon>
          <span>宝贝收藏</span>
        </div>
        <div class="divIcon">
          <n-icon size="40" :depth="2" @click="handleCart">
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
  margin: 3vw 20px;
}
.sho{
  display: grid;
  margin: 5vw 0;
  grid-template-columns: 2fr 2fr 2fr;
  place-items: center;
}
.divIcon{
  display: grid;
  place-items: center;
}
</style>
