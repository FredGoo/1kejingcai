import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import Checkout from '@/components/Checkout'
import OrderInfo from '@/components/OrderInfo'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    }, {
      path: '/checkout',
      name: 'Checkout',
      component: Checkout
    }, {
      path: '/orderInfo',
      name: 'OrderInfo',
      component: OrderInfo
    }
  ]
})
