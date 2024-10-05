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
import PondInfo from "../page/pond/pond-info";
import Profile from "../page/profile";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <Layout />,
    children: [
      { path: "/", element: <Home /> },
      { path: "/profile", element: <Profile /> },
      { path: "/login", element: <Login /> },
      { path: "/register", element: <Resgiter /> },
      { path: "/managerKoi", element: <ManagerKoi /> },
      { path: "/addKoi", element: <AddingKoi /> },
      { path: "/koi-info/:id", element: <PondInfo /> },
      { path: "/managerPond", element: <ManagerPond /> },
      { path: "/addPond", element: <AddingPond /> },
      { path: "/pond-info/:id", element: <PondInfo /> },
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
