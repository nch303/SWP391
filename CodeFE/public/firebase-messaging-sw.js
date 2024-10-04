// // Scripts for firebase and firebase messaging
// importScripts("https://www.gstatic.com/firebasejs/8.2.0/firebase-app.js");
// importScripts("https://www.gstatic.com/firebasejs/8.2.0/firebase-messaging.js");

// // Initialize the Firebase app in the service worker by passing the generated config
// const firebaseConfig = {
//   apiKey: "AIzaSyBY1PxLeYzNdL-mzV9x1cJzPIF66UNBqkg",
//   authDomain: "swp-asp.firebaseapp.com",
//   projectId: "swp-asp",
//   storageBucket: "swp-asp.appspot.com",
//   messagingSenderId: "840654227120",
//   appId: "1:840654227120:web:f86f024ab8f4ad9e755d75",
//   measurementId: "G-X2L1D29BGG",
// };

// firebase.initializeApp(firebaseConfig);

// // Retrieve firebase messaging
// const messaging = firebase.messaging();

// messaging.onBackgroundMessage(function (payload) {
//   console.log("Received background message ", payload);
//   // Customize notification here
//   const notificationTitle = payload.notification.title;
//   const notificationOptions = {
//     body: payload.notification.body,
//   };

//   self.registration.showNotification(notificationTitle, notificationOptions);
// });
