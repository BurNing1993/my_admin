import { Module } from 'vuex';

import { login, getUserInfo } from '@/api/login';
import { getToken, setToken, removeToken } from '@/utils/auth';

const TOKEN_PREFIX = 'Bearer ';

// TODO 优化 type

const user: Module<any, any> = {
  state: () => ({
    username: '',
    token: getToken(),
    name: '',
    roles: [],
  }),
  mutations: {
    SET_TOKEN(state, token) {
      state.token = token;
      setToken(token);
    },
    SET_USERNAME(state, username) {
      state.username = username;
    },
    SET_ROLES(state, roles) {
      state.roles = roles;
    },
    SET_NAME(state, name) {
      state.name = name;
    },
  },
  actions: {
    async login({ commit }, loginRequest) {
      const { data: token } = await login(loginRequest);
      commit('SET_TOKEN', `${TOKEN_PREFIX}${token}`);
    },
    async getUserInfo({ commit }) {
      const { data: { username, roles, nickname } } = await getUserInfo();
      commit('SET_USERNAME', username);
      commit('SET_ROLES', roles);
      commit('SET_NAME', nickname);
      return roles;
    },
  },
};

export default user;
