import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import qs from 'qs'
Vue.use(ElementUI)

Vue.config.productionTip = false

Vue.prototype.$qs= qs;
// Vue.prototype.$axios=axios

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
