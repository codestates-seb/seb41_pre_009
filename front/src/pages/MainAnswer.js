import React from "react";
import styles from "./MainAnswer.module.css";

import { Avatar } from "@mui/material";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import HistoryIcon from "@mui/icons-material/History";

// Answer 달린거 보여주는 칸
const MainAnswer = () => {
  return (
    <div className={styles.anwer}>
      <h2> 1 Answer</h2>
      <div className={styles["mainanswer-bar"]}>
        <div className={styles["just-answer"]}>
          <div className={styles["all-answer-left"]}>
            <div className={styles["vote-options"]}>
              <div className={styles.arrow}>
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
              <div> 10 </div>
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
          <div className={styles["post-mainanswer"]}>
            <div className={styles["post-mainanswer-body"]}>
              넹 !!!! 화이팅 !
            </div>

            <div className={styles["post-mainanswer-author"]}>
              <div className={styles["author-left"]}>
                <span> Share</span>
                <span> Edit</span>
                <span> Follow</span>
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
    </div>
  );
};

export default MainAnswer;