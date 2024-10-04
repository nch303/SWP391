// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";
import { getStorage } from "firebase/storage";

// just change config below to start
const firebaseConfig = {
  apiKey: "AIzaSyBY1PxLeYzNdL-mzV9x1cJzPIF66UNBqkg",
  authDomain: "swp-asp.firebaseapp.com",
  projectId: "swp-asp",
  storageBucket: "swp-asp.appspot.com",
  messagingSenderId: "840654227120",
  appId: "1:840654227120:web:f86f024ab8f4ad9e755d75",
  measurementId: "G-X2L1D29BGG",
};
// Initialize Firebase
export const app = initializeApp(firebaseConfig);
export const auth = getAuth();
export const storage = getStorage();
export const db = getFirestore();
