<script setup lang="ts">
import Header from "@/views/container/header.vue";
import type {DataTableColumns,DataTableRowKey} from "naive-ui"
import {NButton, NImage, NInputNumber, NPopconfirm,useMessage} from "naive-ui";
import {h, ref, reactive, onMounted, computed} from "vue"
import {deleteCart, getAllCart, updateQuantity} from "@/api/cart";
import {useRouter} from "vue-router";
// 每一行数据都要有唯一的key
const rowKey = (row:any) => row.id
const tableData = ref([])
const router = useRouter()
const message = useMessage()
const columns : DataTableColumns = [
  {
    type:'selection',
    disabled(row:any){
      return row.quantity > row.stock
    }
  },
  {
    title: '序号',
    key: 'num',
    width: 80,
    render(_:any,index:any){
      return index+1;
    },
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
            width: 150,
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
    render(row){
      return[ h(
          NInputNumber,
          {
            buttonPlacement:"both",
            value:row.quantity,
            min:'1',
            align:'center',
            "onUpdate:value": (value:any)=>handleUpdate(row,value)
          }
      ),
      ]
    }
  },
  {
    title: '操作',
    key: 'goodId',
    width: 100,
    render(row:any){
      return [
        h(
            NPopconfirm,
            {
              positiveButtonProps: {
              },
              negativeButtonProps: {
              },
              onPositiveClick: ()=> handleDelete(row),
              negativeText:"取消",
              positiveText:"删除"
            },
            {
              trigger: () => {
                return h(
                    NButton,
                    {
                      type: 'info',
                      size: 'medium',
                    },
                    { default: () => '移除购物车' }
                )
              },
              default: () => {
                return '确认移除购物车？'
              }
            }
        )
      ]
    }
  }
]
onMounted(async ()=>{
  const {data} = await getAllCart()
  console.log(data)
  tableData.value = data
})
async function handleDelete(row:any){
    const response =  await deleteCart(row.id);
    const {data} = await getAllCart()
    tableData.value = data
}
async function handleUpdate(row:any,value:any){
      row.quantity = value
      await updateQuantity(row.id,value)
}
const checkedRowKeysRef = ref<DataTableRowKey[]>([])
function handleCheck (rowKeys: DataTableRowKey[]) {
  checkedRowKeysRef.value = rowKeys
  console.log(checkedRowKeysRef.value)
}
function handleSettlement(){
  console.log(checkedRowKeysRef)
    router.push({path:"/order",query:{
    ids:checkedRowKeysRef.value
    }
    })
}
</script>

<template>
<div>
  <Header/>
  <div class="container">
  <n-card hoverable>
    <div class="head">
      <span>购物车(全部:{{tableData.length}})</span>
      <span>已选商品:{{checkedRowKeysRef.length}}（不含运费）</span>
      <n-button @click="handleSettlement" round style="color: #fff;background-color: #ec5e2a">结算</n-button>
    </div>
    <n-divider/>
    <n-data-table
        :remote="true"
        :columns="columns"
        :data="tableData"
        :single-column="false"
        :single-line="false"
        :row-key="rowKey"
        striped
        @update:checked-row-keys="handleCheck"
    />
  </n-card>
  </div>
</div>
</template>

<style scoped>
.container{
  margin: 2vw 3vw;
}
.n-card{
  border-radius: 20px;
}
.head{
  display: grid;
  grid-template-columns: 20fr 3fr 2fr;
}
</style>
