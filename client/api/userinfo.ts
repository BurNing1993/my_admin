import { NowRequest, NowResponse } from '@vercel/node'
import Mock, { Random } from "mockjs";


export interface UserInfo {
  username: string;
  nickname: string;
  email: string;
  roles: string[];
  enabled: boolean;
}


export default (request: NowRequest, response: NowResponse) => {
  const user: UserInfo = {
    username: Random.first(),
    nickname: Random.cname(),
    email: Random.email(),
    roles: Mock.mock({
      "array|1": [
        "ADMIN",
        "USER",
      ]
    }),
    enabled: true,
  }
  response.status(200).json(user);
}
