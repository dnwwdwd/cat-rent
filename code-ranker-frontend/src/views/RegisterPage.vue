<template>

  <div style="height: 60px">
  </div>
  <div style="display: flex; align-items: center; margin-bottom: 30px;;justify-content: center">
    <span style="margin-left: 10px; font-size: 30px">江苏牧职宠物管理系统</span>
  </div>

  <div style="display: flex; justify-content: center;">
    <a-tabs v-model:activeKey="activeKey">
      <a-tab-pane key="register-tab" tab="账号密码注册"></a-tab-pane>
    </a-tabs>
  </div>

  <a-form
      :model="formState"
      name="normal_register"
      class="register-form"
      @finish="onFinish"
      @finishFailed="onFinishFailed"
  >
    <a-form-item
        name="nickname"
        :rules="[{ required: true, message: '请输入账号' }]"
    >
      <a-input v-model:value="formState.nickname" placeholder="请输入昵称">
        <template #prefix>
          <UserOutlined class="site-form-item-icon" />
        </template>
      </a-input>
    </a-form-item>
    <a-form-item
        name="username"
        :rules="[{ required: true, message: '请输入账号' }]"
    >
      <a-input v-model:value="formState.username" placeholder="请输入账号">
        <template #prefix>
          <UserOutlined class="site-form-item-icon" />
        </template>
      </a-input>
    </a-form-item>

    <a-form-item
        name="password"
        :rules="[{ required: true, message: '请输入密码' }]"
    >
      <a-input-password v-model:value="formState.password" placeholder="请输入密码">
        <template #prefix>
          <LockOutlined class="site-form-item-icon" />
        </template>
      </a-input-password>
    </a-form-item>

    <a-form-item
        name="checkPassword"
        :rules="[{ required: true, message: '请输入密码' }]"
    >
      <a-input-password v-model:value="formState.checkPassword" placeholder="请重新输入密码">
        <template #prefix>
          <LockOutlined class="site-form-item-icon" />
        </template>
      </a-input-password>
    </a-form-item>

    <a-form-item style="text-align: left">
      <router-link to="/user/login">已有账号？去登录</router-link>
    </a-form-item>

    <a-form-item>
      <a-button type="primary" html-type="submit" class="register-form-button" style="width: 290px; height: 35px">
        注册
      </a-button>
    </a-form-item>
  </a-form>
</template>
<script lang="js" setup>
import {ref} from 'vue';
import {LockOutlined, UserOutlined} from '@ant-design/icons-vue';
import {message} from "ant-design-vue";
import {useRouter} from "vue-router";
import myAxios from "../plugins/myAxios.js";

const router = useRouter();

const formState = ref({
  username: '',
  password: '',
  checkPassword: '',
  nickname: '',
});

const activeKey = ref('register-tab');

const onFinish = async (values) => {
  const res = await myAxios.post('/user/register', {
    username: values.username,
    password: values.password,
    checkPassword: values.checkPassword,
    nickname: values.nickname,
  });
  if (res.code === 0) {
    message.success('注册成功！');
    router.push({
      path: '/login',
    });
  } else {
    message.error('注册失败' + `${res.message ? `, ${res.message}` : ''}`);
    registerFailed();
  }
};

function registerFailed() {
  // 在注册失败后重置输入框的值为空
  formState.username = '';
  formState.password = '';
  formState.checkPassword = '';
  formState.nickname = '';
}

const onFinishFailed = (errorInfo) => {
  console.log('登录失败');
};

</script>
<style scoped>
#components-form-demo-normal-register .register-form {
  max-width: 300px;
}
#components-form-demo-normal-register .register-form-forgot {
  float: right;
}
#components-form-demo-normal-register .register-form-button {
  width: 100%;
}
.register-form {
  height: 400px;
  width: 350px;
  margin: 0px auto;
}
</style>
