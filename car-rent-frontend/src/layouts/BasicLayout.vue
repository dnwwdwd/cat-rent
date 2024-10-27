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
          <router-link to="/pet/adopt">宠物领养</router-link>
        </a-menu-item>
        <a-menu-item key="3">
          <router-link to="/pet/forum">宠物论坛</router-link>
        </a-menu-item>
        <a-menu-item key="4">
          <router-link to="/pet/donate">宠物捐赠</router-link>
        </a-menu-item>
        <a-menu-item key="5">
          <router-link to="/activity">热门活动</router-link>
        </a-menu-item>
      </a-menu>

      <a-button style="color: #67C23A; margin-right: 20px" @click="showModal">我要捐赠宠物</a-button>
      <a-modal v-model:open="open" title="捐赠宠物" @ok="handleOk" cancelText="取消" okText="确认捐赠">
        宠物名称：<a-input v-model:value="formModal.petName" class="a-input"/>
        年龄：<a-input v-model:value="formModal.age" class="a-input"/>
        性别：<a-input v-model:value="formModal.sex" class="a-input"/>
        描述：<a-input v-model:value="formModal.description" class="a-input"/>
        图片：<a-input v-model:value="formModal.avatarUrl" class="a-input"/>
        品种：<a-input v-model:value="formModal.variety" class="a-input"/>
        状态：<a-input v-model:value="formModal.state" class="a-input"/>
        <div style="margin-top: 10px; margin-bottom: 10px">
          是否绝育：
          <a-select
              v-model:value="formModal.isSterilized"
              style="width: 120px"
          >
            <a-select-option value="0">未绝育</a-select-option>
            <a-select-option value="1">已绝育</a-select-option>
          </a-select>
          是否接种疫苗：
          <a-select
              v-model:value="formModal.isVaccination"
              style="width: 120px"
          >
            <a-select-option value="0">未接种</a-select-option>
            <a-select-option value="1">已接种</a-select-option>
          </a-select>
        </div>
        宠物类型：
        <a-select
            v-model:value="formModal.type"
            style="width: 120px"
        >
          <a-select-option value="0">猫</a-select-option>
          <a-select-option value="1">狗</a-select-option>
          <a-select-option value="2">仓鼠</a-select-option>
        </a-select>
      </a-modal>
      <a-button style="color: plum; margin-right: 20px" @click="showPetForumModal">发布论坛</a-button>
      <a-modal v-model:open="petForumOpen" title="发布论坛" @ok="handlePetForumOk" cancelText="取消" okText="确认发布">
        论坛名称：<a-input v-model:value="petForumModal.name" class="a-input"/>
        论坛描述：<a-input v-model:value="petForumModal.description" class="a-input"/>
        图片：<a-input v-model:value="petForumModal.imgUrl" class="a-input"/>
      </a-modal>
      <a-dropdown style="margin-left: auto">
        <a-avatar
            shape="circle"
            :src="user.avatarUrl"
        />
        <template #overlay>
          <a-menu>
            <a-menu-item>
              <router-link to="/admin/petManagement">后台管理</router-link>
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

    <a-layout-content style="padding: 0 50px; height: 600px; background-image: url('../assets/bg.png'); !important;">
      <router-view />
    </a-layout-content>

    <a-layout-footer style="text-align: center">
      Designed by 叶欣然
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

const user = ref({});

onMounted(async () => {
  if (!route.path.includes('/user/login') && !route.path.includes('/user/register')) {
    const res = await getCurrentUser();
    if (res) {
      user.value = res;
    }
  }
});


watchEffect(() => {
  // 根据当前路由的 path 来设置选中的菜单项
  switch (route.path) {
    case '/':
      selectedKeys.value = ['1'];
      break;
    case '/pet/adopt':
      selectedKeys.value = ['2'];
      break;
    case '/pet/forum':
      selectedKeys.value = ['3'];
      break;
    case '/pet/donate':
      selectedKeys.value = ['4'];
      break;
    case '/activity':
      selectedKeys.value = ['5'];
      break;
    default:
      // 如果没有匹配到任何菜单项，清空选中项
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
