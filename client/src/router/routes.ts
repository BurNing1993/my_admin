import { RouteRecordRaw } from 'vue-router';
import Layout from '../layout/index.vue';

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    children: [
      {
        path: '',
        name: 'Home',
        component: () => import('@/views/home/index.vue'),
      },
      {
        path: 'user',
        name: 'User',
        component: () => import('@/views/user/index.vue'),
      },
      {
        path: 'role',
        name: 'Role',
        component: () => import('@/views/role/index.vue'),
      },
    ],
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/login/index.vue'),
  },
  // 404
  {
    path: '/:pathMatch(.*)*',
    name: 'not-found',
    component: () => import('@/components/Exception/NotFound.vue'),
  },
];

export default routes;
