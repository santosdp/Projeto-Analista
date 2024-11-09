import { RouteRecordRaw } from 'vue-router';
import LoginPage from 'src/pages/LoginPage.vue';
import ErrorNotFound from 'src/pages/ErrorNotFound.vue';
const routes: RouteRecordRaw[] = [
  {
    path: '/',
    name: 'home',
    component: LoginPage,
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: ErrorNotFound,
  },
];

export default routes;
