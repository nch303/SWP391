import { Navigate, Outlet, createBrowserRouter } from "react-router-dom";
// import Test from "../component/Test";
import Zustand from "../Zustand";
import UseReactQuerry from "../component/UseReactQuerry";
import Layout from "../component/layout";
import Home from "../page/home";
import Login from "../page/login";
import ManagerKoi from "../page/koifish/manager-koi";
import Register from "../page/register";


// const ProtectedRouteAuth = ({ children }) => {
//   const user = useSelector(selectUser);
//   if (!user) {
//     alertFail("You need to login first!!");
//     return <Navigate to="/login" replace />;
//   }
//   return children;
// };

// const ProtectedRouteCreator = ({ children }) => {
//   const user = useSelector(selectUser);
//   console.log(user);
//   if (user?.role === "AUDIENCE") {
//     alertFail("You do not have permissions to access");
//     return <Navigate to="/go-pro" replace />;
//   }
//   return children;
// };

// const ProtectedADMIN = ({ children }) => {
//   const user = useSelector(selectUser);
//   console.log(user);
//   if (user?.role !== "ADMIN") {
//     if (user?.role !== "MOD") {
//       alertFail("You do not have permissions to access");
//       return <Navigate to="/" replace />;
//     }
//   }
//   return children;
// };

export const router = createBrowserRouter([
  {
    path: '/',
      element: <Layout />,
      children: [
        { path : "/Home", element: <Home /> },
        { path : "/Login", element: <Login /> },
        { path : "/MyKoi", element: <ManagerKoi  /> },
        { path : "/Register", element: <Register/> },
      ],
  },
  {
    path: "/zustand",
    element: <Zustand />,
  },
  {
    path: "/a",
    element: <UseReactQuerry />,
  },
]);
