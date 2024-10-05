import React from "react";
import { Form, Input, Button, Select } from "antd";
import api from "../../config/axios";
import { useNavigate } from "react-router-dom";
import "./index.scss";

function Register() {
  const navigate = useNavigate();
  const [form] = Form.useForm();

  const onFinish = async (values) => {
    if (values.password !== values.confirmPassword) {
      form.setFields([
        {
          name: "password",
          errors: ["Password and confirm password do not match"],
        },
      ]);
      return;
    }

    try {
      const response = await api.post("register", values);
      alert("Register successfully");
      console.log(response.data);
      navigate("/login");
    } catch (error) {
      alert("Register failed");
    }
  };

  return (
    <div className="register">
      <div className="register-form-container">
        <Form form={form} onFinish={onFinish}>
          <div className="register-title">Register</div>
          <Form.Item
            className="register-form-inputbox"
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
          <Form.Item
            label="Confirm Password"
            name="confirmPassword"
            rules={[
              {
                required: true,
                message: "Please input your confirm password!",
              },
            ]}
          >
            <Input.Password />
          </Form.Item>
          <Form.Item
            label="Name"
            name="name"
            rules={[{ required: true, message: "Please input your name!" }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="Phone"
            name="phone"
            rules={[{ required: true, message: "Please input your phone!" }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="Email"
            name="email"
            rules={[{ required: true, message: "Please input your email!" }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="Role"
            name="role"
            rules={[{ required: true, message: "Please select your role!" }]}
          >
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
