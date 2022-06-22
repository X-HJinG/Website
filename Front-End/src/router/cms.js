import Login from '../views/cms/Login.vue'
import Home from '../views/cms/Home.vue'
import Welcome from '../views/cms/Welcome.vue'
import Notice from '../views/cms/Notice.vue'
import NoticeManagement from '../views/cms/Notice-Management.vue'
import Record from '../views/cms/Record.vue'
import Security from '../views/cms/Security.vue'
import Info from '../views/cms/Info.vue'
import Register from '../views/cms/Register.vue'
import Management from '../views/cms/Management.vue'
import Output from '../views/cms/Output.vue'

export default [
  {
    path: '/cms/login',
    component: Login
  },
  {
    path: '/cms/home',
    component: Home,
    redirect: '/cms/welcome',
    children: [
      {
        path: '/cms/welcome',
        component: Welcome
      },
      {
        path: '/cms/notice',
        component: Notice
      },
      {
        path: '/cms/notice-management',
        component: NoticeManagement
      },
      {
        path: '/cms/record',
        component: Record
      },
      {
        path: '/cms/security',
        component: Security
      },
      {
        path: '/cms/info',
        component: Info
      },
      {
        path: '/cms/register',
        component: Register
      },
      {
        path: '/cms/management',
        component: Management
      },
      {
        path: '/cms/output',
        component: Output
      }
    ]
  }
]
