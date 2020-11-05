import { AxiosPromise } from "axios";
import request from "../utils/request";

export interface LoginData {
  username: string;
  password: string;
}

export function login(data: LoginData): AxiosPromise<string> {
  return request({
    url: "/auth/login",
    method: "POST",
    data
  })
}
