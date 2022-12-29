import React, { useEffect, useState } from "react";

import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import { Avatar } from "@mui/material";
import styles from "./Question.module.css";

const Question = (props) => {

  const navigate = useNavigate();

  const mainQuestionView = (index) => {
    navigate(`/mainquestion/${index}`);
  }

  return (
    <div className={styles.question}>
      <div className={styles["question-container"]}>
        <div className={styles["question-left"]}>
          <div className={styles.options}>
            <div className={styles.option}>
              <div> 0 vote </div>
            </div>
            <div className={styles.option}>
              <span> 0 answers </span>
            </div>
            <div className={styles.option}>
              <span> 0 views </span>
            </div>
          </div>
        </div>
        <div className={styles.answer}>
          <h3 className={styles["qeustion-title"]}>
            <div onClick={() => mainQuestionView(props.index + 1)}>{props.title}</div>
            <div className={styles["answer-title"]}>
              {props.body}
            </div>
            <span className={styles.tags}>react</span>
            <span className={styles.tags}>css</span>
            <span className={styles.tags}>html</span>
          </h3>
          <div className={styles.author}>
            <Avatar />
            <p>작성자</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Question;