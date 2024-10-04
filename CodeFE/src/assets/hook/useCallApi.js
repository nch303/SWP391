// import { useState } from "react";
// import api from "../../config/axios";

// const useCallApi = () => {
//   const [loading, setLoading] = useState(false);
//   const [error, setError] = useState(null);
//   const [data, setData] = useState(null);

//   const callApi = async (method, endpoint, requestData) => {
//     setLoading(true);
//     try {
//       let response;
//       switch (method) {
//         case "GET":
//           response = await api.get(endpoint);
//           break;
//         case "POST":
//           response = await api.post(endpoint, requestData);
//           break;
//         case "PUT":
//           response = await api.put(endpoint, requestData);
//           break;
//         case "DELETE":
//           response = await api.delete(endpoint);
//           break;
//         default:
//           throw new Error("Unsupported HTTP method");
//       }
//       // var susscessMessage = // duyet mang
//       // alertSuccess(susscessMessage);
//       setData(response.data.data);
//       setError(null);
//     } catch (error) {
//       //var error = // duyet mang
//       // setError(
//       //   error.response
//       //     ? error.response.data
//       //     : "Có lỗi xảy ra khi gửi yêu cầu."
//       // );
//       setError(error.response.data);
//       setData(null);
//     } finally {
//       setLoading(false);
//     }
//   };

//   const resetError = () => {
//     setError(null);
//   };

//   return { loading, error, data, callApi, resetError };
// };


// export default useCallApi;
