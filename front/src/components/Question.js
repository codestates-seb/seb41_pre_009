import React from "react";
import { Link } from "react-router-dom";
import { Avatar } from "@mui/material";
import styles from "./Question.module.css";

const Question = () => {
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
            <Link to="/mainquestion">
              <p>question title 질문 저희 조 잘 할 수 있을까요?</p>
            </Link>
            <div className={styles["answer-title"]}>
              This is answer 네 그럼요!!!!!가보자9
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