import Index from '../views/portal/Index.vue'
import AboutUs from '../views/portal/AboutUs.vue'
import Detail from '../views/portal/Detail.vue'
import Annoncement from '../views/portal/Annocement.vue'
import Introduce from '../views/portal/Introduce.vue'
import LearnMore from '../views/portal/LearnMore.vue'

export default [
  {
    path: '/',
    // redirect: '/index'
    redirect: '/index/about-us'
  },
  {
    path: '/index',
    component: Index,
    redirect: '/index/about-us',
    children: [
      {
        path: '/index/about-us',
        component: AboutUs
      },
      {
        path: '/index/about-us/detail',
        component: Detail
      },
      {
        // name: '/index/about-us/introduce',
        path: '/index/about-us/introduce',
        component: Introduce
      },
      {
        path: '/index/annoncement',
        component: Annoncement
      },
      {
        path: '/index/learn-more',
        component: LearnMore
      }
    ]
  }
]
