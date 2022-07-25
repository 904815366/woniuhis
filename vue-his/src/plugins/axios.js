"use strict";

import Vue from 'vue';
import axios from "axios";

// Full config:  https://github.com/axios/axios#request-config
// axios.defaults.baseURL = process.env.baseURL || process.env.apiUrl || '';
// axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

let config = {
  // baseURL: process.env.baseURL || process.env.apiUrl || ""
  // timeout: 60 * 1000, // Timeout
  // withCredentials: true, // Check cross-site Access-Control
};

const _axios = axios.create(config);

_axios.interceptors.request.use(
  config => {
    if (localStorage.getItem('strToken')) {
      config.headers.strToken = localStorage.getItem('strToken')
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
);

// Add a response interceptor
_axios.interceptors.response.use(
  response => {
    console.log(response)
    return response
  },() => {
      // 获取错误状态码，token失效
      // 清除token
      localStorage.removeItem('strToken')
      // 重新跳转到login页面
      router.push('/login')
  }
);

Plugin.install = function(Vue, options) {
  Vue.axios = _axios;
  window.axios = _axios;
  Object.defineProperties(Vue.prototype, {
    axios: {
      get() {
        return _axios;
      }
    },
    $axios: {
      get() {
        return _axios;
      }
    },
  });
};

Vue.use(Plugin)

export default Plugin;
