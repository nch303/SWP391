// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";
import { getStorage } from "firebase/storage";

// just change config below to start
const firebaseConfig = {
  apiKey: "AIzaSyBARFc4DEtKumMYVa_qdaQNUO55ya7l33g",
  authDomain: "swp291-3cac5.firebaseapp.com",
  projectId: "swp291-3cac5",
  storageBucket: "swp291-3cac5.appspot.com",
  messagingSenderId: "439878282831",
  appId: "1:439878282831:web:35253f7cee449c1749043a",
  measurementId: "G-JNJHJ5PT0G"
};
// Initialize Firebase
export const app = initializeApp(firebaseConfig);
export const auth = getAuth();
export const storage = getStorage();
export const db = getFirestore();
