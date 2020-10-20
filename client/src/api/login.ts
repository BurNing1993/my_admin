import { AxiosPromise } from 'axios';
import request from '../utils/request';

export interface LoginRequest {
  username: string;
  password: string;
}

export function login(data: LoginRequest): AxiosPromise<string> {
  return request({
    url: '/api/auth/login',
    method: 'POST',
    data,
  });
}

export interface UserInfo {
  username: string;
  nickname: string;
  email: string;
  roles: string[];
  enabled: boolean;
}

export function getUserInfo(): AxiosPromise<UserInfo> {
  return request({
    url: '/api/userinfo',
    method: 'GET',
  });
}
