<script setup lang="ts">
import {reactive, ref} from "vue";
import Wx from "@/assets/svg/wx.vue";
import {validateUsername, validateEmail, validatePassword, validateChecked} from "@/utils/auth/validate";
import {getCode, getUserByUsername,  register} from "@/api/user";
import User from "@/assets/svg/user.vue";
import {useRouter} from "vue-router";
import {useUserRegisterStore} from "@/stores/userRegister";
const router = useRouter()
const confirmation = ref<string>("")
const read = ref(false)
interface User {
  username: string,
    password: string,
  email: string,
}
const user = reactive<User>({
    username: '',
    password: '',
    email: '',
});
const userRegister = useUserRegisterStore();
function isUsernameExist(){
  getUserByUsername(user.username).then(
  )
}
const rules = {
  username: {
    required: true,
    message: '请输入姓名',
    trigger: 'blur'
  },
  email:{
    required: true,
    message: '请输入邮箱',
    trigger: 'blur'
  },
}
function handleEmail() {
    if (
        validateUsername(user.username) &&
        validatePassword(user.password, confirmation.value) &&
        validateEmail(user.email)&&
        validateChecked(read.value))
    {
        getCode(user.email).then(
           response => {
             userRegister.user = user
             router.push({name:'emailCode'})
           }
        )
    }
}
</script>

<template>
  <div>

    <n-message-provider>
      <n-card hoverable embedded>
        <n-form
        :rules="rules"
        :model="user"
        >
          <h1 style="font-weight: normal;margin-bottom: 10px">创建账户</h1>
          <n-form-item label="您的姓名" path="username">
            <n-input @blur="isUsernameExist" v-model:value="user.username"
                     placeholder="please input your username"/>
          </n-form-item>
          <n-form-item label="电子邮件" path="email">
            <n-input @blur="validateEmail(user.email)" v-model:value="user.email"
                     placeholder="please input your email or phone number"/>
          </n-form-item>
          <n-form-item label="密码" path="pwd1">
            <n-input type="password" v-model:value="user.password" placeholder="please input your password"/>
          </n-form-item>
          <n-form-item label="再次输入密码" path="pwd2">
            <n-input type="password" v-model:value="confirmation"
                     @blur="validatePassword(user.password,confirmation)"
                     placeholder="please input your password again"/>
          </n-form-item>
          <n-form-item>
            <n-checkbox v-model:checked="read">
              我已阅读并同意使用条件及隐私声明，并同意我的部分个人信息将根据隐私声明在境外处理。
            </n-checkbox>
          </n-form-item>
          <n-button color="#edd083" keyboard style="width: 100%;margin-bottom: 10px" @click="handleEmail">验证电子邮件
          </n-button>
          <div class="line-with-text">
            <hr>
            <span class="text">其他注册方式</span>
          </div>
          <n-button round style="margin-top: 20px;width: 100%;background-color: #72d446">
            <Wx/>
            &nbsp 微信注册
          </n-button>
          <n-button round style="margin-top: 20px;width: 100%;background-color: #fff">
            <Wx/>
            &nbsp google注册
          </n-button>
        </n-form>
      </n-card>
    </n-message-provider>
  </div>
</template>

<style scoped>
.n-card {
  max-width: 400px;
  margin: 1vw auto;
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
