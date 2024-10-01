import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./login.css"; // Import the CSS file
import {Background} from '../../component/header/layout.js';

// Sample data simulating backend user data
const sampleData = [
  { username: "admin", password: "adminpass", role: "admin" },
  { username: "user", password: "userpass", role: "user" },
  { username: "shop", password: "shoppass", role: "shop" },
];

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    // ---- Backend communication (commented for now) ----
    /*
    try {
      const response = await fetch("http://your-backend-url.com/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
      });

      const data = await response.json();

      if (response.ok) {
        // Redirect based on the role
        if (data.role === "user") {
          navigate("/user-home");
        } else if (data.role === "admin") {
          navigate("/admin-home");
        } else {
          setError("Unknown role");
        }
      } else {
        setError("Invalid credentials");
      }
    } catch (err) {
      setError("Error connecting to server");
    }
    */
   
    // ---- Simulate login using sampleData for now ----
    const user = sampleData.find(
      (user) => user.username === username && user.password === password
    );

    if (user) {
      // Redirect based on role
      if (user.role === "admin") {
        navigate("/admin-home");
      } else if (user.role === "user") {
        navigate("/home");
      } else if (user.role === "shop") {
        navigate("/shop-home");  
      } else {
        setError("Unknown role");
      }
    } else {
      setError("Invalid credentials");
    }
  };

  return (
    
    <div className="login-container">
      <h2>Login</h2>
      <form onSubmit={handleLogin} className="login-form">
        <div className="input-group">
          <label>Username:</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div className="input-group">
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        {error && <p className="error">{error}</p>}
        <button type="submit" className="login-button">
          Login
        </button>
      </form>
    </div>
  );
};

export default Login;
