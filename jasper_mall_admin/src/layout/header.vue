<script setup lang="ts">
import {useStateStore} from "@/stores/state";
import {onMounted} from "vue";
import {renderIcon} from "@/utils/common";
import {LogOutOutline as LogoutIcon, Pencil as EditIcon, PersonCircleOutline as UserIcon} from '@vicons/ionicons5'

const state = useStateStore()
onMounted(()=>{
})
const options = [
  {
    label: '用户资料',
    key: 'profile',
    icon: renderIcon(UserIcon)
  },
  {
    label: '编辑用户资料',
    key: 'editProfile',
    icon: renderIcon(EditIcon)
  },
  {
    label: '退出登录',
    key: 'logout',
    icon: renderIcon(LogoutIcon)
  }
]
function handleSelect (key: string | number) {
  console.log(String(key))
}
</script>

<template>
  <n-layout-header bordered class="header">
    <n-breadcrumb separator="/" >
        <n-breadcrumb-item>
          <router-link to='/frontPage/dashboard'>home</router-link>
        </n-breadcrumb-item>
      <n-breadcrumb-item>{{state.activeKey || 'dashboard'}}</n-breadcrumb-item>
    </n-breadcrumb>

    <n-dropdown :options="options" @select="handleSelect">
      <n-avatar
          round
          size="large"
          src="https://pic2.zhimg.com/80/v2-d07aae5cf13173cc50d5d386c87aa659_1440w.webp"
      />
    </n-dropdown>
  </n-layout-header>
</template>

<style scoped>
.header{
  height: 45px;
  font-size: 30px;
  display: grid;
  grid-template-columns: 1fr 1fr;
  background-color:  #f0f2f5; /* 头部背景颜色 */
  color: #fff; /* 头部文本颜色 */
}
.n-breadcrumb{
  margin-left: 10px;
  justify-self: start;
}
.n-avatar{
  justify-self: end;
  margin-right: 10px;
}
</style>
