import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'index',
    component: () => import('@/views/article')
  },
  {
    path: '/article/detail/:id',
    name: 'articleDetails',
    component: () => import('@/views/article/detail')
  },
  {
    path: '/post/detail/:id',
    name: 'postDetail',
    component: () => import('@/views/community/detail')
  },
  {
    path: '/community/:id',
    name: 'community',
    component: () => import('@/views/community')
  },
  {
    path: '/friends',
    name: 'friends',
    component: () => import('@/views/friends')
  },
  {
    path: '/communitylist',
    name: 'communitylist',
    component: () => import('@/views/communitys')
  },
  {
    path: '/perdetails',
    name: 'perdetails',
    component: () => import('@/views/personDetails'),
    children:[
      {
        path:'/',
        name:'myDetails',
        component:()=>import('@/components/personCom/myDetails')
      },
      {
        path:'/mycommunity',
        name:'mycommunity',
        component:()=>import('@/components/personCom/myCommunity/index')
      },
      {
        path:'/mypost',
        name:'mypost',
        component:()=>import('@/components/personCom/myPost/index')
      },
      {
        path:'/myfriend',
        name:'myfriend',
        component:()=>import('@/components/personCom/friendList/index')
      },
      {
        path:'/myComment',
        name:'myComment',
        component:()=>import('@/components/personCom/myComment')
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
