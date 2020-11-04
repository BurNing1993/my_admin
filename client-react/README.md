# TsApp

## Redux

### install

```sh
yarn add redux react-redux  @types/react-redux redux-thunk
```

### counterReducer

- types

```ts
// /store/counter/types.ts
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
```

- actions

```ts
// /store/counter/actions.ts
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
```

- reducer

```ts
// /store/counter/index.ts
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
```

### createStore

```ts
// /store/index.ts
import { combineReducers, createStore } from "redux";

import counterReducer from './counter'

const rootReducer = combineReducers({
  counter: counterReducer
});

const store = createStore(rootReducer);

export default store;

export type RootState = ReturnType<typeof rootReducer>
```

### Provider

```tsx
// App.tsx
import React, { FC } from "react";
import { Provider } from "react-redux";
import store from "./store";
import Counter from "./Counter";

const App: FC = () => (
  <Provider store={store}>
    <Counter />
  </Provider>
);

export default App;
```

### CounterComponents

```tsx
// Counter.tsx
import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../store";
import { increment, decrement } from "../store/counter/actions";

const Counter = () => {
  const counter = useSelector((state: RootState) => state.counter.num);
  const dispatch = useDispatch();

  const onIncrement = (num: number) => {
    dispatch(increment(num));
  };
  const onDecrement = () => {
    dispatch(decrement());
  };
  return (
    <>
      <p>Counter : {counter}</p>
      <button onClick={() => onIncrement(2)}>increment</button>
      <button onClick={onDecrement}>decrement</button>
    </>
  );
};
export default Counter;
```

## react-router

- install

```sh
yarn add react-router-dom @types/react-router-dom
```
