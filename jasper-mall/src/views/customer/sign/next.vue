<script setup lang="ts">
import { reactive} from "vue";
import {useRoute} from "vue-router";
import type {LocationQueryValue} from "vue-router";
import {login} from "@/api/user";
import router from "@/router";
import {useMessage} from 'naive-ui'
import {setToken} from "@/utils/auth";
import {setSession} from "@/utils/storage";

const route = useRoute()
const username:string | null | LocationQueryValue[] = route.query.username
const message = useMessage()
  interface User{
    email:string
    password:string
  }
  const user = reactive({
    password:'',
    email:''
  })
async function handleClick(){
    const email = route.query.email
    if (typeof email === "string")
    user.email = email
    if(user.password == ''){
      message.warning('密码不能为空')
      return
    }
  const resp = await login(user)
  if(resp.code===2001){
    setToken(resp.data)
    setSession('username',username)
    await router.push({name:'home'})
  }

}
</script>

<template>
  <div>
    <n-card hoverable embedded >
      <h1 style="font-weight: normal;margin-bottom: 10px">登录</h1>
      <div style="margin: 15px auto">
        <span>2154648489</span><a href="">更改</a >
      </div>
      <div class="pwd">
        密码
        <a href="" style="justify-self: end">忘记密码</a>
      </div>
      <n-form>
        <n-input
                type="password"
                 show-password-on="mousedown"
                 v-model:value="user.password"
                 placeholder="please input your password" />
        <n-button color="#edd083" keyboard style="width: 100%;margin:30px auto" @click="handleClick">登录</n-button>
      </n-form>
      <n-popover trigger="hover"  :duration="500">
        <template #trigger>
          <n-checkbox>记住登录状态</n-checkbox>
        </template>
        <span>选择“记住登录状态”可减少您需要在此设备上登录的次数。
          为了确保您的账户安全，请仅在您的个人设备上使用此选项。</span>
      </n-popover>
    </n-card>
  </div>

</template>

<style scoped>
.n-card{
  display: grid;
  max-width: 400px;
  grid-template-columns: 2fr;
  margin: 4vw auto 18vw;
}
.pwd{
  display: grid;
  grid-template-columns: 2fr 2fr;
  justify-items: stretch;
}

</style>
