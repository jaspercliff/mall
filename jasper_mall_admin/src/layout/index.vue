<script setup lang="ts">

import Sidebar from "@/layout/sidebar.vue";
import Header from "@/layout/header.vue";
import Content from "@/layout/content.vue";
import Footer from "@/layout/footer.vue";
</script>

<template>
  <n-layout has-sider>
      <sidebar/>
      <n-layout>
        <Header/>
        <content/>
        <Footer/>
      </n-layout>
  </n-layout>
</template>

<style scoped>


</style>
