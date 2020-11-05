import axios from 'axios';
import { message } from 'ant-design-vue';

import { getToken } from './auth';
import router from '@/router';

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
  return Promise.reject(error);
});

function handleError(status: number, errorInfo: unknown) {
  let errMessage = 'Ops,出错了！';
  console.log(errorInfo);
  if (typeof errorInfo === 'string') {
    errMessage = errorInfo;
  }
  switch (status) {
    // 400 Bad Request
    case 400:
      if (errorInfo && typeof errorInfo === 'object') {
        errMessage = Object.values(errorInfo).join();
      }
      message.error(errMessage);
      break;
    // 401 Unauthorized
    case 401:
      message.error(errMessage);
      router.replace('/login');
      break;
    //  Forbidden
    case 403:
      errMessage = '无权限！';
      message.error(errMessage);
      break;

    default:
      message.error(errMessage);
      break;
  }
}

// 响应拦截器
axiosInstance.interceptors.response.use(res => {
  return res;
}, error => {
  // message, config, code, request, response
  const { response: { data, status } } = error;
  console.error(`Status:${status},Message:${JSON.stringify(data)}`);
  handleError(status, data);
  return Promise.reject(error);
});

export default axiosInstance;
