// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import axios from 'axios'
import Vant from 'vant';
import 'vant/lib/vant-css/index.css';
import App from './App'
import router from './router'

Vue.config.productionTip = false
Vue.use(Vant)

// 加载axios并加载默认host
Vue.prototype.$axios = axios.create({
  baseURL: '/api'
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
})
