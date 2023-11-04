import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import SvgIcon from 'vue-svgicon'


Vue.use(ElementUI);
Vue.use(SvgIcon, {
  'tagName': 'svg-icon',
  'defaultWidth': '1em',
  'defaultHeight': '1em'
})

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
