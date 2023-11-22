<script setup lang="ts">
import {reactive,ref,h} from "vue";
import {NIcon} from "naive-ui";
import Cart from "@/assets/svg/cart.vue";
import {useUserStore} from "@/stores/user";
import {getSession} from "@/utils/storage";
import router from "@/router";
const search = reactive({
  select:'veli good',
  search:''
})
const rules = reactive({
  select: {
    required: true,
        trigger: ['blur', 'change'],
        message: '请选择 selectValue'
  },
})
const menuOptions = ['groode', 'veli good', 'emazing', 'lidiculous'].map(
    value => ({
      label:value,
      value:value
    })
)
const username = getSession('username')
const isHave = (username === null ? false : true)
function handleClick(){
  router.push("/signin")
}
function imageClick(){
  router.push("/")
}
function handleCart(){
  router.push("cart")
}
function handleOrderDetail(){
  router.push("orderDetail")
}
</script>

<template>
<div class="header">
  <div class="">
  </div>
  <div>
    <n-form inline>
      <div  @click="imageClick">
        <img src="@/assets/logo.png" alt="logo" height="50" width="190">
      </div>
        <n-input  placeholder="search" v-model:value="search.search"/>
        <n-button  style="margin-left: -20px">搜索</n-button>
        <n-button size="medium" :bordered="false" ghost style="display:grid; grid-template-columns: 1fr 1fr" @click="handleClick" :disabled="isHave">
          您好,
          <span v-if="!isHave">
            登录
          </span>
          <span v-if="isHave">{{username}}</span>
        </n-button>
      <n-button @click="handleOrderDetail" size="medium" :bordered="false" ghost>
        我的订单
      </n-button>
      <n-button size="medium" :bordered="false" ghost icon-placement="left" @click="handleCart">
        <template #icon>
          <cart/>
        </template>
        购物车
      </n-button>
    </n-form>
  </div>
</div>
</template>

<style scoped>
.n-form{
  display: grid;
  grid-template-columns: 1.5fr 8fr 0.6fr 1fr 1fr 1fr;
  background: #141920;
  column-gap: 20px;
  height: 50px;
  align-items: center;
}
.n-form *{
  color: #fff;
}
.n-button{
 bordered:false
}
.n-input{
  display: inline;
}
</style>
