import { create } from "zustand";
const useAuthStore = create((set) => {
  const storedUser = localStorage.getItem("user");
  if (storedUser) {
    const user = JSON.parse(storedUser);
    set({ user });
  }
  return {
    // declare state here
    user: null,
    // declare functions here
    login: (userData) => {
      set({ user: userData });
      localStorage.setItem("user", JSON.stringify(userData));
    },
    logout: () => {
      set({ user: null });
      localStorage.removeItem("user");
    },
  };
});

export { useAuthStore };
