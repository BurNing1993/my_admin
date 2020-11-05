import { AxiosPromise } from 'axios';
import request from '../utils/request';
import { BasePageParams, PageResponse, Props } from '../utils/types';

export interface User {
  id?: number;
  username: string;
  nickname: string;
  enabled: boolean;
  cellphone: string;
  email: string;
  roles: any[]; // TODO role
}

export function getUserPage(params: Props & BasePageParams): AxiosPromise<PageResponse<User>> {
  return request({
    url: '/user/page',
    method: 'GET',
    params,
  });
}

export function addUser(data: object) {
  return request({
    url: '/user',
    method: 'POST',
    data,
  });
}
