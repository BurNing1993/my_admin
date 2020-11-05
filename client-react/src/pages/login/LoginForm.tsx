import React, { useState } from "react";
import { Form, Input, Button } from "antd";
import { UserOutlined, LockOutlined } from "@ant-design/icons";
import {useDispatch} from "react-redux"
import {useHistory} from "react-router-dom";

import { login, LoginData } from "../../api/login";
import  {setToken} from "../../store/user/actions"

const LoginForm = () => {
  const [loading, setLoading] = useState(false);
  const dispatch = useDispatch();
  const history = useHistory();
  const [form] = Form.useForm();

  const onFinish = async (loginData: LoginData) => {
    try {
      setLoading(true);
      const {  data:  token  } = await login(loginData);
      dispatch(setToken(token));
      history.replace("/");
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <Form form={form} name="login" onFinish={onFinish}>
      <Form.Item
        name="username"
        rules={[
          {
            required: true,
            pattern: /^[a-zA-Z0-9_-]{4,16}$/,
            message: "Please input correct username!",
          },
        ]}
      >
        <Input placeholder="username" prefix={<UserOutlined />} />
      </Form.Item>
      <Form.Item
        name="password"
        rules={[
          {
            required: true,
            pattern: /^[a-zA-Z0-9_-]{4,16}$/,
            message: "Please input correct password!",
          },
        ]}
      >
        <Input.Password placeholder="password" prefix={<LockOutlined />} />
      </Form.Item>
      <Form.Item>
        <Button loading={loading} type="primary" block htmlType="submit">
          Login
        </Button>
      </Form.Item>
    </Form>
  );
};
export default LoginForm;
