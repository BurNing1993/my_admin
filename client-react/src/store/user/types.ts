export interface UserState {
  token: string;
  username: string;
  nickname: string;
  roles: string[];
}

export const LOGIN = "login";


export interface LoginAction {
  type: typeof LOGIN,
  token: string
}


export type UserActions = LoginAction
