import { LOGIN, LoginAction } from "./types";
import { setToken as saveToken } from "../../utils/auth";

export function setToken(token: string): LoginAction {
  saveToken(token);
  return {
    type: LOGIN,
    token
  }
}
