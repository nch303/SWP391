import { useState } from "react";
import { useQuery } from "react-query";
import api from "../../config/axios";

const useCustomQuery = (method, endpoint, requestData = null) => {
  const [isApiCalled, setIsApiCalled] = useState(false);

  // Chỉ gọi API nếu nó chưa được gọi trước đó
  const fetchData = async () => {
    let axiosConfig = { method, url: endpoint };
    if (requestData) {
      if (method === "GET") {
        axiosConfig.params = requestData;
      } else {
        axiosConfig.data = requestData;
      }
    }
    const response = await api(axiosConfig);
    setIsApiCalled(true); // Đặt biến isApiCalled thành true sau khi gọi API
    return response.data;
  };

  // Sử dụng hook useQuery
  return useQuery([endpoint, method], fetchData, {
    enabled: !isApiCalled, // Kích hoạt query chỉ khi API chưa được gọi
  });
};

export default useCustomQuery;
