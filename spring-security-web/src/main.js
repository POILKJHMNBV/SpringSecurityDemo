import Vue from 'vue'
import App from './App.vue'
import $ from 'jquery';
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN' 
import { Message } from 'element-ui'

Vue.use(ElementUI, { locale })
Vue.config.productionTip = false

let baseUrl = "http://localhost:8082/";
Vue.prototype.$post = function(url, data, async, fun) {
  $.ajax({
      url: baseUrl + url,
      type: 'POST',
      dataType: 'json',
      contentType: "application/json",
      xhrFields: {
          withCredentials: true
      },
      headers: {
          "token": localStorage.getItem("token") === null ? '' : localStorage.getItem("token")
      },
      async: async,
      data: JSON.stringify(data),
      success: function(resp) {
          if (resp.code == 200) {
              fun(resp)
          } else {
            Message({
              message: resp.errorMsg,
              type: 'error',
              duration: 1200
            })
          }
      },
      error: function(e) {
          if (e.status == undefined) {
            Message({
              message: "前端页面错误",
              type: 'error',
              duration: 1200
            })
          } else {
              let status = e.status
              if (status == 401) {
                  router.push({
                      name: 'Login'
                  })
              } else {
                Message({
                  message: e.responseText,
                  type: 'error',
                  duration: 1200
                })
              }
          }
      }
  })
}

Vue.prototype.$get = function (url, data, async, fun) {
  $.ajax({
      url: baseUrl + url,
      type: "GET",
      xhrFields: {
          withCredentials: true
      },
      headers: {
        "token": localStorage.getItem("token") === null ? '' : localStorage.getItem("token")
      },
      async: async,
      data: data,
      success: function(resp) {
          if (resp.code == 200) {
              fun(resp)
          } else {
            Message({
              message: resp.errorMsg,
              type: 'error',
              duration: 1200
            })
          }
      },
      error: function(e) {
          if (e.status == undefined) {
            Message({
              message: "前端页面错误",
              type: 'error',
              duration: 1200
            })
          } else {
              let status = e.status
              if (status == 401) {
                  router.push({
                      name: 'Login'
                  })
              } else {
                Message({
                  message: e.responseText,
                  type: 'error',
                  duration: 1200
                })
              }
          }
      }
  })
}


new Vue({
  router,
  render: h => h(App),
}).$mount('#app');