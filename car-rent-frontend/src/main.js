import {createApp} from 'vue';
import Antd from 'ant-design-vue';
import App from './App.vue';
import 'ant-design-vue/dist/reset.css';
import routes from "./route/index.js";
import * as VueRouter from 'vue-router';

const app = createApp(App);

const router = VueRouter.createRouter({
    // 4. 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
    history: VueRouter.createWebHistory(),
    routes: routes, // `routes: routes` 的缩写
});

app.use(Antd).use(router).mount('#app');