<script setup>
import {computed} from 'vue';
import {useRoute} from 'vue-router';
import BasicLayout from './layouts/BasicLayout.vue';
import AdminBasicLayout from './layouts/AdminBasicLayout.vue';

// 获取当前路由
const route = useRoute();

// 动态计算布局组件
const layoutComponent = computed(() => {
  if (route.meta.layout === 'basic') {
    return BasicLayout;
  }
  return AdminBasicLayout; // 默认布局
});
</script>

<template>
  <!-- 动态渲染布局组件 -->
  <component :is="layoutComponent">
    <router-view />
  </component>
</template>

<style scoped>
</style>
