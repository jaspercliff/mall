<script setup lang="ts">
import {ref} from "vue";
import {StepProps} from "naive-ui";
import {useStepStore} from "@/stores/step";
import {useRouter} from "vue-router";
const currentStatus = ref<StepProps['status']>('finish')
const currentStore = useStepStore()
const router = useRouter()
function prev(){
    currentStore.current--;
    router.push("/goods/addGoods")
}
function next(){
  console.log(currentStore.goods)
  router.push("/goods/spu")
  currentStore.current++
}
function onCreateFull(){
  return {fullPrice:0,reducePrice:0}
}
function onCreateLadder(){
  return {count:0,discount:0}
}
function handleType(value:string){
  currentStore.goods.promotionType = value
  console.log(currentStore.goods.promotionType);
}
</script>

<template>
  <n-steps :current="currentStore.current as number" :status="currentStatus">
    <n-step title="添加商品信息"/>
    <n-step title="添加商品促销"/>
    <n-step title="添加商品属性spu"/>
  </n-steps>
  <n-card style="max-width: 700px;margin-left: 20px">
    <n-form
        :model="currentStore.goods"
        label-placement="left"
        label-width="auto"
    >
      <n-form-item label="赠送积分">
        <n-input v-model:value="currentStore.goods.giftPoint" />
      </n-form-item>
      <n-form-item label="赠送成长值">
        <n-input v-model:value="currentStore.goods.giftGrowth" />
      </n-form-item>
      <n-form-item label="积分购买限制">
        <n-input v-model:value="currentStore.goods.promotionPerLimit" />
      </n-form-item>
      <n-form-item label="是否新品">
        <n-radio-group v-model:value="currentStore.goods.isNew">
          是: <n-radio :value="1"></n-radio>
          否: <n-radio :value="0"></n-radio>
        </n-radio-group>
      </n-form-item>
      <n-form-item label="是否推荐">
        <n-radio-group v-model:value="currentStore.goods.isRecommend">
          是: <n-radio :value="1"></n-radio>
          否: <n-radio :value="0"></n-radio>
        </n-radio-group>
      </n-form-item>
      <n-form-item label="选择优惠方式">
        <n-card title="优惠方式" style="margin-bottom: 16px">
          <n-tabs type="line" animated @update-value="handleType">
            <n-tab-pane name="0" tab="无优惠">
            </n-tab-pane>
            <n-tab-pane name="1" tab="促销优惠" v-model:value="currentStore.goods.promotionType">
              开始时间：<n-date-picker
                  v-model:formatted-value="currentStore.goods.promotionStartTime"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  type="datetime"
                  clearable
              />
              结束时间：<n-date-picker
                  v-model:formatted-value="currentStore.goods.promotionEndTime"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  type="datetime"
                  clearable
              />
              促销价格：<n-input v-model:value="currentStore.goods.promotionPrice"/>
            </n-tab-pane>
            <n-tab-pane name="2" tab="会员价格">
              <n-form-item v-for="level in currentStore.goods.memberPrices" :key="level.memberLevelId">
                  {{level.memberLevelName}}: <n-input v-model:value="level.memberPrice" style="width: 350px"/>
              </n-form-item>
            </n-tab-pane>
            <n-tab-pane name="3" tab="满减价格">
              <n-dynamic-input
                  v-model:value="currentStore.goods.goodsFullReductions"
                  placeholder="请输入"
                  :on-create="onCreateFull"
              >
                <template #default="{ value }">
                    <n-input-number
                        v-model:value="value.fullPrice"
                    />
                  <n-input-number
                      v-model:value="value.reducePrice"
                  />
                </template>
              </n-dynamic-input>
            </n-tab-pane>
            <n-tab-pane name="4" tab="阶梯价格">
              <n-dynamic-input
                  v-model:value="currentStore.goods.ladders"
                  placeholder="请输入"
                  :on-create="onCreateLadder"
              >
                <template #default="{ value }">
                  <n-input-number
                      v-model:value="value.count"
                  />
                  <n-input-number
                      v-model:value="value.discount"
                  />
                </template>
              </n-dynamic-input>

            </n-tab-pane>
          </n-tabs>
        </n-card>
      </n-form-item>
      <n-button-group>
        <n-button @click="prev" :disabled="currentStore.current===1">上一步</n-button>
        <n-button @click="next" >下一步</n-button>
      </n-button-group>
    </n-form>
  </n-card>
</template>

<style scoped>
.n-data-picker{
  display: inline;
}
</style>
