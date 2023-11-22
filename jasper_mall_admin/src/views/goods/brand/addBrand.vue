<script setup lang="ts">
import {onMounted, reactive} from "vue";
import {addBrand, updateBrand} from "@/api";
import {useRouter} from "vue-router";
import {useMessage} from 'naive-ui'
import {useBrandStore} from "@/stores/brand";

let brand = reactive({
  id:'',
  name:'',
  firstLetter:'',
  sort: '0' ,
  showStatus: 1,
  logo: '',
  bigPic: '',
  brandStory: '',
  goodCount: '0',
  goodCommentCount:'0'
})
function onlyNumber(value:any){
  return !value || /^\d+$/.test(value)
}
const router = useRouter()
const brandStore = useBrandStore()
const message = useMessage()
async function handleAddBrandOrUpdate(){
  if(brand.id===''){
    await addBrand(brand);
    await router.push('/goods/brandManage')
  }else {
    const response = await updateBrand(brand)
    brandStore.brand = {}
    await router.push('/goods/brandManage')
  }
}
onMounted(()=>{
  // if(brandStore.brand.id !==''){
    Object.assign(brand,brandStore.brand)
  // }
})

function handleReset(){
  brand.name = ''; // 将name属性还原为空字符串
  brand.firstLetter = ''; // 将firstLetter属性还原为空字符串
  brand.sort = '0'; // 将sort属性还原为'0'
  brand.showStatus = 1; // 将showStatus属性还原为'1'
  brand.logo = ''; // 将logo属性还原为空字符串
  brand.bigPic = ''; // 将bigPic属性还原为空字符串
  brand.brandStory = ''; //
}
</script>

<template>
  <n-card hoverable >
    增加品牌
  </n-card>
  <n-card style="max-width: 600px;margin-left: 20px">
    <n-form
        :model="brand"
    >
      <n-form-item label="名字" >
        <n-input v-model:value="brand.name" />
      </n-form-item>
      <n-form-item label="品牌首字母">
        <n-input v-model:value="brand.firstLetter"/>
      </n-form-item>
      <n-form-item label="品牌logo">
        <n-upload v-model:value="brand.logo">
          <n-button @click="">上传</n-button>
        </n-upload>
      </n-form-item>
      <n-form-item label="专区大图">
        <n-upload>
          <n-button>上传</n-button>
        </n-upload>
      </n-form-item>
      <n-form-item label="排序">
        <n-input :value="brand.sort.toString()"/>
      </n-form-item>
      <n-form-item >
        <n-radio-group v-model:value="brand.showStatus">
          显示:<n-radio :value="1" ></n-radio>
          不显示:<n-radio :value="0"></n-radio>
        </n-radio-group>
      </n-form-item>
      <n-form-item label="品牌故事">
        <n-input type="textarea" v-model:value="brand.brandStory" />
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
