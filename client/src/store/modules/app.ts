import { Module } from 'vuex';

const app: Module<any, any> = {
  state: () => ({
    collapsed: false,
  }),
  mutations: {
    TOGGLE_COLLAPSE(state) {
      state.collapsed = !state.collapsed;
    },
  },
  actions: {

  },
};
export default app;
