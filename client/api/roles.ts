import { NowRequest, NowResponse } from '@vercel/node'
import Mock, { Random } from "mockjs";

const roles: Role[] = [
  {
    id: 1,
    roleName: "ADMIN",
    roleDesc: "管理员"
  },
  {
    id: 2,
    roleName: "USER",
    roleDesc: "用户"
  }
]
export class Role {
  id: number;
  roleName: string;
  roleDesc: string;
  constructor() {
    const index = Random.natural(0, 1);
    this.id = roles[index].id;
    this.roleName = roles[index].roleName;
    this.roleDesc = roles[index].roleDesc;
  }
}

export default (request: NowRequest, response: NowResponse) => {
  response.status(200).json(roles)
}
