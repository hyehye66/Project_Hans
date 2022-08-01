import { createRouter, createWebHistory } from 'vue-router'


const routes = [
  {
    path: '/',            
    name: 'Home',
    component:() => import('@/views/home/HomeView.vue'),
  },
  {
    path:'/mypage',
    name: 'MyPage',
    component :() => import('@/views/account/MyPageView.vue'),
  },
  {
    path : '/communication',
    name : 'CommunicationView',
    component :() => import('@/views/communication/CommunicationView.vue'),
  },
  {
    path : '/tab',
    name : 'CommunicationTab',
    component :() => import('@/views/communication/components/CommunicationTab.vue'),
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
