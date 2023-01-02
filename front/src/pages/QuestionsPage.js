import React from "react";

import Main from "../components/Main";
import RightSidebar from "../components/RightSIdebar";
import Sidebar from "../components/Sidebar";
import Footer from "../components/Footer";

import styles from "./QuestionsPage.module.css";

const QuestionsPage = () => {
  return (
    <React.Fragment>
      <div className={styles.container}>
        <Sidebar />
        <Main />
        <RightSidebar />
      </div>
      <Footer />
    </React.Fragment>
  );
};

export default QuestionsPage;
