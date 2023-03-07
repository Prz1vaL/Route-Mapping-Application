import {createRouter, createWebHistory} from 'vue-router'
import App from "./App.vue";

const routes = [
    {
        path: '/',
        name: 'app',
        component: App,
    }
    // ,{ path: '/add', component: AddRoute },
    // { path: '/list', component: RoutesList },

]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router