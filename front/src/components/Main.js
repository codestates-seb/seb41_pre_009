import React, { useState } from "react";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";

import Question from "./Question";

import { FilterList } from "@mui/icons-material";
import styles from './Main.module.css';

const Main = () => {

    const isAuth = useSelector(state => state.auth.isAuthenticated);

    return (
        <div className={styles.main}>
            <div className={styles['main-container']}>
                <div className={styles['main-top']}>
                    <h2>All Questions</h2>
                    {!isAuth && <Link to="/loginpage"><button>Ask Question</button></Link>}
                    {isAuth && <Link to="/askquestionpage"><button>Ask Question</button></Link>}
                </div>
                <div className={styles['main-desc']}>
                    <p>10 questions</p>
                    <div className={styles['main-filter']}>
                        <div className={styles['main-tabs']}>
                            <div className={styles['main-tab']}>
                                <p>Newest</p>
                            </div>
                            <div className={styles['main-tab']}>
                                <p>Active</p>
                            </div>
                            <div className={styles['main-tab']}>
                                <p>Bountied</p>
                            </div>
                            <div className={styles['main-tab']}>
                                <p>Unanswered</p>
                            </div>
                            <div className={styles['main-tab']}>
                                <p>More</p>
                            </div>
                        </div>
                        <div className={styles['main-filter__item']}>
                            <FilterList />
                            <p>Filter</p>
                        </div>
                    </div>
                </div>
                <div className={styles.questions}>
                    <div className={styles.question}>
                        <Question/>
                        <Question/>
                        <Question/>
                        <Question/>
                        <Question/>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Main;