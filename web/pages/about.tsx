import Head from "next/head";
import React from "react";
import AppHeader from "../components/AppHeader/index";

const About = () => {
  return (
    <>
      <Head>
        <title>App-about</title>
      </Head>
      <AppHeader />
      <main className="layout">layout</main>
    </>
  );
};
export default About;
