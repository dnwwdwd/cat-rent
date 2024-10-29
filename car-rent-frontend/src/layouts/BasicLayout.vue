<template>
  <a-layout class="layout" style="min-height: 100vh; display: flex; flex-direction: column;">
    <a-layout-header :style="{ background: '#fff', display: 'flex', alignItems: 'center', marginBottom: '24px' }">
      <img src="../assets/logo.png" alt="Logo" style="height: 40px; margin-right: 20px" />
      <span style="font-size: 15px; color: #1E90FF">基于Java的汽车租赁平台</span>
      <!-- 动态高亮菜单项 -->
      <a-menu
          :selectedKeys="selectedKeys"
          theme="light"
          mode="horizontal"
          :style="{ lineHeight: '64px', flexGrow: 1 }"
      >
        <a-menu-item key="1">
          <router-link to="/">首页</router-link>
        </a-menu-item>
        <a-menu-item key="2">
          <router-link to="/car/rent">租车</router-link>
        </a-menu-item>
        <a-menu-item key="3">
          <router-link to="/order">我的订单</router-link>
        </a-menu-item>
        <a-menu-item key="4">
          <router-link to="/news">资讯</router-link>
        </a-menu-item>
      </a-menu>

      <a-dropdown style="margin-left: auto">
        <a-avatar
            shape="circle"
            :src="user.userAvatar"
        />
        <template #overlay>
          <a-menu>
            <a-menu-item>
              <router-link to="/admin/carRentManagement" v-if="isAdmin">后台管理</router-link>
            </a-menu-item>
            <a-menu-item>
              <router-link to="/user/setting">个人设置</router-link>
            </a-menu-item>
            <a-menu-item>
              <router-link to="/user/login" @click="userLogout">退出登录</router-link>
            </a-menu-item>
          </a-menu>
        </template>
      </a-dropdown>
    </a-layout-header>

    <a-layout-content style="padding: 0 50px; height: 600px;">
      <router-view />
    </a-layout-content>

    <a-layout-footer style="text-align: center">
      Designed by 钱魏巍
    </a-layout-footer>
  </a-layout>
</template>

<script lang="js" setup>
import {onMounted, ref, watchEffect} from 'vue';
import {useRoute} from 'vue-router';
import myAxios from "../plugins/myAxios.js";
import {message} from "ant-design-vue";
import {getCurrentUser} from "../services/user.js";

const selectedKeys = ref([]);

const route = useRoute();
const isAdmin = ref(false);
const user = ref({});

onMounted(async () => {
  if (!route.path.includes('/user/login') && !route.path.includes('/user/register')) {
    const res = await getCurrentUser();
    if (res) {
      user.value = res;
      isAdmin.value = user.value.userRole === "admin";
    }
  }
});


watchEffect(() => {
  switch (route.path) {
    case '/':
      selectedKeys.value = ['1'];
      break;
    case '/car/rent':
      selectedKeys.value = ['2'];
      break;
    case '/order':
      selectedKeys.value = ['3'];
      break;
    case '/news':
      selectedKeys.value = ['4'];
      break;
    default:
      selectedKeys.value = [];
  }
});

const userLogout = async () => {
  const res = await myAxios.post('/user/logout');
  if (res.code === 0) {
    message.success('退出成功');
  } else {
    message.error('退出失败');
  }
};

const open = ref(false);

const petForumOpen = ref(false);

const formModal = ref({
  petName: '',
  age: '',
  sex: '',
  state: '',
  isSterilized: '',
  isVaccination: '',
  description: '',
  type: '',
  avatarUrl: '',
  variety: '',
});

const petForumModal = ref({
  name: '',
  imgUrl: '',
  description: '',
});

const showModal = () => {
  open.value = true;
};

const showPetForumModal = () => {
  petForumOpen.value = true;
};

const handleOk = async () => {
  const res = await myAxios.post('/donate/pet', formModal.value);
  if (res.code === 0) {
    open.value = false;
    window.location.reload();
  }
};

const handlePetForumOk = async () => {
  const res = await myAxios.post('/pet/forum/add', petForumModal.value);
  if (res.code === 0) {
    petForumOpen.value = false;
    window.location.reload();
  }
};

</script>

<style scoped>

</style>
