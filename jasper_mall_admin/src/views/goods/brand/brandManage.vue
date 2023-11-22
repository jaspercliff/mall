<script setup lang="ts">
import {h, onMounted, ref} from "vue";
import {changeShowStatus, deleteBrand, getAllBrand} from "@/api";
import type {DataTableColumns} from "naive-ui"
import {NButton, NPopconfirm, NSwitch, useMessage} from "naive-ui";
import router from "@/router";
import {useRoute} from "vue-router";
import {useBrandStore} from "@/stores/brand";

const brandStore = useBrandStore()
type rowData = {
  id:string
  key:string,
  name:string,
  sort:string
  firstLetter:string,
  showStatus:string
  goodCommentCount:number
  goodCount:number
}
function handleSwitch(row:any){
  if(row.showStatus ===1){
    row.showStatus = 0
     changeShowStatus(row)
  }else {
    row.showStatus = 1
    changeShowStatus(row)
  }
}
function handleUpdate(row:rowData){
  brandStore.brand = row
  router.push({path:'/goods/addBrand'})
}

const message = useMessage()
async function handleDelete(row:rowData){
  try {
    await deleteBrand(row.id);
    message.info("删除成功")
    const response = await getAllBrand();
    data.value = response.data
  }catch (error){
    message.error('删除失败'+error)
  }
}
const columns : DataTableColumns = [
  {
    type:'selection',
    disabled(row){
      return row.name === '三星'
    }
  },
  {
    title: '名字',
    key: 'name',
  },

  {
    title: '首字母',
    key: 'firstLetter',
  },
  {
    title: '是否显示',
    key: 'showStatus',
    render(row) {
      return h(NSwitch,
          {
        value: row.showStatus === 1,
            onClick: ()=> handleSwitch(row)
          },
      )
    }
  },
  {
    title: '评价',
    key: 'goodCommentCount',
    width: '200px'
  },
  {
    title: '商品',
    key: 'goodCount',
  },
  {
    title: '操作',
    key: 'goodId',
    render(row:any){
      return [
        h(NButton,
            {
              type:'info',
              onclick: ()=> handleUpdate(row)
            },
            {
              default: ()=> '修改'
            }),
        h(
            NPopconfirm,
            {
              positiveButtonProps: {
                color:'red'
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
                      type: 'warning',
                      size: 'medium',
                    },
                    { default: () => '删除' }
                )
              },
              default: () => {
                return '确认删除该节点？'
              }
            }
        )
    ]
    }
  }
]
columns.map(
    column => {
      column.resizable=true;
      column.minWidth=100;
      column.maxWidth=500
})
const data = ref([])
const pagination = ref()
const rowKey = (row:rowData) => row.name
function handleAdd(){
  router.push('/goods/addBrand')
}
const route = useRoute()
onMounted(()=>{
  getAllBrand().then(
      response =>{
        data.value=response.data
      }
  )
})
</script>

<template>
  <n-card hoverable>
    <span>数据列表</span>
    <n-button @click="handleAdd" style="position:absolute; right: 20px">增加品牌</n-button>
  </n-card>
<n-data-table
  :columns="columns"
  :data="data"
  :single-column="false"
  :single-line="false"
  :pagination="pagination"
  :row-key="rowKey"
  />
<!--  rowKey标识每一行-->
</template>

<style scoped>
.n-card{
  margin-top: 20px;
}
.n-data-table{
  margin-top: 20px;
}
</style>
