import Index from '../views/Index.vue';
import PetDetail from "../views/Pet/PetDetail.vue";
import PetAdopt from "../views/Pet/PetAdopt.vue";
import PetAdoptDetail from "../views/Pet/PetAdoptDetail.vue";
import PetForum from "../views/Pet/PetForum.vue";
import PetForumDetail from "../views/Pet/PetForumDetail.vue";
import PetDonate from "../views/Pet/PetDonate.vue";
import PetDonateDetail from "../views/Pet/PetDonateDetail.vue";
import Activity from "../views/Activity/Activity.vue";
import ActivityDetail from "../views/Activity/ActivityDetail.vue";
import UserSetting from "../views/User/UserSetting.vue";
import LoginPage from "../views/LoginPage.vue";
import RegisterPage from "../views/RegisterPage.vue";
import PetManagement from "../views/Admin/PetManagement.vue";
import petAdoptManagement from "../views/Admin/petAdoptManagement.vue";
import PetDonateManagement from "../views/Admin/PetDonateManagement.vue";
import PetForumManagement from "../views/Admin/PetForumManagement.vue";
import ActivityManagement from "../views/Admin/ActivityManagement.vue";
import UserManagement from "../views/Admin/UserManagement.vue";

const routes = [
    {path: '/', component: Index, meta: { layout: 'basic' }},
    {path: '/pet/detail/:id', component: PetDetail, meta: { layout: 'basic' }},
    {path: '/pet/adopt', component: PetAdopt, meta: { layout: 'basic' }},
    {path: '/adopt/:id', component: PetAdoptDetail, meta: { layout: 'basic' }},
    {path: '/pet/:id', component: PetAdoptDetail, meta: { layout: 'basic' }},
    {path: '/pet/forum', component: PetForum, meta: { layout: 'basic' }},
    {path: '/pet/forum/detail/:id', component: PetForumDetail, meta: { layout: 'basic' }},
    {path: '/pet/donate', component: PetDonate, meta: { layout: 'basic' }},
    {path: '/donate/detail/:id', component: PetDonateDetail, meta: { layout: 'basic' }},
    {path: '/activity', component: Activity, meta: { layout: 'basic' }},
    {path: '/activity/detail/:id', component: ActivityDetail, meta: { layout: 'basic' }},
    {path: '/user/setting', component: UserSetting, meta: { layout: 'basic' }},
    {path: '/user/login', component: LoginPage, meta: { layout: 'basic' }},
    {path: '/user/register', component: RegisterPage, meta: { layout: 'basic' }},
    {path: '/admin/petManagement', title: '宠物管理', component: PetManagement, meta: { layout: 'admin' }},
    {path: '/admin/petAdoptManagement', title: '宠物领养管理', component: petAdoptManagement, meta: { layout: 'admin' }},
    {path: '/admin/petDonateManagement', title: '宠物捐赠管理', component: PetDonateManagement, meta: { layout: 'admin' }},
    {path: '/admin/petForumManagement', title: '宠物论坛管理', component: PetForumManagement, meta: { layout: 'admin' }},
    {path: '/admin/activityManagement', title: '热门活动管理', component: ActivityManagement, meta: { layout: 'admin' }},
    {path: '/admin/userManagement', title: '用户管理', component: UserManagement, meta: { layout: 'admin' }},
]

export default routes;