import React, { useState } from "react";

import { Link, useNavigate } from "react-router-dom";

import { useSelector, useDispatch } from "react-redux";

import { renderActions } from "../redux/render";

import axios from "axios";

import MDEditor from '@uiw/react-md-editor';

import Footer from "../components/Footer";

import { Post } from "../API/api";

import styles from "./AskQuestionPage.module.css";



const AskQuestionPage = () => {

    const isAuth = useSelector(state => state.auth.isAuthenticated);

    const dispatch = useDispatch();

    const navigate = useNavigate();

    const [title, setTitle] = useState("");
    const [body, setBody] = useState("");

    const onChangeTitle = (event) => {
        setTitle(event.target.value);
    }

    // const onChangeBody = (event) => {
    //     setBody(event.target.value);
    // }

    const onSubmitHandler = (title, body) => {
        const data = { title, body }
        Post("https://stackoverflow-6b095-default-rtdb.firebaseio.com/questions.json", data)
        // dispatch(renderActions());
        navigate("/");
    };

    return (
        <React.Fragment>
            <div className={styles["big-container"]}>
                <div className={styles["question-create-container"]}>
                    <div className="question-create-description">
                        <div className="question-create-title">
                            <h2>Ask a public question</h2>
                        </div>
                        <div className="question-create-description-box">
                            <div>
                                <h3>Writing a good question</h3>
                                <p>
                                    You’re ready to ask a programming-related question and this
                                    form will help guide you through the process. <br />
                                    Looking to ask a non-programming question? See the topics here
                                    to find a relevant site.
                                </p>
                                <h4>Steps</h4>
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
                                <div className="qc-form-title">
                                    <label htmlFor="title">Title</label>
                                    <label htmlFor="title">
                                        Be specific and imagine you’re asking a question to another
                                        person.
                                    </label>
                                </div>
                                <div>
                                    <input value={title} onChange={onChangeTitle} />
                                </div>
                            </div>
                        </div>
                        <div className="qc-form flexstart">
                            <div>
                                <div className="qc-form-title">
                                    <label htmlFor="qc-body">
                                        What are the details of your problem?
                                    </label>
                                    <label htmlFor="qc-body">
                                        Introduce the problem and expand on what you put in the title.
                                        Minimum 20 characters.
                                    </label>
                                </div>
                                <div className="qc-form-body" id="qc-body">
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
    )
}

export default AskQuestionPage;