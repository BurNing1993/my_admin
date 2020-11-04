export interface CounterState {
  num: number;
}

export const INCREMENT = 'increment';
export const DECREMENT = 'decrement';

export interface IncrementAction {
  type: typeof INCREMENT;
  num: number
}

export interface DecrementAction {
  type: typeof DECREMENT;
  num: number
}

export type CounterActionTypes = IncrementAction | DecrementAction
