import { NowRequest, NowResponse } from '@vercel/node'
import Mock, { Random } from "mockjs";

import { PageResponse } from "../type";
import { Role } from '../roles'

export class User {
  id: number;
  username: string;
  nickname: string;
  enabled: boolean;
  cellphone: string;
  email: string;
  roles: any[]; // TODO role
  constructor() {
    const role = new Role();
    this.id = Random.natural();
    this.username = Random.first();
    this.nickname = Random.cfirst();
    this.enabled = Random.boolean();
    this.cellphone = Random.integer(13000000000, 19999999999);
    this.email = Random.email();
    this.roles = [role];
  }
}

export default (request: NowRequest, response: NowResponse) => {
  // const page = request.query.page;
  const size = Number(request.query.size) || 10;
  const userList: User[] = []
  for (let i = 0; i < size; i++) {
    userList.push(new User());
  }
  const total = Random.integer(10)
  const pages = Math.ceil(total / size);
  const pageData: PageResponse<User> = {
    content: userList,
    totalElements: total,
    totalPages: pages,
  }
  response.status(200).json(pageData);
}
