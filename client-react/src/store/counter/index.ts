import { CounterState, CounterActionTypes, INCREMENT, DECREMENT } from "./types";


const initialState: CounterState = {
  num: 0,
}

export default function counter(state = initialState, action: CounterActionTypes): CounterState {
  switch (action.type) {
    case INCREMENT:
      return {
        num: state.num + action.num,
      }
    case DECREMENT:
      return {
        num: state.num - action.num,
      }
    default:
      return state;
  }
}
