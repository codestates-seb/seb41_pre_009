import React from "react";
import styles from "./Footer.module.css";

const Footer = () => {
  return (
    <div className={styles.footer}>
      <ul className={styles["footer-container"]}>
        <li className={styles["footer-left"]}>로고 들어갑니다...</li>
        <li className={styles["footer-main"]}>
          <ul>
            <h5>STACK OVERFLOW</h5>
            <li>Questions</li>
            <li>Help</li>
          </ul>
          <ul>
            <h5>PRE PROJECT</h5>
            <li>Stackoverflow</li>
            <li>Clonecoding</li>
          </ul>
          <ul>
            <h5>TEAM</h5>
            <li>가보자9</li>
          </ul>
          <ul>
            <h5>MEMBER</h5>
            <li>정유경</li>
            <li>강예은</li>
            <li>강하렴</li>
            <li>김일태</li>
            <li>박영선</li>
          </ul>
          <ul>
            <h5>MBTI</h5>
            <li>ISTP</li>
            <li>ESTP</li>
            <li>ENTJ</li>
            <li>ISFJ</li>
            <li>ISFJ</li>
          </ul>
        </li>
        <li className={styles["footer-right"]}>
          <div className={styles["footer-social"]}>
            <ul>
              <li>Blog </li>
              <li>FaceBook</li>
              <li>Twitter</li>
              <li>LinkedIn</li>
              <li>Instagram</li>
            </ul>
          </div>
        </li>
      </ul>
    </div>
  );
};

export default Footer;
