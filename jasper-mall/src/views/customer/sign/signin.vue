<script setup lang="ts">
import {reactive, ref} from "vue";
import Wx from "@/assets/svg/wx.vue";
import {useRouter} from "vue-router";
import {useMessage} from "naive-ui";
import {getUserByEmail, } from "@/api/user";
const router = useRouter();
const message = useMessage()
const read = ref<Boolean>(false)
interface User{
  email: string,
  username:string
}
const user = reactive<User>({
  email:'',
  username:''
})
const isOpen = ref(false)
const toggleDropDown = () =>{
  isOpen.value = !isOpen.value
}
const success = ref<boolean>(false)
async function handleBlur(){
  const resp = await getUserByEmail(user.email)
  // @ts-ignore
  success.value = resp.success
  user.username = resp.data
}
function handleContinue() {
  if( user.email==='') {
    message.warning('邮箱不能为空')
    return
  }
  if(read.value === false){
    message.warning("请先阅读用户协议")
    return
  }
  if(success.value === false){
    message.warning("该用户不存在，请重新输入，或者前往注册")
    return
  }
    router.push({name: 'next', query: {email: user.email,username:user.username}})
}
function handleSignup(){
  router.push({name:'signUp'})
}
</script>

<template>
<div>
  <n-card hoverable embedded >
    <h1 style="font-weight: normal;margin-bottom: 10px">登录</h1>
    <n-form-item label="手机号码或者电子邮箱" >
      <n-input @blur="handleBlur" v-model:value="user.email" placeholder="please input your email" />
    </n-form-item>
    <n-button color="#edd083" keyboard style="width: 100%;margin-bottom: 10px" @click="handleContinue">继续</n-button>
    <span @click="toggleDropDown">
      <span :class="isOpen ? 'arrow down' : 'arrow right'"></span>
      need help
    </span>
    <br>
      <span v-show="isOpen" >
          <a href="" style="margin-left: 17px">forget password</a> <br>
          <a href="" style="margin-left: 17px">other problem</a>
      </span>
    <div class="line-with-text">
      <hr>
      <span class="text">更多登录方式</span>
    </div>
    <n-button round style="margin-top: 20px;width: 100%;background-color: #72d446"><Wx/>&nbsp 微信登录</n-button>
    <n-button round style="margin-top: 20px;width: 100%;background-color: #fff"><Wx/>&nbsp google登录</n-button>
    <n-form-item>
      <n-checkbox v-model:checked="read">
        我已阅读并同意 <a href="">使用条件</a>及<a href="">隐私声明</a>并同意我的部分个人信息被查看。
      </n-checkbox>
    </n-form-item>
  </n-card>
  <div class="line-with-text" style="margin-top: 4vw">
    <hr>
    <span class="text">乐购新用户？</span>
  </div>
  <div class="regButton" >
    <n-button @click="handleSignup" keyboard >新用户注册</n-button>
  </div>
</div>
</template>

<style scoped>
.n-card{
  max-width: 400px;
  margin:1vw auto;
}
.arrow {
  display: inline-block;
  width: 0;
  height: 0;
  border-style: solid;
}

.right {
  border-width: 4px 0 4px 6px;
  border-color: transparent transparent transparent #000;
  margin-left: 5px;
}

.down {
  border-width: 6px 4px 0 4px;
  border-color: #000 transparent transparent transparent;
  margin-left: 5px;
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
.regButton{
    width: 400px;
    margin: 2vw auto;
}
.regButton button{
  width: 400px;
  background-color: #eeeff2;
}

</style>
