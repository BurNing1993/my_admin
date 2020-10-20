import { createRouter, createWebHistory } from 'vue-router';
import routes from './routes';
import { getToken } from '@/utils/auth';

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
      // TODO getUserInfo
    } else {
      return false;
    }
  }
});
