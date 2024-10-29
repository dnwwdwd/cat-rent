import Index from '../views/Index.vue';
import CarRent from '../views/Car/CarRent.vue';
import Order from '../views/Order/Order.vue';
import CarRentDetail from '../views/Car/CarRentDetail.vue';
import UserSetting from "../views/User/UserSetting.vue";
import LoginPage from "../views/LoginPage.vue";
import RegisterPage from "../views/RegisterPage.vue";
import CarRentManagement from "../views/Admin/CarRentManagement.vue";
import NewsManagement from "../views/Admin/NewsManagement.vue";
import OrderDetail from "../views/Order/OrderDetail.vue";
import CarCategoryDetail from "../views/CarCategory/CarCategoryDetail.vue";
import News from "../views/News/News.vue";
import NewsDetail from "../views/News/NewsDetail.vue";
import CarCategoryManagement from "../views/Admin/CarCategoryManagement.vue";
import UserManagement from "../views/Admin/UserManagement.vue";
import OrderManagement from "../views/Admin/OrderManagement.vue";

const routes = [
    {path: '/', component: Index, meta: { layout: 'basic' }},
    {path: '/car/category/:id', component: CarCategoryDetail, meta: { layout: 'basic' }},
    {path: '/car/rent', component: CarRent, meta: { layout: 'basic' }},
    {path: '/car/rent/detail/:id', component: CarRentDetail, meta: { layout: 'basic' }},
    {path: '/order', component: Order, meta: { layout: 'basic' }},
    {path: '/order/detail/:id', component: OrderDetail, meta: { layout: 'basic' }},
    {path: '/news', component: News, meta: { layout: 'basic' }},
    {path: '/news/detail/:id', component: NewsDetail, meta: { layout: 'basic' }},
    {path: '/user/setting', component: UserSetting, meta: { layout: 'basic' }},
    {path: '/user/login', component: LoginPage, meta: { layout: 'basic' }},
    {path: '/user/register', component: RegisterPage, meta: { layout: 'basic' }},
    {path: '/admin/carRentManagement', title: '汽车管理', component: CarRentManagement, meta: { layout: 'admin' }},
    {path: '/admin/carCategoryManagement', title: '汽车分类管理', component: CarCategoryManagement, meta: { layout: 'admin' }},
    {path: '/admin/newsManagement', title: '汽车管理', component: NewsManagement, meta: { layout: 'admin' }},
    {path: '/admin/orderManagement', title: '订单管理', component: OrderManagement, meta: { layout: 'admin' }},
    {path: '/admin/userManagement', title: '用户管理', component: UserManagement, meta: { layout: 'admin' }},
]

export default routes;