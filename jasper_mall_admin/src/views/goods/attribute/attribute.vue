<script setup lang="ts">
import type {DataTableColumns} from "naive-ui"
import {NButton, NPopconfirm, useMessage} from "naive-ui";
import {h, onMounted, reactive, ref} from "vue";
import {useRoute, useRouter} from "vue-router";
import {deleteAttribute, pageAttributeList} from "@/api/attribute";
import {useAttribute} from "@/stores/attribute";

const message = useMessage()
const router = useRouter()
const route = useRoute()
const tableData = ref([])
const attributeStore = useAttribute()
const rowKey = (row:any) => row.name
const {cid,type}  = route.query
const columns : DataTableColumns = [
  {
    title: '序号',
    key: 'ket',
    render(_,index){
      return index+1;
    }
  },
  {
    title: '属性名称',
    key: 'name',
  },
  {
    title: '商品类型',
    key: 'attributeCount',
    render(){
      return route.query.name
    }
  },
  {
    title: '属性是否可选',
    key: 'selectType',
    render(row){
      switch (row.selectType){
        case 0: return '唯一';
        case 1: return '单选';
        case 2: return '多选';
      }
    },
  },
  {
    title: '属性值的录入方式',
    key: 'inputType',
    render(row){
      switch (row.inputType){
        case 0: return '手工录入';
        case 1: return '从列表选取';
      }
    }
  },
  {
    title: '可选值列表',
    key: 'inputList',
  },
  {
    title: '排序',
    key: 'sort',
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
                      color: 'red'
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
const pagination = reactive({
  pageSize: 6,
  pageSizes: [6,8,12],
  showSizePicker: true,
  page: 1,
  itemCount: 0,
  onChange:async (page: number) => {
    pagination.page= page;
    const response = await pageAttributeList(cid,type,pagination.page,pagination.pageSize)
    tableData.value = response.data.records
    pagination.itemCount = response.data.total
  },
  onUpdatePageSize:async (pageSize: number) => {
    pagination.pageSize = pageSize
    pagination.page = 1
    const response = await pageAttributeList(cid,type,pagination.page,pagination.pageSize)
    tableData.value = response.data.records
    pagination.itemCount = response.data.total
  }
})
function handleUpdate(row:any){
  attributeStore.attribute = row
  router.push("/goods/addAttribute")
}
async function handleDelete(row:any){
  await deleteAttribute(row.id)
  message.success("删除成功")
  await pageAttrList()
}
function handleAttrAdd(){
router.push({path:"/goods/addAttribute",query:{
  type: type
  }
})
}
async function pageAttrList(){
  const response = await pageAttributeList(cid,type,pagination.page,pagination.pageSize)
  tableData.value = response.data.records
  pagination.itemCount = response.data.total
}
onMounted(()=>{
    pageAttrList()
})
</script>

<template>
  <n-card hoverable>
    <span>数据列表</span>
    <n-button @click="handleAttrAdd" style="position: absolute;right: 20px" v-show="Number(type)===0">增加属性</n-button>
    <n-button @click="handleAttrAdd" style="position: absolute;right: 20px" v-show="Number(type)===1">增加规格</n-button>
  </n-card>
  <n-data-table
      :remote="true"
      :columns="columns"
      :data="tableData"
      :single-column="false"
      :single-line="false"
      :pagination="pagination"
      :row-key="rowKey"
      striped
  />
</template>

<style scoped>
.n-data-table{
  margin-left: 30px;
}
.n-card{
  margin:20px 0;
}
.n-select{
  width: 500px;
}
</style>
