
import { createRouter, createWebHistory } from 'vue-router'
import Reglister from "../views/Reglister.vue"
import chat from "../views/chat.vue"
import privatemsg from "../views/privatemsg.vue"



const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'LoginView',
    //   component: LoginView
      component: () => import('../views/LoginView.vue'),

    },
    {
      path: '/chat',
      name: 'chat',
      component: chat
    },
    {
      path: '/reglister', 
      name: 'Reglister',
      component: Reglister,
    },
    {
      path: '/:name',
      name: 'privatemsg',
      component:privatemsg,
      props: true,
    }
  ]
})


export default router
