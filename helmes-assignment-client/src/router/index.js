import {createRouter, createWebHistory} from 'vue-router'
import DashboardView from "@/views/DashboardView";
import PageNotFoundView from "@/views/PageNotFoundView";


const routes = [
  {
    path: '/dashboard',
    name: 'dashboard',
    component: DashboardView,
  },
  {
    path: "/:catchAll(.*)",
    name: 'page-not-found',
    component: PageNotFoundView
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
