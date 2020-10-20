import { Module } from 'vuex';

import { PropsPayload } from '../types';
import { login } from '@/api/login';
import { getToken, setToken, removeToken } from '@/utils/auth';

const TOKEN_PREFIX = 'Bearer ';

// TODO 优化 type

const user: Module<any, any> = {
  state: {
    username: '',
    token: getToken(),
    name: '',
    roles: [],
  },
  mutations: {
    SET_STATE_PROPS(state, payload: PropsPayload<string>) {
      state[payload.prop] = payload.payload;
    },
    SET_TOKEN(state, token) {
      state.token = token;
      setToken(token);
    },
  },
  actions: {
    async login({ commit }, loginRequest) {
      const { data: token } = await login(loginRequest);
      commit('SET_TOKEN', `${TOKEN_PREFIX}${token}`);
    },
  },
};

export default user;
