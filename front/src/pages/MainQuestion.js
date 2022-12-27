import React from "react";
import styles from "./MainQuestion.module.css";
// import { Link } from "react-router-dom";
import Sidebar from "../components/Sidebar";
import MainAnswer from "./MainAnswer";
import YourAnswer from "./YourAnswer";

import { Avatar } from "@mui/material";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import HistoryIcon from "@mui/icons-material/History";

const MainQuestion = () => {
  return (
    <div className={styles.question}>
      <Sidebar />
      <div className={styles.content}>
        <div className={styles["main-inner"]}>
          <div className={styles["mainquestion-header"]}>
            <h2>question title 질문 저희조 잘 할 수 있을까요?</h2>
            {/* <Link to="/QuestionPage"> */}
            <button>Ask Question</button>
            {/* </Link> */}
          </div>
          <div className={styles["main-desc"]}>
            <div className={styles.info}>
              <span> Asked today</span>
              <span> Modified today</span>
              <span> Viewed 10 times</span>
            </div>
          </div>
          <div className={styles["mainquestion-bar"]}>
            <div className={styles["just-question"]}>
              <div className={styles["all-questions-left"]}>
                <div className={styles["vote-options"]}>
                  <div className={styles.arrow}>
                    {/* vote버튼 이미지 */}
                    <svg
                      aria-hidden="true"
                      width="36"
                      height="36"
                      viewBox="0 0 36 36"
                      fill="#babfc4"
                    >
                      <path d="M2 25h32L18 9 2 25Z" />
                    </svg>
                  </div>
                  <div>10</div>
                  {/* vote버튼 이미지 */}
                  <svg
                    aria-hidden="true"
                    width="36"
                    height="36"
                    viewBox="0 0 36 36"
                    fill="#babfc4"
                  >
                    <path d="M2 11h32L18 27 2 11Z" />
                  </svg>
                </div>
                <div className={styles.icons}>
                  <BookmarkBorderIcon />
                </div>
                <div className={styles.icons}>
                  <HistoryIcon />
                </div>
              </div>

              <div className={styles["post-mainqeustion"]}>
                <div className={styles["post-mainqeustion-body"]}>
                  저희 가보자9조는 화목합니다!!!!! 다들 너무 좋아욥!!저만 잘하면
                  되는데 저 잘할 수 있을까요?
                </div>
                <span className={styles.tags}>react</span>
                <span className={styles.tags}>css</span>
                <span className={styles.tags}>html</span>

                <div className={styles["post-mainqeustion-author"]}>
                  <div className={styles["author-left"]}>
                    <span> Share</span>
                    <span> Edit</span>
                    <span> Follow</span>
                  </div>
                  <div className={styles["author-middle"]}>
                    <div className={styles["author-edited"]}>
                      edited 질문시간이 뜹니다!
                      <div className={styles["author-info"]}>
                        <Avatar />
                        <div className={styles["author-name"]}>작성자</div>
                      </div>
                    </div>
                  </div>
                  <div className={styles["author-right"]}>
                    <div className={styles["author-asked"]}>
                      asked 질문시간이 뜹니다!
                      <div className={styles["author-info"]}>
                        <Avatar />
                        <div className={styles["author-name"]}>작성자</div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <MainAnswer />
          <YourAnswer />
        </div>
      </div>
    </div>
  );
};

export default MainQuestion;