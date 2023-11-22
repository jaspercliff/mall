<script setup lang="ts">
import {onMounted, reactive, ref} from "vue";
import {addCategory, getParentCategory, updateCategory} from "@/api";
import {useRouter} from "vue-router";
import type {SelectOption} from "naive-ui"
import {useMessage} from 'naive-ui'
import {useCategoryStore} from "@/stores/category";
import {getListWithAttr} from "@/api/attributeCategory";
interface attribute{
  [id:string] : any
}
let category:attribute = reactive({
  id:'',
  parentId:'0',
  name:'',
  Level: '0' ,
  showStatus: 1,
  navStatus:1,
  productCount: '',
  productUnit: '',
  sort: '0',
  icon: '',
  keywords: '',
  attributeList: []
})
const router = useRouter()
const categoryStore = useCategoryStore()
const selectOptions = ref<Array<SelectOption>>([])
const cascaderOptions = ref([])
const cascaderSelect = ref([])
const message = useMessage()
async function handleAddBrandOrUpdate(){
  if(category.id===''){
    category.attributeList = []
    await category.attributeList.push(...cascaderSelect.value)
   await addCategory(category);
    message.info("添加成功")
    await router.push('/goods/categoryList')
  }else {
    category.attributeList = []
    await category.attributeList.push(...cascaderSelect.value)
    await updateCategory(category)
    categoryStore.category = {
      attributeList: []
    }
    await router.push('/goods/categoryList')
  }
}
function handleReset(){

}
async function getListWithAttribute(){
  const {data} = await getListWithAttr()
  cascaderOptions.value = data
}
// 辅助函数：从 attributeList 对象数组中提取 id 值并返回 id 数组
function extractAttributeIds(attributeList:any) {
  return attributeList.map((item:any) => item.id);
}
onMounted(async ()=>{
     const {data} = await getParentCategory();
     data.unshift({
       id:'0',
       name:'无上级分类'
     })
     // 上级分类
     selectOptions.value = data
  // 获取级联
  await getListWithAttribute()
      // 更新获取store中的category
      Object.assign(category,categoryStore.category)
      console.log(cascaderSelect.value)
      if(category.attributeList.length > 0){
        cascaderSelect.value = extractAttributeIds(category.attributeList);
      }
})
function test(){
  category.attributeList = []
  category.attributeList.push(...cascaderSelect.value)
  console.log(cascaderSelect.value)
}
</script>

<template>
  <n-card hoverable >
    增加品牌
  </n-card>
  <n-card style="max-width: 600px;margin-left: 20px">
    <n-form
        :model="category"
        label-placement="left"
        label-width="auto"
    >
      <n-form-item label="分类名称" >
        <n-input v-model:value="category.name" />
      </n-form-item>
      <n-form-item label="上级分类">
        <n-select
            v-model:value="category.parentId"
            :options="selectOptions"
            value-field="id"
            label-field="name"
        />
      </n-form-item>
      <n-form-item label="数量单位">
        <n-input v-model:value="category.productUnit"/>
      </n-form-item>
      <n-form-item label="排序">
        <n-input v-model:value="category.sort"/>
      </n-form-item>
      <n-form-item label="是否显示">
        <n-radio-group v-model:value="category.showStatus">
          显示:<n-radio :value="1"></n-radio>
          不显示:<n-radio :value="0"></n-radio>
        </n-radio-group>
      </n-form-item>
      <n-form-item label="导航栏是否显示">
        <n-radio-group v-model:value="category.navStatus">
          显示:<n-radio :value="1" ></n-radio>
          不显示:<n-radio :value="0"></n-radio>
        </n-radio-group>
      </n-form-item>
      <n-form-item label="分类图标">
        <n-upload v-model:value="category.icon">
          <n-button @click="">上传</n-button>
        </n-upload>
      </n-form-item>
      <n-form-item label="筛选属性">
            <n-cascader
                v-model:value="cascaderSelect"
                :cascade="true"
                :options="cascaderOptions"
                label-field="name"
                value-field="id"
                children-field="attributeList"
                multiple
                @update-value="test"
            />
      </n-form-item>
      <n-form-item label="关键词">
        <n-input v-model:value="category.keywords"/>
      </n-form-item>
      <n-button type="info" @click="handleAddBrandOrUpdate">保存</n-button>
      <n-button style="margin-left: 20px;" @click="handleReset">重置</n-button>
    </n-form>
  </n-card>
</template>

<style scoped>
.n-card{
  margin: 20px 0;
  font-size: large;
}
.n-form{
  margin-left: 40px;
}

</style>
