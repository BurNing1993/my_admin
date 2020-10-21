import axios from 'axios';
import { getToken } from '@/utils/auth';

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8';

const axiosInstance = axios.create({
  baseURL: '/api',
  // timeout: 10000,
});

const AUTH_HEADER_KEY = 'Authorization';

// request拦截器
axiosInstance.interceptors.request.use(config => {
  const token = getToken();
  if (token) {
    config.headers[AUTH_HEADER_KEY] = token;
  }
  return config;
}, error => {
  console.log(error);
  return error;
});

// 响应拦截器
axiosInstance.interceptors.response.use(res => {
  return res;
}, err => {
  console.error(err);
  throw new Error(err);
});

export default axiosInstance;
