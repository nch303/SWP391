// import React, { useEffect } from "react";

// import SockJS from "sockjs-client";
// import Stomp from "stompjs";
// function useRealtime(callback) {
//   // const baseUrl = "http://128.199.178.23:8080";
//   // const baseUrl = "http://68.183.180.21:8080";
//   // const WS_URL = "http://localhost:8080/websocket";

//   const WS_URL = "http://68.183.180.21:8080/websocket";
//   const socket = new SockJS(WS_URL);
//   const stomp = Stomp.over(socket);
//   const accountID = localStorage.getItem("accountId");
//   useEffect(() => {
//     const onConnected = () => {
//       console.log("WebSocket connected");
//       stomp.subscribe(`/topic/chat/${accountID}`, (message) => {
//         console.log(message);
//         callback && callback(message);
//       });

//       stomp.subscribe(`/topic/interaction`, (message) => {
//         console.log(message);
//         callback && callback(message);
//       });

//       stomp.subscribe(`/topic/notification/${accountID}`, (message) => {
//         console.log(message);
//         callback && callback(message);
//       });
//     };
//     stomp.connect({}, onConnected, null);
//   }, []);
//   return <></>;
// }

// export default useRealtime;
