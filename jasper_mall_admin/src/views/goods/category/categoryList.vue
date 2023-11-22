<script setup lang="ts">
import type {DataTableColumns} from "naive-ui"
import {NButton, NPopconfirm, NSwitch, useMessage} from "naive-ui";
import {h, onMounted, reactive, ref} from "vue";
import {changeNavStatus, deleteCategory, pageList} from "@/api/category";
import { getRelationAttrInfoByCid} from "@/api";
import {useRouter} from "vue-router";
import {useCategoryStore} from "@/stores/category";
const message = useMessage()
const router = useRouter()
const categoryStore = useCategoryStore()
const parentId = ref<number>(0)
const columns : DataTableColumns = [
  {
    title: '名字',
    key: 'name',
  },
  {
    title: '上级',
    key: 'parentName',
  },
  {
    title: '排序',
    key: 'sort',
    width: '200px'
  },
  {
    title: '分类级别',
    key: 'level',
    render(row){
      switch (row.level){
        case 0: return '一级分类';
        case 1: return '二级分类';
        case 2: return '三级分类';
        default: return '一级分类';
      }
    }
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
    title: '导航栏显示',
    key: 'NavStatus',
    render(row) {
      return h(NSwitch,
          {
            value: row.navStatus === 1,
            onClick: ()=> handleNavSwitch(row)
          },
      )
    }
  },
  {
    title: '商品数量',
    key: 'productCount',
    width: '200px'
  },
  {
    title: '计量单位',
    key: 'productUnit',
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
const tableData = ref([])
const pagination = reactive({
  pageSize: 8,
  pageSizes: [8,12,16],
  showSizePicker: true,
  page: 1,
  itemCount: 0,
  onChange:async (page: number) => {
    pagination.page= page;
    const {data} = await pageList(parentId.value,pagination.page,pagination.pageSize)
    tableData.value = data.records
    pagination.itemCount = data.total
  },
  onUpdatePageSize:async (pageSize: number) => {
    pagination.pageSize = pageSize
    pagination.page = 1
    const {data} = await pageList(parentId.value,pagination.page,pagination.pageSize)
    tableData.value = data.records
    pagination.itemCount = data.total
  }
})
const selectOptions =[
  {
    label: '一级分类',
    value: 0
  },
  {
    label: '二级分类',
    value: 1
  },
  {
    label: '三级分类',
    value: 2
  }
]
const rowKey = (row:any) => row.name
function handleSwitch(row:any){

}
function handleNavSwitch(row:any){
  if(row.navStatus === 1 ){
    row.navStatus = 0
    changeNavStatus(row)
  }else {
    row.navStatus = 1
    changeNavStatus(row)
  }
}
async function handleDelete(row:any){
  try {
    await deleteCategory(row.id)
    message.info("删除成功")
    const {data} = await pageList(parentId.value,pagination.page,pagination.pageSize)
    tableData.value = data.records
    pagination.itemCount = data.total
  }catch (error){
    message.error('删除失败'+error)
  }
}
function handleAdd(){
    router.push('/goods/addCategory')
}
async function handleUpdate(row:any){
  const {data} =await getRelationAttrInfoByCid(row.id);
  categoryStore.category = row
  categoryStore.category.attributeList = data
  await router.push("/goods/addCategory")
}

onMounted(async ()=>{
  const response = await pageList(parentId.value,pagination.page,pagination.pageSize)
  tableData.value = response.data.records
  pagination.itemCount = response.data.total
})

async function handleSelect(value:number){
    parentId.value = value
  const response = await pageList(parentId.value,pagination.page,pagination.pageSize)
  tableData.value = response.data.records
  pagination.itemCount = response.data.total
}
const select = ref(0)
</script>

<template>
  <n-card hoverable>
    <div class="container">
      <span>数据列表</span>
      <n-select
          :options="selectOptions" placeholder="选择分类"
          v-model:value="select"
      />
      <n-button @click="handleSelect(select)">查询</n-button>
      <n-button @click="handleAdd">增加分类</n-button>
    </div>
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
.container{
  display: grid;
  grid-template-columns: 1fr 1fr 0.2fr 0.5fr;
  gap: 20px;
}
.n-select{
  width: 500px;
}
</style>
