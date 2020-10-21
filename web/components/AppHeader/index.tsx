import React from "react";
import { Layout, Menu, Breadcrumb, Row, Col } from "antd";
import Logo from "./Logo";
import AppMenu from "./AppMenu";
const { Header, Content, Footer } = Layout;
import style from "../../styles/components/AppHeader.module.css";

export default function AppHeader() {
  return (
    <header className={style.header}>
      <Row style={{ width: "100%" }}>
        <Col xs={24} sm={6} md={6} lg={4} xl={4} className="text-center">
          <Logo />
        </Col>
        <Col xs={24} sm={18} md={18} lg={20} xl={20}>
          <AppMenu />
        </Col>
      </Row>
    </header>
  );
}
