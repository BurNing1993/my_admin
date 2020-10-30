import { NowRequest, NowResponse } from '@vercel/node'


export interface Role {
  id: number;
  roleName: string;
  roleDesc: string;
}

export default (request: NowRequest, response: NowResponse) => {
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
  response.status(200).json(roles)
}
