import { UserState } from "./types";
import { getToken } from "../../utils/auth"
import { LOGIN, UserActions } from "./types";

const initialState: UserState = {
  token: getToken(),
  username: "",
  nickname: "",
  roles: [],
}

export default function userReducer(state = initialState, action: UserActions) {
  switch (action.type) {
    case LOGIN:
      return {
        ...state,
        token: action.token
      }

    default:
      return state;
  }
}
