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
