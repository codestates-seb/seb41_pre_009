import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";

// import { renderActions } from "../redux/renderSlice";

import axios from "axios";
import MDEditor from "@uiw/react-md-editor";
import Footer from "../components/Footer";

import styles from "./AskQuestionPage.module.css";

const AskQuestionPage = () => {
  //   const isAuth = useSelector((state) => state.auth.isAuthenticated);
  const { user } = useSelector((state) => state.loginReducer);

  const dispatch = useDispatch();

  const navigate = useNavigate();

  const [title, setTitle] = useState("");
  const [body, setBody] = useState("");

  const memberId = localStorage.getItem("memberId");
  const initialToken = localStorage.getItem("accessToken");

  const onChangeTitle = (event) => {
    setTitle(event.target.value);
  };

  // const onChangeBody = (event) => {
  //     setBody(event.target.value);
  // }

  const onSubmitHandler = (title, body) => {
    fetch("https://28ea-112-149-100-82.jp.ngrok.io/questions/ask", {
      method: "POST",
      headers: {
        "Content-type": "application/json",
        Authorization: initialToken,
      },
      body: JSON.stringify({
        questionWriterId: memberId,
        title: title,
        content: body,
      }),
    })
      .then((res) => res.json())
      .then((res) => {
        console.log(res);
      });
    navigate("/");
  };

  return (
    <React.Fragment>
      <div className={styles["big-container"]}>
        <div className={styles["question-create-container"]}>
          <div className={styles["question-create-description"]}>
            <div className={styles["question-create-title"]}>
              <h1>Ask a public question</h1>
            </div>
            <div className={styles["question-create-description-box"]}>
              <div>
                <h2>Writing a good question</h2>
                <p>
                  You’re ready to ask a programming-related question and this
                  form will help guide you through the process. <br />
                  Looking to ask a non-programming question? See the topics here
                  to find a relevant site.
                </p>
                <h3>Steps</h3>
                <p>
                  <ul>
                    <li>Summarize your problem in a one-line title.</li>
                    <li>Describe your problem in more detail.</li>
                    <li>
                      Describe what you tried and what you expected to happen.
                    </li>
                    <li>
                      Add “tags” which help surface your question to members of
                      the community.
                    </li>
                    <li>Review your question and post it to the site.</li>
                  </ul>
                </p>
              </div>
            </div>
          </div>
          <form onSubmit={() => onSubmitHandler(title, body)}>
            <div className="qc-form">
              <div>
                <div className={styles["qc-form-title"]}>
                  <label htmlFor="title">Title</label>
                  <div className={styles["qc-form-title-children"]}>
                    <label htmlFor="title">
                      Be specific and imagine you’re asking a question to
                      another person.
                    </label>
                  </div>
                </div>
                <div className={styles["qc-form-title-input"]}>
                  <input
                    value={title}
                    placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
                    onChange={onChangeTitle}
                  />
                </div>
              </div>
            </div>

            <div className={styles["qc-form flexstart"]}>
              <div>
                <div className={styles["qc-form-title"]}>
                  <label htmlFor="qc-body">
                    What are the details of your problem?
                  </label>
                  <div div className={styles["qc-form-title-children"]}>
                    <label htmlFor="qc-body">
                      Introduce the problem and expand on what you put in the
                      title. Minimum 20 characters.
                    </label>
                  </div>
                </div>
                <div className={styles["qc-form-body"]} id="qc-body">
                  <MDEditor height={200} value={body} onChange={setBody} />
                </div>
                <button>Post your Question</button>
              </div>
            </div>
          </form>
        </div>
      </div>
      <Footer />
    </React.Fragment>
  );
};

export default AskQuestionPage;
