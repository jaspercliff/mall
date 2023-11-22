<script setup lang="ts">
import {useDialog, useMessage} from "naive-ui";
window.$message = useMessage()
window.$dialog = useDialog()
</script>

<template>
<router-view></router-view>
</template>

<style scoped>

</style>