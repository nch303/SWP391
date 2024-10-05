import React from "react";
import { useAuthStore } from "../../zustand/useAuthStore";
import { Avatar, Button, Card, Col, Row, Space, Typography } from "antd";
import { useNavigate } from "react-router-dom";

function Profile() {
  const navigate = useNavigate();
  const { user } = useAuthStore();

  const handleLogout = () => {
    localStorage.removeItem("user");
    navigate("/login");
  };

  return (
    <Row justify="center">
      <Col span={8}>
        <Card title="Profile" style={{ marginTop: 16 }}>
          <Space direction="vertical" size="large">
            <Avatar size={100} src={user?.avatar} />
            <Typography.Title level={3}>{user?.name}</Typography.Title>
            <Typography.Text>{user?.email}</Typography.Text>
            <Typography.Text>{user?.phone}</Typography.Text>
            <Button type="primary" danger onClick={handleLogout}>
              Logout
            </Button>
          </Space>
        </Card>
      </Col>
    </Row>
  );
}

export default Profile;
