import Vue from 'vue';
import VueRouter from 'vue-router';
import Login from '../components/Login.vue';
import Main from '../components/Main.vue';
import Details from '../components/Details.vue';
import Clorinde from '../components/Clorinde.vue';
Vue.use(VueRouter);

const routes = [{
    path: '/',
    name: 'Login',
    component: Login
},
{
    path: '/Main',
    name: 'Main',
    component: Main
},
{
    path: '/Clorinde',
    name: 'Clorinde',
    component: Clorinde
},
{
    path: '/Details',
    name: 'Details',
    component: Details
}
]

const router = new VueRouter({
routes
})

// 全局前置路由守卫--初始化时被调用，每次路由切换之前被调用
router.beforeEach((to, from, next) => {
if (to.name != "Login") {
    let token = localStorage.getItem("token")
    if (token == null || token == "") {
        next({
            name: 'Login'
        })
    }
}
return next()
})

export default router