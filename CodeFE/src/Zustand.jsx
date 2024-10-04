import { useAuthStore } from "./zustand/useAuthStore";
import { useQuery, useQueryClient } from "react-query";
function Zustand() {
  console.log("alo123");
  const { user, login, logout } = useAuthStore();

  console.log(user);

  const handleLogin = () => {
    const userData = { id: 1, username: "Hong Thinh", age: "21" };
    login(userData);
  };
  const querryCLine = new useQueryClient();
  const dataAll = querryCLine.getQueryData("detail");
  console.log(dataAll);
  return (
    <div>
      {user != null ? (
        <div>
          <p>Welcome, {user.username}!</p>
          <button onClick={() => logout()}>Logout</button>
        </div>
      ) : (
        <button onClick={handleLogin}>Login</button>
      )}
    </div>
  );
}

export default Zustand;
