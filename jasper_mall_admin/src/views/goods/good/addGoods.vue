<script setup lang="ts">
import {onMounted, ref} from 'vue'
import type {StepProps} from "naive-ui"
import {useStepStore} from "@/stores/step";
import {getAllBrand, getCategoryCascade} from "@/api";
import {uploadGoodPicture} from "@/api/goods";
import type {UploadCustomRequestOptions} from "naive-ui";
import {useRouter} from "vue-router";
const currentStatus = ref<StepProps['status']>('finish')
const currentStore = useStepStore()
const cascaderOptions = ref([])
const selectBrandOptions = ref()
const router = useRouter()
async function getCategoryCascader(){
  const {data} = await getCategoryCascade()
  cascaderOptions.value = data
}
async function getAllBrands(){
  const {data} = await getAllBrand()
  selectBrandOptions.value = data
}

onMounted(async ()=>{
  await getCategoryCascader()
  await getAllBrands()
})
function prev(){
    if (currentStore.current=== 1){
      return
    }else {
      currentStore.current--;
    }
}
function next(){
  router.push("/goods/promotion")
    currentStore.current++
}
//上传
const handleUpload = async ({ file }: UploadCustomRequestOptions) => {
  const formData = new FormData();
  formData.append('file', file.file as File);
  //上传接口
  const {data} = await uploadGoodPicture(formData)
  currentStore.goods.picture = data
};
</script>

<template>
  <n-steps :current="currentStore.current as number" :status="currentStatus">
    <n-step title="添加商品信息"/>
    <n-step title="添加商品促销"/>
    <n-step title="添加商品属性spu"/>
  </n-steps>
  <n-card style="max-width: 52vw;margin-left: 20px">
    <n-form
        :model="currentStore.goods"
        label-placement="left"
        label-width="auto"
    >
      <n-form-item label="商品名称" required trigger="blur">
        <n-input v-model:value="currentStore.goods.name" />
      </n-form-item>
      <n-form-item label="商品分类">
        <n-cascader
            v-model:value="currentStore.goods.goodCategoryId"
            :options="cascaderOptions"
            label-field="name"
            value-field="id"
            children-field="list"
            :cascade="true"
            check-strategy="child"
        />
      </n-form-item>
      <n-form-item label="商品品牌">
        <n-select
            :options = selectBrandOptions
            v-model:value="currentStore.goods.brandId"
            label-field="name"
            value-field="id"
        />
      </n-form-item>
      <n-form-item label="商品售价">
        <n-input v-model:value="currentStore.goods.price"/>
      </n-form-item>
      <n-form-item label="商品库存">
        <n-input v-model:value="currentStore.goods.stock"/>
      </n-form-item>
      <n-form-item label="库存预警值">
        <n-input v-model:value="currentStore.goods.lowStock"/>
      </n-form-item>
      <n-form-item label="商品排序">
        <n-input v-model:value="currentStore.goods.sort"/>
      </n-form-item>
      <n-form-item label="商品关键字">
        <n-input v-model:value="currentStore.goods.keywords"/>
      </n-form-item>
      <n-form-item label="是否免邮">
        <n-radio-group v-model:value="currentStore.goods.isPostFree">
          是: <n-radio :value="1"></n-radio>
          否: <n-radio :value="0"></n-radio>
        </n-radio-group>
      </n-form-item>
      <n-form-item label="是否发布">
        <n-radio-group v-model:value="currentStore.goods.isPublished">
          是: <n-radio :value="1"></n-radio>
          否: <n-radio :value="0"></n-radio>
        </n-radio-group>
      </n-form-item>
      <n-form-item label="商品图片">
        <n-upload action="#" :custom-request="handleUpload"
        :show-file-list="false"
        >
          <n-button>上传</n-button>
        </n-upload>
      </n-form-item>
      <n-form-item>
        <n-image v-if="currentStore.goods.picture" :src="currentStore.goods.picture" width="200" />
      </n-form-item>
      <n-button-group>
        <n-button @click="prev" :disabled="currentStore.current===1">上一步</n-button>
        <n-button @click="next" >下一步</n-button>
      </n-button-group>
    </n-form>
  </n-card>
>
</template>

<style scoped>
.n-card{
  margin-top: 25px;
}
.n-steps{
  margin-top: 20px;
  margin-left: 20px;
}
</style>
