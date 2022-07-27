
import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/home/Home.vue";
import Login from "../views/login/Login.vue";
import Patient from "../components/doctor/Patient.vue";
import Cpoe from "../components/doctor/Cpoe.vue";
import AddUser from '../components/user/AddUser.vue'
import UserInfo from '../components/user/UserInfo.vue'
import UserList from '../components/user/UserList.vue'
import UserTime from '../components/user/UserTime.vue'
import HomeContent from '../components/user/HomeContent.vue'
import DrugList from '../components/drug/Drug.vue'
Vue.use(VueRouter)


const routes = [
  {
    path: "/",
    redirect: "/login",
  },
  {
    path: "/login",
    name: "login",
    component: Login,
  },

  {
    path: "/home",
    name: "home",
    component: Home,

    children: [
      { path: "/doctor/patient", name: "patient", component: Patient },
      {path:"/doctor/cpoe",name:"cpoe",component:Cpoe},
      {path:'/charge/enter',name: '/charge/enter',component: () => import('../components/charge/enter.vue')},
      {path: '/user/add',
      name: 'addUser',
      component: AddUser,
      children:[]
      },
      {
        path: '/user/list',
        name: 'userList',
        component: UserList,
        children:[]
      },
      {
        path: '/user/time',
        name: 'userTime',
        component: UserTime,
        children:[]
      },
      {
        path: '/user/myself',
        name: 'myself',
        component: UserInfo,
        children:[]
      },
      {
        path: '/user/homeContent',
        name: 'homeContent',
        component: HomeContent,
        children:[]
      },
      {
        path:'/nurse/register',
        name:'nurse/register',
        component: ()=> import(//不需要先导入，注册时导入，新语法
        '../components/nurse/Register.vue')
      },
      {
        path:'/drug/list',
        name:'drugList',
        component:DrugList,
        children:[]
      }
    ],
  },
];

const router = new VueRouter({
  routes,
});

router.beforeEach((to, from, next) => {
  if (to.path === "/login") {
    //如果请求的path为login
    next(); //放行
  } else {
    //否则，路由的path不是请求login
    const token = localStorage.getItem("strToken"); //获取token
    token ? next() : next("/login"); //如果取到了，放行，没有取到转路径login
  }
});

export default router;
