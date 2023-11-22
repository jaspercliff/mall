<script setup lang="ts">
import type {Component} from "vue";
import {h} from 'vue'
import type {RouteRecordRaw} from "vue-router";
import {RouterLink, useRouter} from "vue-router";
import type {MenuOption} from "naive-ui";
import routes from "@/router/routes";
import {useStateStore} from "@/stores/state";
import {renderIcon} from "@/utils/common";
import {Production24Regular} from "@vicons/fluent"

const state = useStateStore()
const router = useRouter()
function mapRoutesToOptions(routes: RouteRecordRaw[], parentPath = ''): MenuOption[] {
  const options: MenuOption[] = [];
  for (let route of routes) {
    const option: MenuOption = {
      key: route.path,
      label: !route.children ? () =>
          h(
              RouterLink,
              {
                to: {
                  name: route.name,
                }
              },
              { default: () => route.name }
          ): route.name,
      show: true,
      icon: renderIcon(route.meta?.icon as Component || Production24Regular),
    };
    if (route.meta && route.meta.hidden) {
      option.show = false;
    }
    if (route.children) {
      option.children = mapRoutesToOptions(route.children, parentPath + route.path);
    }
    options.push(option);
  }
  return options;
}
const options = mapRoutesToOptions(routes)
const defaultExpandKeys = ['/frontPage']
</script>

<template>
  <n-layout-sider
      width="230px"
      bordered
      show-trigger="bar"
      collapse-mode="width"
      :collapsed-width=50
      :native-scrollbar="false"
      :collapsed="state.sidebarCollapsed"
      @update-collapsed="state.toggle"
  >
  <n-menu
      v-model:value="state.activeKey"
      :root-indent=18
      :collapsed-icon-size= 26
      :options="options"
      :icon-size= 26
      :default-expanded-keys="defaultExpandKeys"
  />
  </n-layout-sider>
</template>

<style scoped>
.n-layout-sider{
  min-height: 100vh;
  background-color: #f0f2f5; /* 侧边栏背景颜色 */
  color: #333; /* 文本颜色 */
}
</style>
