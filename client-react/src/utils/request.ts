import axios from 'axios';
import { getToken } from "./auth";

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8';
const AUTH_HEADER_KEY = 'Authorization';

const axiosInstance = axios.create({
  baseURL: '/api',
  // timeout: 10000,
});

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

function handleError(status: number, errorMessage: unknown) {
  let errMessage = 'Ops,出错了！';
  if (typeof errorMessage === 'string') {
    errMessage = errorMessage;
  }
  switch (status) {
    // 401 Unauthorized
    case 401:
      break;
    //  Forbidden
    case 403:
      errMessage = '无权限！';
      break;

    default:
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
