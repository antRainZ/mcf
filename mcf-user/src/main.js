import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from './utils/axiosUtil'
import './assets/css/base.css'
Vue.use(ElementUI)
Vue.prototype.$axios = axios
Vue.config.productionTip = false
import Chat from 'jwchat'

Vue.use(Chat)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

