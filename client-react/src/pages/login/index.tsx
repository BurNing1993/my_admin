import React from "react";
import { Card, Col, Row } from "antd";
import LoginForm from "./LoginForm";
import styles from "./login.module.css";

const About = () => {
  return (
    <Row align="middle" justify="center" className={styles.container}>
      <Col xs={24} sm={12} md={10} lg={8} xl={6}>
        <Card title="Login">
          <LoginForm />
        </Card>
      </Col>
    </Row>
  );
};
export default About;
