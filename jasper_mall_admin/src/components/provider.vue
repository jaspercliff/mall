<script setup lang="ts">
import {darkTheme, useOsTheme} from "naive-ui";
import {computed} from "vue";

const osTheme = useOsTheme()
const theme = computed(
    ()=>(osTheme.value === 'dark' ? darkTheme : null)
)
</script>

<template>
<n-config-provider >

</n-config-provider>
</template>

<style scoped>

</style>
