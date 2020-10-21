import Head from "next/head";
import styles from "../styles/Home.module.css";
import { Button, Layout } from "antd";
import AppHeader from "../components/AppHeader/index";

export default function Home() {
  return (
    <>
      <Head>
        <title>App-home</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <main className="layout">
        <AppHeader />
      </main>
    </>
  );
}
