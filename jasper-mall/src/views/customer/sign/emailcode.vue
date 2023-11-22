<script setup lang="ts">
import {ref} from "vue";
import {useRoute, useRouter} from "vue-router";
import {useMessage} from "naive-ui";
import {useUserRegisterStore} from "@/stores/userRegister";
import {register} from "@/api/user";
const message = useMessage()
const userRegister = useUserRegisterStore()
const verCode = ref<String>('')
const router = useRouter()
function handleContinue(){
  register(userRegister.user,verCode.value).then(
      response => {
        if(response.code === 2002){
          return
        }
        message.info('现在你可以前往登录了')
        router.push({name:'signIn'})
      }
  )
}
</script>

<template>
<div>
  <n-card hoverable embedded >
    <h1 style="font-weight: normal;margin-bottom: 10px">注册账户</h1>
    <n-form-item label="请输入您邮箱收到的验证码" >
      <n-input v-model:value.number="verCode" placeholder="please input your email or phone number" />
    </n-form-item>
    <n-button color="#edd083" keyboard style="width: 100%;margin-bottom: 10px" @click="handleContinue">继续</n-button>
  </n-card>
</div>
</template>

<style scoped>

.n-card{
  max-width: 400px;
  margin: 4vw auto 18vw;
}
</style>
