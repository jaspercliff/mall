<script setup lang="ts">

import Header from "@/views/container/header.vue";
import {onMounted, ref} from "vue";
import {useRoute, useRouter} from "vue-router";
import {buy, getOrderById, getOrderDetail} from "@/api/order";
import {useMessage} from "naive-ui"
const orderDetail = ref({
  id:'',
  payAmount:'',
  delayTime:'',
  address:''
})
const route = useRoute()
onMounted(async ()=>{
  orderDetail.value.id = route.query.id as string
  console.log(route.query.id)
  const {data} = await getOrderDetail(orderDetail.value.id)
  const {payAmount,delayTime,address,goodList} = data
  orderDetail.value.payAmount = payAmount
  orderDetail.value.delayTime = delayTime
  orderDetail.value.address = address
})
const message= useMessage()
const router = useRouter()
async function handlePay(){
  await buy()
  message.info("付款成功")
  await router.push("/orderDetail")
}
</script>

<template>
<Header/>
  <n-card hoverable>
    <n-result status="success">
      <h1 style="text-align: center">订单提交成功！可以前往付款了</h1>
      <h3>请在 <h1><n-gradient-text type="success"><n-countdown @finish="handleFinish" :duration="orderDetail.delayTime"/></n-gradient-text></h1>  内完成支付,超时将取消订单!!
        应付金额: <h1><n-gradient-text type="success">{{orderDetail.payAmount}}</n-gradient-text></h1> 元</h3>
    </n-result>
    <n-divider/>
    <n-collapse>
      <n-collapse-item title="订单详情">
        <div>
          订单号:{{orderDetail.id}} <br>
          收货信息:{{orderDetail.address}}<br>
          应付金额:{{orderDetail.payAmount}}<br>
        </div>
      </n-collapse-item>
    </n-collapse>
  </n-card>
  <n-card>
    <n-button @click="handlePay">支付宝支付</n-button>
  </n-card>

</template>

<style scoped>
h1{
  display: inline;
}
.n-card{
  margin:2vw 0vw
}
</style>
