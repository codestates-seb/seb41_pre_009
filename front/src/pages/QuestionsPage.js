import React from "react";

import Main from "../components/Main";
import RightSidebar from "../components/RightSIdebar";
import Sidebar from "../components/Sidebar";

import styles from "./QuestionsPage.module.css";

const QuestionsPage = () => {
    return (
        <div className={styles.container}>
            <Sidebar/>
            <Main/>
            <RightSidebar/>
        </div>
    )
}

export default QuestionsPage;