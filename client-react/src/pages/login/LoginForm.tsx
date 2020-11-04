import React from "react";
import { Form, Input, Button } from "antd";
import { UserOutlined, LockOutlined } from "@ant-design/icons";

const LoginForm = () => {
  const [form] = Form.useForm();

  const onFinish = (values: any) => {
    console.log(values);
  };
  return (
    <Form form={form} name="login" onFinish={onFinish}>
      <Form.Item name="username" rules={[{ required: true }]}>
        <Input placeholder="username" prefix={<UserOutlined />} />
      </Form.Item>
      <Form.Item name="password" rules={[{ required: true }]}>
        <Input.Password placeholder="password" prefix={<LockOutlined />} />
      </Form.Item>
      <Form.Item>
        <Button type="primary" block htmlType="submit">
          Login
        </Button>
      </Form.Item>
    </Form>
  );
};
export default LoginForm;
