import { useState } from "react";
import useCustomQuery from "../assets/hook/useReactQuery";
import { useQuery, useQueryClient } from "react-query";
import { ReactQueryDevtools } from "react-query/devtools";

import api from "../config/axios";
function UseReactQuerry() {
  const [data1, setData1] = useState(null);
  const [data2, setData2] = useState(null);

  // const {
  //   data: responseData1,
  //   isLoading: isLoading1,
  //   error: error1,
  // } = useQuery("GET", "artworks");

  // const {
  //   data: responseData2,
  //   isLoading: isLoading2,
  //   error: error2,
  // } = useCustomQuery("GET", "/artwork-detail/13");

  // if (!isLoading1 && !error1 && responseData1 && !data1) {
  //   setData1(responseData1);
  // }

  // if (!isLoading2 && !error2 && responseData2 && !data2) {
  //   setData2(responseData2);
  // }
  const fetchTodos = () => {
    return api.get("artworks");
  };

  const { data: todo } = useQuery({
    queryKey: ["getAll"],
    queryFn: () => api.get("artworks"),
    select: (data) => {
      // return data.data.data;
      // console.log(data.data.data[0].id);
      return data.data.data[0].id;
    },
    staleTime: 3000, // thoi gian out ra ngoai de du lieu luon moi nhat
  });

  // const { data: details } = useQuery({
  //   queryKey: ["detail"],
  //   queryFn: () => api.get("/artwork-detail/13"),
  //   select: (data) => {
  //     // return da
  //     // console.log(data);
  //     return data.data.data.title + "cc";
  //   },
  //   onSuccess: (data) => {
  //     console.log(data + "ccc");
  //   },
  // });

  // const querryCLine = new useQueryClient();
  // const dataAll = querryCLine.getQueryData("detail");
  // console.log(dataAll);

  // console.log(todo);
  // console.log(details);
  // console.log(data1);
  // console.log(data2);
  return (
    <div>
      {
        <>
          <div>a</div>
          <div>a</div>
          <div>a</div>
          <div>a</div>
          <div>a</div>
          <div>a</div>
          <div>a</div>
          <div>a</div>
          <div>a</div>
          <div>a</div>
          <div>a</div>
          <div>a</div>
          <div>a</div>
          <ReactQueryDevtools initialIsOpen={true} position="bottom-right" />
        </>
      }
    </div>
  );
}

export default UseReactQuerry;
