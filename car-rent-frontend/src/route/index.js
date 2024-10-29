import Index from '../views/Index.vue';
import CarRent from '../views/Car/CarRent.vue';
import Order from '../views/Order/Order.vue';
import CarRentDetail from '../views/Car/CarRentDetail.vue';
import PetDetail from "../views/Car/PetDetail.vue";
import PetAdopt from "../views/Car/PetAdopt.vue";
import PetAdoptDetail from "../views/Car/PetAdoptDetail.vue";
import PetForum from "../views/News/News.vue";
import PetForumDetail from "../views/News/NewsDetail.vue";
import PetDonate from "../views/Car/PetDonate.vue";
import PetDonateDetail from "../views/Car/CarRentDetail.vue";
import Activity from "../views/CarCategory/CarCategory.vue";
import ActivityDetail from "../views/CarCategory/CarCategoryDetail.vue";
import UserSetting from "../views/User/UserSetting.vue";
import LoginPage from "../views/LoginPage.vue";
import RegisterPage from "../views/RegisterPage.vue";
import PetManagement from "../views/Admin/PetManagement.vue";
import petAdoptManagement from "../views/Admin/petAdoptManagement.vue";
import PetDonateManagement from "../views/Admin/PetDonateManagement.vue";
import PetForumManagement from "../views/Admin/PetForumManagement.vue";
import ActivityManagement from "../views/Admin/ActivityManagement.vue";
import UserManagement from "../views/Admin/UserManagement.vue";
import OrderDetail from "../views/Order/OrderDetail.vue";

const routes = [
    {path: '/', component: Index, meta: { layout: 'basic' }},
    {path: '/car/rent', component: CarRent, meta: { layout: 'basic' }},
    {path: '/car/rent/detail/:id', component: CarRentDetail, meta: { layout: 'basic' }},
    {path: '/order', component: Order, meta: { layout: 'basic' }},
    {path: '/order/detail/:id', component: OrderDetail, meta: { layout: 'basic' }},
    {path: '/user/setting', component: UserSetting, meta: { layout: 'basic' }},
    {path: '/user/login', component: LoginPage, meta: { layout: 'basic' }},
    {path: '/user/register', component: RegisterPage, meta: { layout: 'basic' }},
    // {path: '/admin/petManagement', title: '宠物管理', component: PetManagement, meta: { layout: 'admin' }},
    // {path: '/admin/petAdoptManagement', title: '宠物领养管理', component: petAdoptManagement, meta: { layout: 'admin' }},
    // {path: '/admin/petDonateManagement', title: '宠物捐赠管理', component: PetDonateManagement, meta: { layout: 'admin' }},
    // {path: '/admin/petForumManagement', title: '宠物论坛管理', component: PetForumManagement, meta: { layout: 'admin' }},
    // {path: '/admin/activityManagement', title: '热门活动管理', component: ActivityManagement, meta: { layout: 'admin' }},
    // {path: '/admin/userManagement', title: '用户管理', component: UserManagement, meta: { layout: 'admin' }},
]

export default routes;