import React from "react";
import { Link } from "react-router-dom";

import styles from "./LoginPage.module.css"

const LoginPage = () => {
    return (
        <div className={styles.container}>
            <button className={styles.withGoogle}>Log in with Google</button>
            <button className={styles.withGitHub}>Log in with GitHub</button>
            <button className={styles.withFaceBook}>Log in with Facebook</button>
            <div className={styles['form-container']} >
                <form>
                    <div>Email</div>
                    <input></input>
                    <div>PassWord</div>
                    <input></input>
                    <button>Log in</button>
                </form>
            </div>
        </div>
    )
}

export default LoginPage;