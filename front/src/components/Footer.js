import React from "react";
import styles from "./Footer.module.css";

const Footer = () => {
  return (
    <div className={styles.footer}>
      <ul className={styles["footer-container"]}>
        <li className={styles["footer-left"]}>
          {/* 깃허브아이콘 삽입 */}
          <svg
            aria-hidden="true"
            class="native svg-icon iconLogoGlyphMd"
            width="32"
            height="37"
            viewBox="0 0 32 37"
          >
            <path d="M26 33v-9h4v13H0V24h4v9h22Z" fill="#BCBBBB"></path>
            <path
              d="m21.5 0-2.7 2 9.9 13.3 2.7-2L21.5 0ZM26 18.4 13.3 7.8l2.1-2.5 12.7 10.6-2.1 2.5ZM9.1 15.2l15 7 1.4-3-15-7-1.4 3Zm14 10.79.68-2.95-16.1-3.35L7 23l16.1 2.99ZM23 30H7v-3h16v3Z"
              fill="#F48024"
            ></path>
          </svg>
        </li>
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
