import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import axios from "axios";

import Question from "./Question";

import { FilterList, PropaneSharp } from "@mui/icons-material";
import styles from "./Main.module.css";

const Main = () => {
  // const isAuth = useSelector(state => state.auth.isAuthenticated);
  const { user } = useSelector((state) => state.loginReducer);

  const [data, setData] = useState([]);

  useEffect(() => {
    const getData = async () => {
      try {
        const response = await axios.get(
          "https://stackoverflow-6b095-default-rtdb.firebaseio.com/questions.json"
        );
        const refinedData = [];
        for (let i = 0; i < Object.keys(response.data).length; i++) {
          refinedData.push(response.data[Object.keys(response.data)[i]]);
        }
        setData(refinedData);
        // console.log(response.data[Object.keys(response.data)[0]].title)
      } catch (error) {
        window.alert("비상!");
      }
    };
    getData();
  });

  return (
    <div className={styles.main}>
      <div className={styles["main-container"]}>
        <div className={styles["main-top"]}>
          <h2>All Questions</h2>
          {!user && (
            <Link to="/loginpage">
              <button>Ask Question</button>
            </Link>
          )}
          {user && (
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
              return <Question title={el.title} body={el.body} index={index} />;
            })}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Main;
