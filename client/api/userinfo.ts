import { NowRequest, NowResponse } from '@vercel/node'
import { Random } from "mockjs";
import { Role } from './roles'

export interface UserInfo {
  username: string;
  nickname: string;
  email: string;
  roles: string[];
  enabled: boolean;
}

export default (request: NowRequest, response: NowResponse) => {
  const role = new Role();
  const user: UserInfo = {
    username: Random.first(),
    nickname: Random.cname(),
    email: Random.email(),
    roles: [role.roleName],
    enabled: true,
  }
  response.status(200).json(user);
}
