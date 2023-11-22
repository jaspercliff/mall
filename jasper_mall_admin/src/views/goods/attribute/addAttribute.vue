<script setup lang="ts">
import {onMounted, reactive, ref} from "vue";
import {useRoute, useRouter} from "vue-router";
import type {SelectOption} from "naive-ui"
import {useMessage} from 'naive-ui'
import {getAllAttributeCategory} from "@/api/attributeCategory";
import {addAttribute, updateAttribute} from "@/api";
import {useAttribute} from "@/stores/attribute";
const router = useRouter()
const message = useMessage()
const route = useRoute()
const {type} = route.query
const attributeStore = useAttribute()
let attribute = reactive({
  id:'',
  productAttributeCategoryId:'',
  name:'',
  selectType: 0,
  inputType: 0,
  inputList:'',
  sort: 0,
  filterType: 0,
  searchType: 0,
  relatedStatus: 0,
  handAddStatus: 0,
  type: type
})
const rules = {
    name:{
      required: true,
      message: '填写属性名称',
      trigger: ['blur']
    }
}
async function handleAddOrUpdate() {
if(attribute.id===''){
 await addAttribute(attribute)
  message.success("添加成功")
  await router.push('/goods/goodsAttribute')
}else{
   const response = await updateAttribute(attribute)
    attributeStore.attribute={
     id: ''
    }
  message.info("保存成功")
  await router.push("/goods/goodsAttribute")
}
}
function handleReset(){
}
const selectOptions = ref<Array<SelectOption>>([])
onMounted(async ()=>{
  const {data} = await getAllAttributeCategory();
  selectOptions.value = data
  if(attributeStore.attribute.id!==''){
    Object.assign(attribute,attributeStore.attribute)
  }
})
</script>

<template>
  <n-card hoverable >
    增加品牌
  </n-card>
  <n-card style="max-width: 600px;margin-left: 20px">
    <n-form
        :model="attribute"
        label-placement="left"
        label-width="auto"
        :rules="rules"
    >
      <n-form-item label="属性名称" path="name">
        <n-input v-model:value="attribute.name" />
      </n-form-item>
      <n-form-item label="商品类型">
        <n-select
            :disabled="attributeStore.attribute.id !=='' "
            v-model:value="attribute.productAttributeCategoryId"
            :options="selectOptions"
            value-field="id"
            label-field="name"
            placeholder="请选择"
        />
      </n-form-item>
      <n-form-item label="分类筛选样式">
        <n-radio-group v-model:value="attribute.filterType">
          普通: <n-radio :value="0"></n-radio>
          颜色: <n-radio :value="1"></n-radio>
        </n-radio-group>
      </n-form-item>
      <n-form-item label="能否进行检索">
        <n-radio-group v-model:value="attribute.searchType">
          不需要检索: <n-radio :value="0"></n-radio>
          关键字检索: <n-radio :value="1"></n-radio>
          范围检索: <n-radio :value="2"></n-radio>
        </n-radio-group>
      </n-form-item>
      <n-form-item label="商品属性关联">
        <n-radio-group v-model:value="attribute.relatedStatus">
          不关联: <n-radio :value="0"></n-radio>
          关联: <n-radio :value="1"></n-radio>
        </n-radio-group>
      </n-form-item>
      <n-form-item label="属性是否可选">
        <n-radio-group v-model:value="attribute.inputType">
          手工录入: <n-radio :value="0"></n-radio>
          从下面列表选择: <n-radio :value="1"></n-radio>
        </n-radio-group>
      </n-form-item>
      <n-form-item label="属性可选值列表">
        <n-input v-model:value="attribute.inputList"></n-input>
      </n-form-item>
      <n-form-item label="是否支持手动新增">
        <n-radio-group v-model:value="attribute.handAddStatus">
          不支持: <n-radio :value="0"></n-radio>
          支持: <n-radio :value="1"></n-radio>
        </n-radio-group>
      </n-form-item>
      <n-form-item label="排序">
        <n-input v-model:value="attribute.sort"></n-input>
      </n-form-item>
      <n-button type="info" @click="handleAddOrUpdate">保存</n-button>
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
