<script setup lang="ts">
import Total from "@/assets/svg/total.vue";
import {onMounted, ref} from 'vue'
import type {DrawerPlacement} from 'naive-ui'
import {getSession} from "@/utils/storage";
import {getHotCategory} from "@/api/category";
interface Category{
  name:string,
  id:string
}
const active = ref(false)
const placement = ref<DrawerPlacement>('right')
const activate = (place: DrawerPlacement) => {
  active.value = true
  placement.value = place
}
const username = getSession('username')
const isHave = (username === null ? false : true)
const hotCat = ref<Category[]>([])

onMounted(()=>{
  getHotCategory().then(
     res =>{
       hotCat.value = res.data
       console.log(hotCat.value)
     }
  )
})
</script>

<template>
  <div class="container">
    <div class="fixed">
      <n-button :bordered="false" ghost color="#fff"  @click="activate('left')">
        <template #icon>
          <total/>
        </template>
        全部
      </n-button>
      <n-button :bordered="false" color="#fff" ghost>
        会员中心
      </n-button>
      <n-button :bordered="false" color="#fff" ghost>
        优惠券
      </n-button>
      <n-button  ghost :bordered="false" color="#fff">
        新品发布
      </n-button>
      <n-button :bordered="false" v-for="item in hotCat" :key="item.id">
        {{item.name}}
      </n-button>
    </div>
  </div>
  <n-drawer v-model:show="active" :width="400" :placement="placement">
    <n-drawer-content header-style="background-color:#252f3d;color:#fff">
      <template #header>
        您好,
        <n-button v-if="!isHave" text :bordered="false" ghost style="color: #fff;font-size: large">请登录</n-button>
        <span v-if="isHave">{{username}}</span>
      </template>
      <div>
        <h2>热门导航</h2>
        <n-list clickable hoverable>
          <n-list-item>
              会员中心
          </n-list-item>
          <n-list-item>
              优惠卷
          </n-list-item>
          <n-list-item>
            新品发布
          </n-list-item>
        </n-list>
        <h2>全部分类</h2>
        <hr>
      </div>
    </n-drawer-content>
  </n-drawer>
</template>

<style scoped>
.container {
  background-color: #252f3d;
  color: #fff;
  height: 35px;
}

.fixed {
  background-color: #252f3d;
  color: #fff;
  display: inline;
  width: 200px;
}

.container * {
  color: #fff;
}

.header-style{
  background-color: red;
  color: palegoldenrod;
}
</style>