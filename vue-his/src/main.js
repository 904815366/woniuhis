import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import qs from 'qs'
import './filters/index'  //加载自定义过滤器
import VueBus from 'vue-bus';
Vue.use(ElementUI)
Vue.use(VueBus);

Vue.config.productionTip = false

Vue.prototype.$qs= qs;
// Vue.prototype.$axios=axios

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
