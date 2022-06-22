import Vue from 'vue'
import VueRouter from 'vue-router'
import ErrorPage from '../views/ErrorPage.vue'
import cms from './cms'
import portal from './portal'

Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    {
      path: '/error',
      component: ErrorPage
    },
    ...cms,
    ...portal
  ]
})

router.beforeEach((to, from, next) => {
  if (to.matched.length == 0) {
    return next('/error?404')
  }
  if (to.path === '/cms/login') return next()
  if (to.path.search('/cms/*') != -1) {
    const token = window.sessionStorage.getItem('token')
    if (!token) {
      Vue.prototype.$message.error('尚未登陆账号')
      return next('/cms/login')
    }
  }
  next()
})

export default router
