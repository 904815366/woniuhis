import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/home/Home.vue";
import Login from "../views/login/Login.vue";
import Patient from "../components/doctor/Patient.vue";
import Cpoe from "../components/doctor/Cpoe.vue";
import Emr from "../components/doctor/Emr.vue";
import Notification from "../components/doctor/Notification.vue";
import Consultation from "../components/doctor/Consultation.vue";
import Medical from "../components/doctor/Medical.vue";
import AddUser from '../components/user/AddUser.vue'
import UserInfo from '../components/user/UserInfo.vue'
import UserList from '../components/user/UserList.vue'
import UserTime from '../components/user/UserTime.vue'
import HomeContent from '../components/user/HomeContent.vue'
import DrugList from '../components/drug/Drug.vue'
import DrugEdit from '../components/drug/DrugEdit.vue'
import DrugOut from '../components/drug/DrugOut.vue'
import DrugDetail from '../components/drug/DrugDetail.vue'
import UserTimeThis from '../components/user/UserTimeThis.vue'
import UserTimeNext from '../components/user/UserTimeNext.vue'
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
      { path: "/doctor/cpoe", name: "cpoe", component: Cpoe }, //医嘱管理
      {path:'/charge/enter',name: '/charge/enter',component: () => import('../components/charge/enter.vue')},//入院登记
      {path:'/charge/delivery',name: '/charge/delivery',component: () => import('../components/charge/delivery.vue')},//预交缴费
      { path: "/doctor/consultation", name: "consultation", component: Consultation }, //会诊管理
      { path: "/doctor/patient", name: "patient", component: Patient }, //患者列表
      { path: "/doctor/emr", name: "emr", component: Emr }, //入院记录
      { path:"/doctor/medical",name:"medical",component:Medical},
      {
        path: "/doctor/notification",
        name: "notification",
        component: Notification,
      }, //住院通知单
      {path:'/charge/outSettlement',name: '/charge/outSettlement',component: () => import('../components/charge/outSettlement.vue')},//出院结算
      {path:'/charge/dailysettlement',name: '/charge/dailysettlement',component: () => import('../components/charge/dailysettlement.vue')},//收费日结
      { path: "/user/add", name: "addUser", component: AddUser, children: [] },
      {
        path: "/user/list",
        name: "userList",
        component: UserList,
        children: [],
      },
      {
        path: "/user/time",
        name: "userTime",
        component: UserTime,
        children:[
          {
            path: '/user/time/usertimethis',
            name: 'userTimeThis',
            component: UserTimeThis,
            children:[
            ]
          },
          {
            path: '/user/time/usertimenext',
            name: 'userTimeNext',
            component: UserTimeNext,
            children:[
            ]
          }
        ]
      },
      {
        path: "/user/myself",
        name: "myself",
        component: UserInfo,
        children: [],
      },
      {
        path: "/user/homeContent",
        name: "homeContent",
        component: HomeContent,
        children: [],
      },
      {
        path:'/nurse/register',
        name:'nurse/register',
        component: ()=> import(//不需要先导入，注册时导入，新语法
        '../components/nurse/Register.vue')
      },
      {
        path:'/nurse/nur',//护理记录的路由
        name:'nurse/nur',
        component: ()=> import(//不需要先导入，注册时导入，新语法
        '../components/nurse/Nurserecord.vue')
      },
      {
        path:'/drug/list',
        name:'drugList',
        component:DrugList,
        children:[
          { path: "/drug/drugedit", name: "drugedit", component: DrugEdit }
        ]
      },
      {
        path:'/drug/drugout',
        name:'drugOut',
        component:DrugOut,
        children:[
          { path: "/drug/drugdetail", name: "drugdetail", component: DrugDetail }
        ]
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
