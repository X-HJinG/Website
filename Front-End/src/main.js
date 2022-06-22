import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
import './assets/iconfont/font.css'
import './assets/js/common.js'
import axios from 'axios'

axios.interceptors.request.use(
  config => {
    let token = window.sessionStorage.getItem('token')
    if (token) {
      config.headers.token = token
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

axios.interceptors.response.use(
  res => {
    if (res.headers.code == 4031) {
      Vue.prototype.$message({
        showClose: true,
        message: '身份过期,请重新登陆',
        type: 'error'
      })
      setTimeout(() => {
        window.sessionStorage.clear()
        router.push('/cms/login')
      }, 2000)
    }
    if (res.headers.code == 4032) {
      Vue.prototype.$message({
        showClose: true,
        message: '权限不足',
        type: 'error'
      })
      // setTimeout(() => {
      //   window.sessionStorage.clear()
      //   router.push('/login')
      // }, 2000)
    }
    return res
  },
  error => {
    if (error.message === 'Network Error') {
      Vue.prototype.$message({
        showClose: true,
        message: 'Network Error',
        type: 'error',
        duration: 1000
      })
    } else if (error.response.status != 200) {
      router.push('/error?' + error.response.status)
    }
    return Promise.reject(error)
  }
)
Vue.prototype.$http = axios
Vue.config.productionTip = false
axios.defaults.baseURL = 'http://119.91.65.141:8888'
window.jQuery = window.$ = require('jquery')
require('velocity-animate/velocity')
require('velocity-animate/velocity.ui')

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
