import {createRouter, createWebHistory} from 'vue-router'
import routes from "@/router/routes";

const history = createWebHistory(import.meta.env.BASE_URL)
const router = createRouter({
  history: history,
  routes: routes
})

export default router
