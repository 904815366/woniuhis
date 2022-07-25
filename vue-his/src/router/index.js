import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/home/Home.vue'
import Login from '../views/login/Login.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect:'/login'
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  
  {
    path: '/home',
    name: 'home',
    component: Home,
    children:[
 
    ]
  },
  
  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to,from,next)=>{
  if(to.path === '/login'){ //如果请求的path为login 
    next(); //放行
  }
  else{ //否则，路由的path不是请求login
    const token = localStorage.getItem('strToken');  //获取token
    token ? next() : next('/login') //如果取到了，放行，没有取到转路径login
  }
})

export default router

