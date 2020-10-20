import { createRouter, createWebHistory } from 'vue-router';
import routes from './routes';
import { getToken } from '@/utils/auth';
import store from '@/store';

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;

const allowList = ['/login'];

router.beforeEach(async (to, from) => {
  // 不需要token
  if (allowList.includes(to.path)) {
    return true;
  } else {
    const token = getToken();
    if (token) {
      const roles = store.getters.roles;
      console.log(roles);
      if (roles.length > 0) {
        return true;
      } else {
        try {
          // TODO getUserInfo roles ADMIN
          await store.dispatch('getUserInfo');
          return true;
        } catch (error) {
          console.error(error);
          return false;
        }
      }
    } else {
      router.replace('/login');
      return false;
    }
  }
});
