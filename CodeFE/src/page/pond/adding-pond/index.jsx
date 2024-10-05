import React, { useState } from "react";
import { Form, Input, Button, Image, Upload } from "antd";
import api from "../../../config/axios";
import { useNavigate } from "react-router-dom";
import { PlusOutlined } from "@ant-design/icons";
import uploadFile from "../../../assets/hook/useUpload";


function AddPond() {
  const [form] = Form.useForm();
  const navigate = useNavigate();
  const [fileList, setFileList] = useState([]);
  const [previewImage, setPreviewImage] = useState("");
  const [previewOpen, setPreviewOpen] = useState(false);

  const handlePreview = async (file) => {
    if (!file.url && !file.preview) {
      file.preview = await getBase64(file.originFileObj);
    }
    setPreviewImage(file.url || file.preview);
    setPreviewOpen(true);
  };

  const handleChange = ({ fileList: newFileList }) => setFileList(newFileList);

  const uploadButton = (
    <button
      style={{
        border: 0,
        background: "none",
        color: "black",
      }}
      type="button"
    >
      <PlusOutlined />
      <div
        style={{
          marginTop: 8,
        }}
      >
        Upload
      </div>
    </button>
  );

  const getBase64 = (file) =>
    new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result);
      reader.onerror = (error) => reject(error);
    });

  const handleSubmit = async (values) => {
    try {
      const url = await uploadFile(fileList[0].originFileObj);
      console.log(url);
      values.image = url;
      const response = await api.post("pond/create", values);
      console.log(response.data);
      alert("Pond added successfully");
      navigate("/managerPond");
    } catch (error) {
      console.log("pond adding failed");
    }
  };

  return (
    <div>
      <Form
        form={form}
        name="add-pond-form"
        onFinish={handleSubmit}
        labelCol={{ span: 8 }}
        wrapperCol={{ span: 16 }}
      >
        <h1 style={{ textAlign: "center" }}>Add Pond</h1>
        <Form.Item
          label="Pond Name"
          name="pondName"
          rules={[{ required: true, message: "Please input pond name!" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          label="Image"
          name="image"
          rules={[{ required: true, message: "Please upload pond image!" }]}
        >
          <Upload
            action="https://660d2bd96ddfa2943b33731c.mockapi.io/api/upload"
            listType="picture-card"
            fileList={fileList}
            onPreview={handlePreview}
            onChange={handleChange}
          >
            {fileList.length >= 8 ? null : uploadButton}
          </Upload>
        </Form.Item>
        <Form.Item
          label="Area"
          name="area"
          rules={[{ required: true, message: "Please input area!" }]}
        >
          <Input type="number" />
        </Form.Item>
        <Form.Item
          label="Depth"
          name="depth"
          rules={[{ required: true, message: "Please input depth!" }]}
        >
          <Input type="number" />
        </Form.Item>
        <Form.Item
          label="Volume"
          name="volume"
          rules={[{ required: true, message: "Please input volume!" }]}
        >
          <Input type="number" />
        </Form.Item>
        <Form.Item
          label="Drain Count"
          name="drainCount"
          rules={[{ required: true, message: "Please input drain count!" }]}
        >
          <Input type="number" />
        </Form.Item>
        <Form.Item
          label="Skimmer Count"
          name="skimmerCount"
          rules={[{ required: true, message: "Please input skimmer count!" }]}
        >
          <Input type="number" />
        </Form.Item>
        <Form.Item
          label="Pumping Capacity"
          name="pumpingCapacity"
          rules={[
            { required: true, message: "Please input pumping capacity!" },
          ]}
        >
          <Input type="number" />
        </Form.Item>
        <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
          <Button type="primary" htmlType="submit">
            Add Pond
          </Button>
        </Form.Item>
      </Form>
      {previewImage && (
        <Image
          wrapperStyle={{ display: "none" }}
          preview={{
            visible: previewOpen,
            onVisibleChange: (visible) => setPreviewOpen(visible),
            afterOpenChange: (visible) => !visible && setPreviewImage(""),
          }}
          src={previewImage}
        />
      )}
    </div>
  );
}

export default AddPond;

