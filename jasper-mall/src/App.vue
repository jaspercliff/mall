<script setup lang="ts">
import Footer from "@/views/container/footer.vue";
import Global from "@/views/global.vue";
</script>
<template>
  <div >
  <n-message-provider>
    <n-dialog-provider>
    <Global/>
    <Footer/>
    </n-dialog-provider>
  </n-message-provider>
  </div>
</template>
