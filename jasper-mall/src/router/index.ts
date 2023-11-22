import { createRouter, createWebHistory } from 'vue-router'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: ()=>import("@/views/container/home.vue")
    },
    {
      path: '/cart',
      name: 'cart',
      component: ()=>import("@/views/customer/goods/Cart.vue")
    },
    {
      path: '/order',
      name: 'order',
      component: ()=>import("@/views/customer/goods/order.vue")
    },
    {
      path:'/signUp',
      name: 'signUp',
      component:()=>import("@/views/customer/sign/signup.vue")
    },
    {
      path:'/emailcode',
      name: 'emailCode',
      component:()=>import("@/views/customer/sign/emailcode.vue")
    },
    {
      path: '/next',
      name:'next',
      component: ()=>import("@/views/customer/sign/next.vue")
    },
    {
      path: '/signin',
      name: 'signIn',
      component: ()=>import("@/views/customer/sign/signin.vue")
    },
    {
      path: '/goodsDetail',
      name: 'goodsDetail',
      component:()=>import("@/views/customer/goods/goodsDetail.vue")
    },
    {
      path: '/payA',
      name: 'payA',
      component:()=>import("@/views/customer/goods/pay.vue")
    },
    {
      path: '/orderDetail',
      name: 'orderDetail',
      component:()=>import("@/views/customer/goods/orderDetail.vue")
    }
  ]
})

export default router
