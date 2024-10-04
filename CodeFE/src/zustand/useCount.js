import { create } from "zustand";

const useCount = create((set) => ({
  number: 0,
  // declare functions here
  increase: (number) => {
    set(number + 1);
  },
  decrease: (number) => {
    set(number - 1);
  },
}));

export { useCount };
