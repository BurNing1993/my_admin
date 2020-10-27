import request from '../utils/request';
import { AxiosPromise } from 'axios';

export interface Role {
  id: number;
  roleName: string;
  roleDesc: string;
}

export function getRoleList(params?: any): AxiosPromise<Role[]> {
  return request({
    url: '/roles',
    method: 'GET',
    params,
  });
}
