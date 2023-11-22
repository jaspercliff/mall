<script setup lang="ts">
import {reactive, ref} from "vue";
import {useRouter} from "vue-router";
import type {FormInst} from "naive-ui"
import {useMessage} from "naive-ui"
import {login} from "@/api/admin";
import Wx from "@/components/icons/wx.vue";

const router = useRouter()
const message = useMessage()
interface User{
  username:string,
  password:string
}
const user = reactive<User>({
  username:'',
  password:''
})

const rules = {
  username:{
    required:true,
    message:'请输入用户名',
    trigger: ['blur']
  },
  password:{
    required:true,
    message:'请输入密码',
    trigger:'blur'
  }
}

const formRef = ref<FormInst | null>(null)
async function handleLogin(){
  formRef.value?.validate((errors) => {
    if (!errors) {
      message.success('Valid')
       login(user).then(
           response =>{

           }
       )
      router.push({name:'dashboard'})
    } else {
      message.error('form Invalid')
    }
  })
}
</script>

<template>
  <div class="container">
    <div class="vector">
    </div>
  <div>
    <n-card >
      <n-form
          ref="formRef"
          :model="user"
          :rules="rules"
      >
        <h3>
          LoginForm
        </h3>
        <n-form-item label="用户名" path="username">
          <n-input v-model:value="user.username" placeholder="输入用户名"/>
        </n-form-item>
        <n-form-item label="password" path="password">
          <n-input type="password" v-model:value="user.password" placeholder="please input password"/>
        </n-form-item>
        <n-button color="#edd083" keyboard style="width: 100%;margin-bottom: 10px" @click="handleLogin">登录</n-button>
        <div class="line-with-text">
          <hr>
          <span class="text">更多登录方式</span>
        </div>
        <n-button  style="margin-top: 20px;width: 100%;background-color: #72d446"><Wx/>&nbsp 微信登录</n-button>
      </n-form>
    </n-card>
  </div>

  </div>
</template>

<style scoped>
.n-card{
  max-width: 40vw;
  margin-top: 20vh;
}
.container{
  display: grid;
  grid-template-columns: 1.25fr 1fr;
}
.vector{
  height: 100vh;
  background-image: url('@/assets/img/login.jpg');
  background-size: cover;
  background-repeat: no-repeat;
}
.line-with-text {
  text-align: center;
  position: relative;
  margin-top: 20px;
  margin-bottom: 15px;
}

.text {
  background-color: #fff;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 5px 10px;
}
</style>
