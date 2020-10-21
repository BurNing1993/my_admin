import React, { useEffect, useState } from "react";
import { Layout, Menu } from "antd";
import Link from "next/link";
import { useRouter } from "next/router";

const AppMenu = () => {
  const router = useRouter();
  return (
    <Menu mode="horizontal" selectedKeys={[router.pathname]}>
      <Menu.Item key="/">
        <Link href="/">
          <a title="home" className="menu">
            home
          </a>
        </Link>
      </Menu.Item>
      <Menu.Item key="/about">
        <Link href="/about">
          <a title="about" className="menu">
            <div className="about">about</div>
          </a>
        </Link>
      </Menu.Item>
    </Menu>
  );
};
export default AppMenu;
