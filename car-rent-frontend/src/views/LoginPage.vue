<template>
  <div style="height: 60px">
  </div>
  <div style="display: flex; align-items: center; margin-bottom: 30px;;justify-content: center">
    <span style="margin-left: 10px; font-size: 30px">基于Java的汽车租赁平台</span>
  </div>

  <div style="display: flex; justify-content: center;">
    <a-tabs v-model:activeKey="activeKey">
      <a-tab-pane key="login-tab" tab="账号密码登录"></a-tab-pane>
    </a-tabs>
  </div>

  <a-form
      :model="formState"
      name="normal_login"
      class="login-form"
      @finish="onFinish"
      @finishFailed="onFinishFailed"
  >
    <a-form-item
        name="userAccount"
        :rules="[{ required: true, message: '请输入账号' }]"
    >
      <a-input v-model:value="formState.userAccount" placeholder="请输入账号">
        <template #prefix>
          <UserOutlined class="site-form-item-icon" />
        </template>
      </a-input>
    </a-form-item>

    <a-form-item
        name="userPassword"
        :rules="[{ required: true, message: '请输入密码' }]"
    >
      <a-input-password v-model:value="formState.userPassword" placeholder="请输入密码">
        <template #prefix>
          <LockOutlined class="site-form-item-icon" />
        </template>
      </a-input-password>
    </a-form-item>

    <a-form-item style="text-align: left">
      <router-link to="/user/register">没账号？去注册</router-link>
    </a-form-item>

    <a-form-item>
      <a-button type="primary" html-type="submit" class="login-form-button" style="width: 290px; height: 35px">
        登录
      </a-button>
    </a-form-item>
  </a-form>

</template>
<script lang="ts" setup>
import {ref} from 'vue';
import {LockOutlined, UserOutlined} from '@ant-design/icons-vue';
import {message} from "ant-design-vue";
import {useRouter} from "vue-router";
import myAxios from "../plugins/myAxios.js";

const router = useRouter();
interface FormState {
  userAccount: string;
  userPassword: string;
}
const formState = ref({
  userAccount: '',
  userPassword: '',
});

const activeKey = ref('login-tab');

// 登录失败将值清空
function loginFailed() {
  // 在登录失败后重置 userAccount 和 userPassword 的值为空
  formState.value.userAccount = '';
  formState.value.userPassword = '';
}

const onFinish = async (values: any) => {
  console.log(values)
  const res : any = await myAxios.post('/user/login', {
    userAccount: values.userAccount,
    userPassword: values.userPassword,
  });
  if (res.code === 0) {
    message.success('登录成功！');
    router.push({
      path: '/'
    });
  } else {
    message.error('登录失败' + `${res.message ? `, ${res.message}` : ''}`);
    loginFailed();
  }
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};

</script>
<style scoped>
#components-form-demo-normal-login .login-form {
  max-width: 300px;
}
#components-form-demo-normal-login .login-form-forgot {
  float: right;
}
#components-form-demo-normal-login .login-form-button {
  width: 100%;
}
.login-form {
  height: 400px;
  width: 350px;
  margin: 0px auto;
}
</style>
