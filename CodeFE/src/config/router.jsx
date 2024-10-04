import React from "react";
import { Navigate, Outlet, createBrowserRouter, Route } from "react-router-dom";
// import Test from "../component/Test";
import Zustand from "../Zustand";
import UseReactQuerry from "../component/UseReactQuerry";
import Layout from "../component/layout";
import Home from "../page/home";
import Login from "../page/login";
import Resgiter from "../page/register";
import ManagerKoi from "../page/koifish/manager-koi";
import AddingKoi from "../page/koifish/adding-koi";
import ManagerPond from "../page/pond/manager-pond";
import AddingPond from "../page/pond/adding-pond";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <Layout />,
    children: [
      { path: "/Home", element: <Home /> },
      { path: "/Login", element: <Login /> },
      { path: "/Register", element: <Resgiter /> },
      { path: "/ManagerKoi", element: <ManagerKoi /> },
      { path: "/AddKoi", element: <AddingKoi /> },
      { path: "/ManagerPond", element: <ManagerPond /> },
      { path: "/AddPond", element: <AddingPond /> },
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
