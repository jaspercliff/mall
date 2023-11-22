<script setup lang="ts">
import type {DataTableColumns} from "naive-ui"
import {NButton, NInput, NPopconfirm, useMessage} from "naive-ui";
import {h, onMounted, reactive, ref} from "vue";
import {useRouter} from "vue-router";
import {
  addAttributeCategory,
  deleteAttributeCategory,
  pageAttributeCategoryList,
  updateAttributeCategory,
} from "@/api/attributeCategory";

const message = useMessage()
const router = useRouter()
const columns : DataTableColumns = [
  {
    title: '序号',
    key: 'ket',
    render(_,index){
      return index+1;
    }
  },
  {
    title: '名字',
    key: 'name',
  },
  {
    title: '属性数量',
    key: 'attributeCount',
  },
  {
    title: '参数数量',
    key: 'paramCount',
  },
  {
    title: '设置',
    key: 'setting',
    render(row) {
      return [
        h(NButton, {
              type: 'default',
              onClick: () => handleAttribute(row),
              style: "margin-right:20px"
            },
            {
              default: () => '属性列表'
            }),
        h(NButton, {
              type: 'default',
              onClick: () => handleParam(row)
            },
            {
              default: () => '参数列表'
            })
      ]
    }
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
    const {data} = await pageAttributeCategoryList(pagination.page,pagination.pageSize)
    tableData.value = data.records
    pagination.itemCount = data.total
  },
  onUpdatePageSize:async (pageSize: number) => {
    pagination.pageSize = pageSize
    pagination.page = 1
    const {data} = await pageAttributeCategoryList(pagination.page,pagination.pageSize)
    tableData.value = data.records
    pagination.itemCount = data.total
  }
})
const tableData = ref([])
const rowKey = (row:any) => row.name
async function handleDelete(row:any){
  try {
    await deleteAttributeCategory(row.id)
    message.info("删除成功")
    const {data} = await pageAttributeCategoryList(pagination.page,pagination.pageSize);
    tableData.value = data.records
    pagination.itemCount = data.total
  }catch (error){
    message.error('删除失败'+error)
  }
}
function handleAttribute(row:any) {
router.push({path:'/goods/attribute',query:{
    cid: row.id,
    type: 0,
    name:row.name
  }})
}
function handleParam(row:any) {
  router.push({path:'/goods/attribute',query:{
      cid: row.id,
      type: 1,
      name:row.name
    }})
}

const visible = ref(false)
function handleAdd(){
  visible.value = true
}
function handleUpdate(row:any){
    visible.value = true
    attributeCategory.id = row.id
    attributeCategory.name = row.name
}
let  attributeCategory = reactive({
  id:'',
  name:''
})
onMounted(async ()=>{
  const response = await pageAttributeCategoryList(pagination.page,pagination.pageSize)
  tableData.value = response.data.records
  pagination.itemCount = response.data.total
})
async function onPositiveClick(){
  if(attributeCategory.id===''){
    await addAttributeCategory(attributeCategory)
    message.success("添加成功！！！")
    const {data} = await pageAttributeCategoryList(pagination.page,pagination.pageSize);
    tableData.value = data.records
    pagination.itemCount = data.total
    visible.value = false
  }else{
    const {data} = await updateAttributeCategory(attributeCategory)
    message.success("修改成功！！！")
    const response = await pageAttributeCategoryList(pagination.page,pagination.pageSize);
    tableData.value = response.data.records
    visible.value = false
    attributeCategory.id=''
    attributeCategory.name=''
  }
}
function onNegativeClick(){
  visible.value = false
  attributeCategory.id=''
  attributeCategory.name=''
}
</script>

<template>
  <n-card hoverable>
      <span>数据列表</span>
      <n-button @click="handleAdd" style="position: absolute;right: 20px">增加分类</n-button>
  </n-card>
  <n-modal v-model:show="visible"
           positive-text="确认"
           negative-text="算了"
           preset="dialog"
           title="添加或者修改"
           :mask-closable="false"
           @positive-click="onPositiveClick"
           @negative-click="onNegativeClick"
  >
    <n-card>
      <n-form :model="attributeCategory">
        <n-form-item label="name">
          <n-input v-model:value="attributeCategory.name"/>
        </n-form-item>
      </n-form>
    </n-card>
  </n-modal>
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
