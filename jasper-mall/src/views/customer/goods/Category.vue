<script setup lang="ts">
import {Star24Regular }from "@vicons/fluent"
import Carousel from "@/views/container/Carousel.vue";
import { ref, onMounted, computed } from 'vue';
import {getOneCategory, getTwoCategory} from '@/api/category'
import Signine from "@/views/customer/user/signine.vue";
import {getSession} from "@/utils/storage";
import Signined from "@/views/customer/user/signined.vue";
interface category{
  id:string,
  name:string
}
const oneCat = ref<category[]>([]);
const twoCat = ref<category[]>([])
const showModel = ref(false);
onMounted(() => {
  getOneCategory().then(res => {
    oneCat.value = res.data;
  });
});
async function showCard(id:string){
  showModel.value= !showModel.value
  const resp = await getTwoCategory(id);
  twoCat.value = resp.data
}
const username = getSession('username')
const isSign = (username === null ? false : true)
</script>
<template>
  <div class="container">
    <div >
      <n-card :bordered="false" style="height: 60vh;text-align: center">
        <n-list hoverable>
          <n-list-item v-for="item in oneCat" :key="item.id">
            {{item.name}}
          </n-list-item>
        </n-list>
      </n-card>
    </div>
    <Carousel ref="carousel" />
    <div>
      <n-card :bordered="false" style="height: 60vh">
        <signine v-if="!isSign"/>
        <signined v-if="isSign"/>
      </n-card>
    </div>
  </div>
</template>
<style scoped>
.container {
  display: grid;
  grid-template-columns: 22vw 5fr 22vw;
}

li {
  list-style: none;
}
.children{
  height: 60vh;
}

</style>
