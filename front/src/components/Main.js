import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import axios from "axios";

import Question from "./Question";

import { FilterList, PropaneSharp } from "@mui/icons-material";
import styles from "./Main.module.css";

const Main = () => {
  const isLogin = useSelector((state) => state.isLogin);

  const [data, setData] = useState([]);

  const token = localStorage.getItem("accessToken");

  useEffect(() => {
    fetch(
      `http://ec2-43-201-34-210.ap-northeast-2.compute.amazonaws.com:8080/questions`,
      {
        headers: {
          "Content-type": "application/json",
          // Authorization: token,
        },
      }
    )
      .then((res) => {
        if (!res.ok) {
          throw Error("could not fetch the data for that resource");
        }
        return res.json();
      })
      .then((data) => {
        setData(data.data);
        // setTotalQNum(data.pageInfo.totalElements);
      })
      .catch((error) => {
        throw new Error(error);
      });
  });

  return (
    <div className={styles.main}>
      <div className={styles["main-container"]}>
        <div className={styles["main-top"]}>
          <h2>All Questions</h2>
          {!isLogin && (
            <Link to="/loginpage">
              <button>Ask Question</button>
            </Link>
          )}
          {isLogin && (
            <Link to="/askquestionpage">
              <button>Ask Question</button>
            </Link>
          )}
        </div>
        <div className={styles["main-desc"]}>
          <p>{data.length} questions</p>
          <div className={styles["main-filter"]}>
            <div className={styles["main-tabs"]}>
              <div className={styles["main-tab"]}>
                <p>Newest</p>
              </div>
              <div className={styles["main-tab"]}>
                <p>Active</p>
              </div>
              <div className={styles["main-tab"]}>
                <p>Bountied</p>
              </div>
              <div className={styles["main-tab"]}>
                <p>Unanswered</p>
              </div>
              <div className={styles["main-tab"]}>
                <p>More</p>
              </div>
            </div>
            <div className={styles["main-filter__item"]}>
              <FilterList />
              <p>Filter</p>
            </div>
          </div>
        </div>
        <div className={styles.questions}>
          <div className={styles.question}>
            {data.map((el, index) => {
              return (
                <Question
                  key={index}
                  title={el.title}
                  body={el.content}
                  index={index}
                />
              );
            })}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Main;
