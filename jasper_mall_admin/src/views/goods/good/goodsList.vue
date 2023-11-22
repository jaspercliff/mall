<script setup lang="ts">
import {NButton, NPopconfirm, NImage,NSwitch, useMessage} from "naive-ui";
import type {DataTableColumns} from "naive-ui"
import { h, onMounted, reactive, ref} from "vue";
import {changeStatusAll, deleteGoods, getGoodById, pageGoodsList} from "@/api/goods";
import Search from "@/components/icons/search.vue";
import {getAllBrand, getCategoryCascade} from "@/api";
import {useStepStore} from "@/stores/step";
import {useRouter} from "vue-router";
const message = useMessage()
const router = useRouter()
const columns : DataTableColumns = [
  {
    type:'selection',
  },
  {
    title: '序号',
    key: 'num',
    render(_,index){
      return index+1;
    }
  },
  {
    title: '商品编号',
    key: 'id'
  },
  {
    title: '商品图片',
    key: 'picture',
    render(row){
      return h(NImage,
          {
            width: 100,
            src:row.picture
          })
    }
  },
  {
    title: '商品名称',
    key: 'name',
    render(row){
      return h('div',
      [
          h('span',{},{default:()=>{return `名称：${row.name}`}}),
          h('br'),
          h('span',{},{default:()=>{return `品牌: ${row.brand}`}})
      ]
      )
    }
  },
  {
    title: '价格',
    key: 'price',
  },
  {
    title: '排序',
    key: 'sort',
    width: '100px'
  },
  {
    title: '销量',
    key: 'sale',
  },
  {
    title: '标签',
    key: 'a',
    width: '100px',
    render(row) {
      return [
          h(NSwitch,
          {
            value: row.isPublished === 1 ,
            onClick: ()=>handleClick1(row),
          },
              {
                  checked:()=>{
                   return "已发布"
                  },
                  unchecked:()=>{return "未发布"}
              }
          ),
        h(NSwitch,
            {
              value: row.isNew === 1,
              onClick: ()=> handleClick2(row)
            },
            {
              checked:()=>{return "新品"},
              unchecked:()=>{return "！新品"}
            }
        ),
        h(NSwitch,
            {
              value: row.isRecommend === 1,
              onUpdateValue: ()=> handleClick3(row)
            },
            {
              checked:()=>{return "推荐"},
              unchecked:()=>{return "不推荐"}
            }
        )
      ]
    }
  },
  {
    title: '操作',
    key: 'goodId',
    render(row:any){
      return [
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
const publishOptions = [
  {
    label:'上架',
    value: 1
  },
  {
    label: '未上架',
    value: 0
  }
]
const verifyOptions = [
  {
    label:'已审核',
    value: 1
  },
  {
    label: '未审核',
    value: 0
  }
]
const pagination = reactive({
  pageSize: 4,
  pageSizes: [4,8,12],
  showSizePicker: true,
  page: 1,
  itemCount: 0,
  onChange:async (page: number) => {
    pagination.page= page;
    await handleCommonPage()
  },
  onUpdatePageSize:async (pageSize: number) => {
    pagination.pageSize = pageSize
    pagination.page = 1
    await handleCommonPage()
  }
})
// 每一行数据都要有唯一的key
const rowKey = (row:any) => row.id
const tableData = ref([])
async function handleCommonPage(){
  const {data} = await pageGoodsList({...searchCondition,pageNum:pagination.page,pageSize:pagination.pageSize})
  tableData.value =  data.records
  pagination.itemCount = data.total
}
function handleClick1(row:any){
  row.isPublished = row.isPublished === 1 ? 0 : 1;
  changeStatusAll(row)
}
function handleClick2(row:any){
  row.isNew = row.isNew === 1 ? 0 : 1;
  changeStatusAll(row)
}
function handleClick3(row:any){
  row.isRecommend = row.isRecommend === 1 ? 0 : 1;
  changeStatusAll(row)
}
const goodStore = useStepStore()
async function handleUpdate(row:any){
    const {data} = await getGoodById(row.id)
    goodStore.goods = data
    await router.push("/goods/addGoods")
}
async function handleDelete(row:any){
   await deleteGoods(row.id)
  message.info("删除成功")
    await handleCommonPage()
}

function handleReset(){
  searchCondition.id = '';
  searchCondition.name = '';
  searchCondition.brandId = '';
  searchCondition.goodCategoryId = '';
  searchCondition.isPublished = '';
  searchCondition.isVerify = '';
}
const searchCondition = reactive({
    id:'',
    name:'',
    brandId: '',
    goodCategoryId:'',
    isPublished: '',
    isVerify:'',
})
const cascaderOptions = ref([])
const selectBrandOptions = ref()

async function getCategoryCascader(){
  const {data} = await getCategoryCascade()
  cascaderOptions.value = data
}
async function getAllBrands(){
  const {data} = await getAllBrand()
  selectBrandOptions.value = data
}
async function getPageGood(){
  const {data} = await pageGoodsList({...searchCondition,pageNum:pagination.page,pageSize:pagination.pageSize})
  tableData.value =  data.records
  pagination.itemCount = data.total
}
async function handSearch(){
    await getPageGood()
}
onMounted(async ()=>{
  // 获取筛选条件
  await getCategoryCascader()
  await getAllBrands()
  await getPageGood()
})
</script>

<template>
  <n-card>
    <search/>筛选搜索
    <n-form
    v-model:value="searchCondition"
    label-placement="left"
    label-width="auto"
    >
      <n-form-item label="输入搜索" >
        <n-input v-model:value="searchCondition.name" placeholder="商品名称"/>
      </n-form-item>
      <n-form-item label="商品分类">
        <n-cascader
            v-model:value="searchCondition.goodCategoryId"
            :options="cascaderOptions"
            label-field="name"
            value-field="id"
            children-field="list"
            :cascade="true"
        />
      </n-form-item>
      <n-form-item label="商品货号">
        <n-input v-model:value="searchCondition.id" placeholder="商品货号"/>
      </n-form-item>
      <n-button @click="handSearch" style="width: 60px">查询</n-button>
      <n-form-item label="商品品牌">
          <n-select
            :options = selectBrandOptions
            v-model:value="searchCondition.brandId"
            label-field="name"
            value-field="id"
            />
      </n-form-item>
      <n-form-item label="上架状态">
        <n-select
          :options =publishOptions
          v-model:value="searchCondition.isPublished"
          />
      </n-form-item>
      <n-form-item label="审核状态">
        <n-select
            :options =verifyOptions
            v-model:value="searchCondition.isVerify"
        />
      </n-form-item>
      <n-button @click="handleReset" style="width: 60px">重置</n-button>
    </n-form>
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

.n-form{
  display: grid;
  grid-template-columns: 2fr 2fr 2fr 2fr;
.n-form-item{
  width: 300px;
}
}
</style>
