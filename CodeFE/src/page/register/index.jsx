import React from "react";
import { Form, Input, Button, Select } from "antd";
import api from "../../config/axios";
import { useNavigate } from "react-router-dom";
import "./index.scss";

function Register() {
  const navigate = useNavigate();
  const handleSubmit = async (values) => {
    if (values.password != values.confirmPassword) {
      console.log("Password and confirm password do not match");
      return;
    }
    try {
      const response = await api.post("register", values);
      console.log(response.data);
      alert("Register successfully");
      navigate("/login");
    } catch (error) {
      alert("Register failed");
    }
  };

  return (
    <div className="register">
      <div className="register-form-container">
        <Form onFinish={handleSubmit}>
          <div className="register-title">Register</div>
          <Form.Item label="Username" name="username">
            <Input />
          </Form.Item>
          <Form.Item label="Password" name="password">
            <Input />
          </Form.Item>
          <Form.Item label="Confirm Password" name="confirmPassword">
            <Input />
          </Form.Item>
          <Form.Item label="Name" name="name">
            <Input />
          </Form.Item>
          <Form.Item label="Phone" name="phone">
            <Input />
          </Form.Item>
          <Form.Item label="Email" name="email">
            <Input />
          </Form.Item>
          <Form.Item label="Role" name="role">
            <Select>
              <Select.Option value="MEMBER">MEMBER</Select.Option>
              <Select.Option value="SHOP">SHOP</Select.Option>
            </Select>
          </Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit">
              Register
            </Button>
          </Form.Item>
        </Form>
      </div>
    </div>
  );
}

export default Register;
