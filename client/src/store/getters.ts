import { GetterTree } from 'vuex';

const getters: GetterTree<any, any> = {
  roles: state => state.user.roles,
};

export default getters;
