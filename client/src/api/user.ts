import { AxiosPromise } from 'axios';
import request from '../utils/request';
import { BasePageParams, PageResponse } from '../utils/types';

export interface UserPageParams {
  [key: string]: any;
}
export interface User {
  id: string;
  username: string;
  nickname: string;
  enabled: string;
  cellphone: string;
  email: string;
  roles: any[]; // TODO role
}

export function getUserPage(params: UserPageParams & BasePageParams): AxiosPromise<PageResponse<User>> {
  return request({
    url: '/user/page',
    method: 'GET',
    params,
  });
}
