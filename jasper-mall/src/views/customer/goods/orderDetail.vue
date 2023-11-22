<script setup lang="ts">
import Header from "@/views/container/header.vue";
import {onMounted, ref} from "vue";
import {getAllOrder, getOrderByStatus} from "@/api/order";
import {useRouter} from "vue-router";
const orderList = ref()
onMounted(async ()=>{
  const {data} = await getAllOrder();
  orderList.value=data
})
function computeStatus(status:any){
  switch (status){
    case 0:
      return "待付款";
    case 1:
      return "待发货";
    case 2:
      return "已发货";
    case 3:
      return "已完成";
    case 4:
      return "已关闭无效订单"
  }
}
async function handleShift(value:any){
  console.log(value)
  if(value=='5'){
    const {data} = await getAllOrder();
    orderList.value=data
  }else{
    const {data} = await getOrderByStatus(value)
    orderList.value=data
  }
}
const router = useRouter()
function handlePay(id:any){
  router.push({path:'pay',query:{
    id:id
    }})
}
</script>

<template>
  <Header/>
  <n-card name="我的订单" hoverable style="margin: 2vw auto;width: 850px;">
      <n-tabs type="line" trigger="click" class="tabs" @update-value="handleShift">
        <n-tab-pane name="5" tab="全部订单" >
            <div hoverable v-for="item in orderList" :key="item.id" style=" width: 600px" >
              <h4 style="display: inline">{{item.createTime}}</h4>
              订单编号: {{item.id}} <br>
              <n-card v-for="good in item.items" :key="good.id">
                <n-image :src="good.productPic" width="100" height="100"/>
                <div class="spanText"> {{good.productName}}</div>
                <div class="spanText">{{good.productPrice}}元</div>
              </n-card>
              实付款: <span style="color: #ed7831;font-size: 30px">{{item.payAmount}}</span>元 <br>
              状态：<span style="font-size: 25px;">{{computeStatus(item.status)}}</span>
              <n-divider/>
            </div>
        </n-tab-pane>
        <n-tab-pane name="0" tab="待付款">
          <div hoverable v-for="item in orderList" :key="item.id" style=" width: 600px" >
            <h4 style="display: inline">{{item.createTime}}</h4>
            订单编号: {{item.id}} <br>
            <n-card v-for="good in item.items" :key="good.id">
              <n-image :src="good.productPic" width="100" height="100"/>
              <div class="spanText"> {{good.productName}}</div>
              <div class="spanText">{{good.productPrice}}元</div>
            </n-card>
            实付款: <span style="color: #ed7831;font-size: 30px">{{item.payAmount}}</span>元 <br>
            状态：<span style="font-size: 25px;">{{computeStatus(item.status)}}</span>
          <n-button @click="handlePay(item.id)" type="primary" style="margin-left: 40px">去付款</n-button>
          </div>
        </n-tab-pane>
        <n-tab-pane name="1" tab="待发货">
          <div hoverable v-for="item in orderList" :key="item.id" style=" width: 600px" >
            <h4 style="display: inline">{{item.createTime}}</h4>
            订单编号: {{item.id}} <br>
            <n-card v-for="good in item.items" :key="good.id">
              <n-image :src="good.productPic" width="100" height="100"/>
              <div class="spanText"> {{good.productName}}</div>
              <div class="spanText">{{good.productPrice}}元</div>
            </n-card>
            实付款: <span style="color: #ed7831;font-size: 30px">{{item.payAmount}}</span>元 <br>
            状态：<span style="font-size: 25px;">{{computeStatus(item.status)}}</span>
          </div>
        </n-tab-pane>
        <n-tab-pane name="2" tab="待收货">
          <div hoverable v-for="item in orderList" :key="item.id" style=" width: 600px" >
            <h4 style="display: inline">{{item.createTime}}</h4>
            订单编号: {{item.id}} <br>
            <n-card v-for="good in item.items" :key="good.id">
              <n-image :src="good.productPic" width="100" height="100"/>
              <div class="spanText"> {{good.productName}}</div>
              <div class="spanText">{{good.productPrice}}元</div>
            </n-card>
            实付款: <span style="color: #ed7831;font-size: 30px">{{item.payAmount}}</span>元 <br>
            状态：<span style="font-size: 25px;">{{computeStatus(item.status)}}</span>
          </div>
        </n-tab-pane>
        <n-tab-pane name="3" tab="已完成">
          <div hoverable v-for="item in orderList" :key="item.id" style=" width: 600px" >
            <h4 style="display: inline">{{item.createTime}}</h4>
            订单编号: {{item.id}} <br>
            <n-card v-for="good in item.items" :key="good.id">
              <n-image :src="good.productPic" width="100" height="100"/>
              <div class="spanText"> {{good.productName}}</div>
              <div class="spanText">{{good.productPrice}}元</div>
            </n-card>
            实付款: <span style="color: #ed7831;font-size: 30px">{{item.payAmount}}</span>元 <br>
            状态：<span style="font-size: 25px;">{{computeStatus(item.status)}}</span>
          </div>
        </n-tab-pane>
        <n-tab-pane name="4" tab="已经取消">
          <div hoverable v-for="item in orderList" :key="item.id" style=" width: 600px" >
            <h4 style="display: inline">{{item.createTime}}</h4>
            订单编号: {{item.id}} <br>
            <n-card v-for="good in item.items" :key="good.id">
              <n-image :src="good.productPic" width="100" height="100"/>
              <div class="spanText"> {{good.productName}}</div>
              <div class="spanText">{{good.productPrice}}元</div>
            </n-card>
            实付款: <span style="color: #ed7831;font-size: 30px">{{item.payAmount}}</span>元 <br>
            状态：<span style="font-size: 25px;">{{computeStatus(item.status)}}</span>
          </div>

        </n-tab-pane>
      </n-tabs>
  </n-card>
</template>

<style scoped>
.n-tab-pane{
  margin: 2vw 0 ;
}
.n-card{
  margin-bottom: 2vw;
}
.spanText{
  margin-left: 30px;
  display: inline-block;
  width: 120px;
  text-align: center;
}
</style>
