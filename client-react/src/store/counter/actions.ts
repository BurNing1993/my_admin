import { INCREMENT, DECREMENT, IncrementAction, DecrementAction } from "./types";

export function increment(num: number = 1): IncrementAction {
  return {
    type: INCREMENT,
    num,
  }
}

export function decrement(num: number = 1): DecrementAction {
  return {
    type: DECREMENT,
    num,
  }
}
