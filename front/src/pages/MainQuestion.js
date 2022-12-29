import React, { useState, useEffect } from "react";
import styles from "./MainQuestion.module.css";
import { Link, useParams } from "react-router-dom";
import axios from "axios";
import Sidebar from "../components/Sidebar";
import MainAnswer from "./MainAnswer";
import YourAnswer from "./YourAnswer";

import { Avatar } from "@mui/material";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import HistoryIcon from "@mui/icons-material/History";

const MainQuestion = () => {

  const [questionData, setQuestionData] = useState(null)

  const params = useParams();

  const [indexNum, setIndexNum] = useState(0);

  const [data, setData] = useState([]);

  const [answer, setAnswer] = useState([]);

  useEffect(() => {
    const getData = async () => {
        const response = await axios.get("https://stackoverflow-6b095-default-rtdb.firebaseio.com/questions.json");
        const refinedIndex = Object.keys(response.data);
        const refinedAnswer = [];
        setIndexNum(refinedIndex[params.id - 1]);
        setData(response.data[refinedIndex[params.id - 1]]);
        for (let i = 0; i < Object.keys(data.answers).length; i++) {
          refinedAnswer.push(data.answers[Object.keys(data.answers)[i]])
        }
        setAnswer(refinedAnswer);
        // console.log(response.data[Object.keys(response.data)[0]].title)
    }
    getData();
  })

  const handleAnswerSubmit = (body) => {
    const data = { answer: body };
    axios(`https://stackoverflow-6b095-default-rtdb.firebaseio.com/questions/${indexNum}/answers.json`, {
      method: 'post',
      headers: {
        // Authorization: user.token,
      },
      data,
    })
      // .then((res) => {
      //   const newAnswer = { ...res.data, voteCount: 0 };
      //   setQuestionData({
      //     ...questionData,
      //     answers: [...questionData.answers, newAnswer],
      //   });
      // })
      // .catch((error) => console.error(error));
  };

  // console.log(Object.values(data.answers))

  return (
    <div className={styles.question}>
      <Sidebar />
      <div className={styles.content}>
        <div className={styles["main-inner"]}>
          <div className={styles["mainquestion-header"]}>
            <h2>{data.title}</h2>
            <Link to="/askquestionpage">
              <button>Ask Question</button>
            </Link>
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
                  {data.body}
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
          <h2>{answer.length} ANSWERS</h2>
          {answer.map(el => {
            return (
              <MainAnswer data={el.answer} />
            )
          })}
          <YourAnswer handleAnswerSubmit={handleAnswerSubmit} />
        </div>
      </div>
    </div>
  );
};

export default MainQuestion;