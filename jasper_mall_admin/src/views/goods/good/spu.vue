<script setup lang="ts">
import {h, onMounted, reactive,  ref} from "vue";
import type {Ref} from "vue"
import {NButton, NInput, NPopconfirm, NSwitch} from "naive-ui";
import type{DataTableColumns,StepProps} from 'naive-ui'
import {useStepStore} from "@/stores/step";
import {useRouter} from "vue-router";
import {getAllAttributeCategory} from "@/api/attributeCategory";
import {getRelationAttrInfoByAttrCateId } from "@/api";
import {addGood, uploadGoodPicture} from "@/api/goods";
const currentStatus = ref<StepProps['status']>('finish')
const currentStore = useStepStore()
const router = useRouter()
interface Attr{
    id: string,
    productAttributeCategoryId: string,
    name: string,
    selectType: string,
    inputType: number,
    inputList: string,
    sort: string,
    filterType: string,
    searchType: string,
    relatedStatus: string,
    handAddStatus: string,
    type: string,
}
interface Item{
  value:any
}
const rowKey = (row:any) => row.id
const tableData:any = ref([])
let columns = reactive([
  {
    title: '销售价格',
    key: 'price',
    render (row:any, index:any) {
      return h(NInput, {
        value: row.price,
        onUpdateValue:(value)=>row.price = value
      })
    }
  },
  {
    title: '促销价格',
    key: 'promotionPrice',
    render (row:any, index:any) {
      return h(NInput, {
        value: row.promotionPrice,
        onUpdateValue:(value)=>row.promotionPrice = value
      })
    }
  },
  {
    title: '商品库存',
    key: 'stock',
    render (row:any, index:any) {
      return h(NInput, {
        value: row.stock,
        onUpdateValue:(value)=>row.stock = value
      })
    }
  },
  {
    title: '库存预警值',
    key: 'limit',
    width: '100px',
    render (row:any, index:any) {
      return h(NInput, {
        value: row.limit,
        onUpdateValue:(value)=>row.limit = value
      })
    }
  },
])
const attributeCategoryOptions = ref([])
onMounted(async ()=>{
  const {data} = await getAllAttributeCategory()
  attributeCategoryOptions.value = data
})
const attr = ref([] as Attr[])
const para = ref([] as Attr[])
const checkboxValue:Ref<[][]> = ref([])
const dynamicArray:Ref<Item[]> = ref([])
function onCreateDynamic(){
  return {value:''}
}
const tempColumns = reactive([...columns])
async function handleSelectUpdate(value:any){
  const {data} = await getRelationAttrInfoByAttrCateId(value)
  attr.value = data.filter((attr:any) => attr.type === 0)
  para.value = data.filter((attr:any) => attr.type === 1)
  columns.splice(0,columns.length,...tempColumns)
  // 动态添加表格前面几列
  attr.value.forEach(
      attr =>{
        columns.unshift({
          title: attr.name,
          key: attr.name,
          // ts-ignore
        })
      }
  )
  tableData.value =[]
  dynamicArray.value = []
  checkboxValue.value = []
}
function  createOptions(optionsArray:string[]) {
  return optionsArray.map((option) => ({ name: option}));
}
const  selectArray = ref([])
function generateCombinations(data: string[][]): string[][] {
  const result: string[][] = [];
  const numArrays = data.length;
  const numPermutations = data.reduce((acc, arr) => acc * arr.length, 1);
  for (let i = 0; i < numPermutations; i++) {
    const combination: string[] = [];
    let index = i;
    for (let j = 0; j < numArrays; j++) {
      const array = data[j];
      const arrayLength = array.length;
      const arrayIndex = index % arrayLength;
      combination.push(array[arrayIndex]);
      index = Math.floor(index / arrayLength);
    }
    result.push(combination);
  }
  return result;
}

function handleSku(){
  const skuData = []; // 创建一个临时数组用于存储排列组合后的数据
if (dynamicArray.value.length>0){
  // 使用两层嵌套循环将 checkboxValue 和 dynamicArray 中的值进行排列组合
  for (let i = 0; i < dynamicArray.value.length; i++) {
    for (let j = 0; j < checkboxValue.value.length; j++) {
      if (checkboxValue.value[j]) {
        console.log(checkboxValue.value[j])
        for (let k = 0; k < checkboxValue.value[j].length; k++) {
          const rowData = {
            [columns[0].key]: dynamicArray.value[i].value, // 这里使用动态数组的 value 属性作为第一列的值
            [columns[1].key]: checkboxValue.value[j][k], // 这里的 [columns[2].key] 表示 'stock' 列的 key
          };
          skuData.push(rowData); // 将组合后的对象添加到临时数组中
        }
      }
    }
  }
  tableData.value = [...skuData]
}else {
  let result = generateCombinations(checkboxValue.value)
  const array:any = []
   result.forEach(
      row =>{
        const keys:any = []; // 定义目标属性名
        for (let i = 0; i < attr.value.length; i++) {
          keys.unshift(columns[i].key)
        }
        let object:any = {}
        for (let i = 0; i < row.length; i++) {
            object[keys[i]] = row[i]
        }
        array.push(object)
      }
  )
  console.log(array);
  tableData.value = [...array]
}
}
function prev(){
  currentStore.current--;
  router.push("/goods/promotion")
}
async function next(){
  currentStore.goods.goodsSkuStocks = tableData.value
  currentStore.goods.goodsSkuStocks.map(
      sku => {
        const spData:any = {};
        for (const key in sku) {
          if (key !== "limit" && key !== "price" && key !== "promotionPrice" && key !== "stock") {
            // ts-ignore
            spData[key] = sku[key];
          }
        }
        sku.spData = JSON.stringify(spData);
      }
  )
  console.log(currentStore.goods.goodsSkuStocks)
  let tempArray = []
  for (let i = 0; i < para.value.length; i++) {
    let temp = {
      productAttributeId: para.value[i].id,
      value: selectArray.value[i]
    }
    tempArray.push(temp)
  }
  currentStore.goods.attributeValues = tempArray
  console.log(currentStore.goods)
  await addGood(currentStore.goods)
  currentStore.goods = {
    name:'',
    goodCategoryId: '',
    brandId: '',
    goodAttributeCategoryId:'',
    price: '',
    stock: '',
    sort: '',
    keywords: '',
    isPostFree: 0,
    isPublished: 0,
    picture:'',
    giftGrowth: '0',
    giftPoint: '0',
    isNew: 0,
    isRecommend: 0,
    isVerify: '',
    lowStock: 0,
    promotionStartTime: "2023-07-28 14:17:10",
    promotionEndTime: "2023-07-28 14:17:10",
    promotionPerLimit: '0',
    promotionType: '0',
    promotionPrice: '',
    pointLimit: '0',
    memberPrices: [
      { memberLevelId: 1, memberLevelName: '青铜会员',memberPrice:''},
      { memberLevelId: 2, memberLevelName: '白银会员',memberPrice:''},
      { memberLevelId: 3, memberLevelName: '黄金会员' ,memberPrice:''},
    ],
    goodsFullReductions:[
      {fullPrice:100,reducePrice:20}
    ],
    ladders:[
      {count:0,discount:20}
    ],
    goodsSkuStocks:[],
    attributeValues:[] as any[]
  }
  await router.push("/goods/goodsList")
}
</script>

<template>
  <n-steps :current="currentStore.current as number" :status="currentStatus">
    <n-step title="添加商品信息"/>
    <n-step title="添加商品促销"/>
    <n-step title="添加商品属性spu"/>
  </n-steps>
  <n-card style="max-width: 600px;margin-left: 20px">
    <n-form
        :model="currentStore.goods"
        label-placement="left"
        label-width="auto"
    >
            <n-form-item label="商品类型">
              <n-select
                  :options = attributeCategoryOptions
                  v-model:value="currentStore.goods.goodAttributeCategoryId"
                  label-field="name"
                  value-field="id"
                  @update-value="handleSelectUpdate"
              />
            </n-form-item>
            <n-form-item label="商品参数">
                <n-card hoverable>
                    <n-checkbox-group
                        v-for="(item,index) in attr" :key="item.id"
                        v-model:value="checkboxValue[index]"
                    >
                      <div>
                        <span v-if="item.inputType===0">{{item.name}}:</span>
                        <n-dynamic-input
                            v-if="item.inputType===0"
                            v-model:value="dynamicArray"
                            placeholder="请输入"
                            :on-create="onCreateDynamic"
                        >
                          <template #default="{ value }">
                            <n-input
                                v-model:value="value.value"
                            />
                          </template>
                        </n-dynamic-input>
                      </div>
                      <div v-if="item.inputType===1">{{item.name}}:
                        <br>
                        <n-checkbox v-for="check in item.inputList.split(',') "
                                    :value="check" :label="check"
                        />
                      </div>
                    </n-checkbox-group>
                  <hr>
                  <n-button @click="handleSku">刷新sku表</n-button>
                </n-card>
            </n-form-item>
            <n-form-item>
              <n-data-table
                  :remote="true"
                  :columns="columns"
                  :data="tableData"
                  :single-column="false"
                  :single-line="false"
                  :row-key="rowKey"
                  striped
              />
            </n-form-item>
      <n-form-item label="商品规格">
        <n-card>
          <n-space vertical>
            <div v-for="(item,index) in para" :key="item.id" style="display: grid;grid-template-columns: 0.5fr 2fr">
              <span >{{item.name}}:</span>
              <n-select
                  :options="createOptions(item.inputList.split(','))"
                  label-field="name"
                  value-field="name"
                  :attributeId="item.id"
                  v-model:value="selectArray[index]"
              />
            </div>
          </n-space>
        </n-card>

      </n-form-item>
      <n-button-group>
        <n-button @click="prev" :disabled="currentStore.current===1">上一步</n-button>
        <n-button @click="next" >保存</n-button>
      </n-button-group>
    </n-form>
  </n-card>
</template>

<style scoped>

</style>
