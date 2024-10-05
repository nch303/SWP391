import React from "react";
import "./index.scss";

import { Button, Form, Input } from "antd";
import api from "../../config/axios";
import { useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();
  const [error, setError] = React.useState(null);

  const handleSubmit = async ({ username, password }) => {
    try {
      const { data } = await api.post("login", { username, password });
      const { token } = data;
      localStorage.setItem("token", token);
      navigate("/");
    } catch (err) {
      setError(err.response.data.message);
    }
  };

  return (
    <div className="login">
      <div className="login-form-container">
        <div className="loginForm-title">Login</div>
        <Form onFinish={handleSubmit}>
          <Form.Item
            label="Username"
            name="username"
            rules={[{ required: true, message: "Please input your username!" }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="Password"
            name="password"
            rules={[{ required: true, message: "Please input your password!" }]}
          >
            <Input.Password />
          </Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit">
              Login
            </Button>
          </Form.Item>
          {error && <div className="loginForm-error">{error}</div>}
        </Form>
        <div className="loginForm-register">
          <p>
            Don't have an account?{" "}
            <a onClick={() => navigate("/register")}>Register</a>{" "}
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
