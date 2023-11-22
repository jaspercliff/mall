<script setup lang="ts">

import Header from "@/views/container/header.vue";
import {h, onMounted, ref} from "vue";
import {useRoute, useRouter} from "vue-router";
import {useMessage, NButton, NImage, NInputNumber, NPopconfirm} from "naive-ui";
import type {DataTableColumns} from "naive-ui"
import { getListByIds} from "@/api/cart";
import {addOrder, confirmOrder} from "@/api/order";
import pca from "@/api/pca.json";
import {addUserAddress, deleteAddress} from "@/api/userReceiveAddress";
const route = useRoute()
const rowKey = (row:any) => row.id
const tableData = ref([])
const router = useRouter()
const columns : DataTableColumns = [
  {
    type:'selection',
  },
  {
    title: '序号',
    key: 'num',
    width: 80,
    render(_:any,index:any){
      return index+1;
    }
  },
  {
    title: '商品名字',
    key: 'productName',
    width: 400
  },
  {
    title: '商品图片',
    key: 'productPic',
    width:250,
    render(row){
      return h(NImage,
          {
            width: 50,
            height: 60,
            src:row.productPic
          },
          null)
    }
  },
  {
    title: '单价',
    key: 'price',
    width: 100
  },
  {
    title: '数量',
    key: 'quantity',
    width:200,
  }]
const confirm = ref(
    {}
)
onMounted(async ()=>{
  if(route.query.ids?.length as number>0){
    const {data} = await getListByIds(route.query.ids)
    tableData.value = data
  }
  const {data} = await confirmOrder(route.query.ids)
  confirm.value = data
  confirm.value.pca = pca
})
const check = ref(1)
const showModal=ref(false)
const message = useMessage()
function cancelCallback () {
  message.success('Cancel')
}
async function submitCallback () {
  const response = await addUserAddress(address.value)
  if (response.data){
    message.success('Submit')
  }else {
    message.error(response.message)
  }
  const {data} = await confirmOrder(route.query.ids)
  confirm.value = data
  confirm.value.pca = pca
}
function handleAddAddress(){
    showModal.value = true
}
const address = ref(
    {
      name:'',
      phoneNumber:'',
      postCode:'',
      province:'',
      city:'',
      region:''
    }
)
// async function handleDeleteAddress(id:string){
//     await deleteAddress(id)
//   message.success("删除成功")
// }
async function handleSubmit(){
  let orderDTO = {
      check:check.value,
      payAmount: confirm.value.payActual,
      freightAmount: confirm.value.freightTotal,
      orderIds: [...route.query.ids as array]
  }
  const response = await addOrder(orderDTO)
  console.log(response)
  await router.push({path:"payA",query:{
  id:response.data
  }
  })
}

</script>

<template>
<div class="container">
  <Header/>
  <div class="address">
    <h2>确定收货地址</h2>
    <hr>
    <n-radio-group class="addressDetail"
                   v-model:value="check"
                   v-for="item in confirm.addressList"
                   :key="item.id"
                   >
      <n-radio
          :value="item.id"
               style="margin-bottom: 1vw"
      >
        {{item.name}}
        {{item.phoneNumber}}
        {{item.province}}-{{item.city}}-{{item.rigion}}-{{item.detailAddress}}
      </n-radio>
    </n-radio-group>
    <n-button @click="handleAddAddress" style="margin-left: 2vw">添加收货地址</n-button>
  </div>
  <div class="good">
    <n-data-table
        :remote="true"
        :columns="columns"
        :data="tableData"
        :single-column="false"
        :single-line="false"
        :row-key="rowKey"
        striped
    />
  </div>
  <n-modal
      style="width: 800px"
      v-model:show="showModal"
      preset="dialog"
      title="添加收货地址"
      content="添加收货地址"
      positive-text="确认"
      negative-text="算了"
      @positive-click="submitCallback"
      @negative-click="cancelCallback"
  >
    <div>
      <n-form
          label-placement="left"
          label-width="auto"
          v-model:value="address">
        <n-form-item label="收货人名称">
          <n-input type="text" v-model:value="address.name"/>
        </n-form-item>
        <n-form-item label="手机号">
          <n-input v-model:value="address.phoneNumber"/>
        </n-form-item>
        <n-form-item label="邮编">
          <n-input v-model:value="address.postCode"/>
        </n-form-item>
        <n-form-item label="选择地址">
          <select name="province" v-model="address.province" style="width: 100px;">
            <option v-for="item in Object.keys(confirm.pca)" :key="item.id" :value="item">
              {{item}}
            </option>
          </select>
          <select name="city" v-model="address.city" v-if="address.province" style="width: 100px;">
            <option v-for="item in Object.keys(pca[address.province])" :key="item.id" :value="item">
                  {{item}}
            </option>
          </select>
          <select name="region" v-model="address.region" v-if="address.city" style="width: 100px;" >
            <option v-for="item in pca[address.province][address.city]" :key="item.id" :value="item">
                  {{item}}
            </option>
          </select>
        </n-form-item>
        <n-form-item label="详细地址">
          <n-input v-model:value="address.detailAddress"/>
        </n-form-item>
      </n-form>
    </div>
  </n-modal>

  <div class="other">
     <h3>  商品总数：{{confirm.productTotal}} </h3>
    <h3>
      运费总价：{{confirm.freightTotal}}
    </h3>
    <h3>
      商品总价：{{confirm.priceTotal}}
    </h3>
    <h3>
      总计：{{confirm.payActual}}
    </h3>
    <n-button color="#db673b" style="width: 100px;" @click="handleSubmit">去结算</n-button>
  </div>
</div>
</template>

<style scoped>
.address{
  margin: 2vw 2vw;
}
.addressDetail{
  margin: 2vw 2vw;
  display: flex;
  flex-direction: column;
}
.other{
  margin: 2vw 4vw;
  display: grid;
  justify-content: end;
}
</style>
