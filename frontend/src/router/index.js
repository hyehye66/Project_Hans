import { createRouter, createWebHistory } from 'vue-router'


const routes = [
  {
    path: '/',            
    name: 'Home',
    component:() => import('@/views/home/HomeView.vue'),
  },
  {
    path:'/mypage',
    name: 'MyPageView',
    component :() => import('@/views/account/MyPageView.vue'),
  },
  {
    path:'/mypage/change',
    name: 'MyPageUpdateView',
    component :() => import('@/views/account/MyPageUpdateView.vue'),
  },
  {
    path:'/signup',
    name: 'SignUpView',
    component :() => import('@/views/account/SignUpView.vue'),
  },
  {
    path:'/login',
    name: 'LoginView',
    component :() => import('@/views/account/LoginView.vue'),
  },
  {
    path : '/communication',
    name : 'CommunicationView',
    component :() => import('@/views/communication/CommunicationView.vue'),
  },
  {
    path : '/dictation',
    name : 'DictationView',
    component :() => import('@/views/dictation/DictationView.vue'),
  },
  {
    path : '/words',
    name : 'WordsMainView',
    component :() => import('@/views/words/WordsMainView.vue'),
  },
  {
    path : '/words/detail/:mode/:sessionName',
    name : 'WordsDetailView',
    component :() => import('@/views/words/WordsDetailView.vue'),
  },
  {
    path : '/body',
    name : 'BodyMainView',
    component :() => import('@/views/body/BodyMainView.vue'),
  },
  {
    path : '/body/detail',
    name : 'BodyDetailView',
    component :() => import('@/views/body/BodyDetailView.vue'),
  },
  {
    path : '/chat',
    name : 'ChatMainView',
    component :() => import('@/views/chat/ChatMainView.vue'),
  },
  {
    path : '/chat/detail/:mode/:sessionName',
    name : 'ChatDetailView',
    component :() => import('@/views/chat/ChatDetailView.vue'),
    props : true
  },
  {
    path : '/rank',
    name : 'RankingView',
    component :() => import('@/views/rank/RankingView.vue'),
  },
  {
    path : '/body/roomupdatemodal',
    name : 'BodyRoomUpdateModal',
    component :() => import('@/views/modal/components/BodyRoomUpdateModal.vue'),
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
