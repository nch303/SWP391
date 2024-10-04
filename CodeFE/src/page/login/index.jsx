import React from "react";
import "./index.scss";

import { Button, Form, Input } from "antd";
import api from "../../config/axios";
import { useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();
  const handleSubmit = async (values) => {
    try {
      const reponse = await api.post("login", values);
      console.log(reponse.data);
      alert("login successfully");
      navigate("/");
      const { token } = reponse.data;
      localStorage.setItem("token", token);
    } catch (err) {
      alert("login failed");
    }
  };
  return (
    <div className="login">
      <div className="login-form-container">
        <div className="loginForm-title">Login</div>
        <Form onFinish={handleSubmit}>
          <Form.Item label="Username" name="username">
            <Input />
          </Form.Item>
          <Form.Item label="Password" name="password">
            <Input />
          </Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit">
              Login
            </Button>
          </Form.Item>
        </Form>
        <div className="loginForm-register">
          <p>
            Don't have an account?{" "}
            <a onClick={() => navigate("/Register")}>Register</a>{" "}
          </p>
        </div>
        <hr />

        <Form.Item>
          <Button type="primary">Login with Google</Button>
        </Form.Item>
      </div>
    </div>
  );
}

export default Login;
