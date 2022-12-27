import React, { useEffect} from "react";
import { Link , useNavigate} from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";

import styles from "./LoginPage.module.css"
import { authActions } from "../redux/auth";

const LoginPage = () => {

    const isAuth = useSelector(state => state.auth.isAuthenticated);

    const dispatch = useDispatch();

    const navigate = useNavigate();

    // 로그인이 완료되면 메인으로 이동
    useEffect(() => {
        if (isAuth) {
            navigate('/')
        }
    },[isAuth]);

    // 로그인 폼이 제출되면 isAuth가 true로 전환
    const loginHandler = (event) => {
        event.preventDefault();

        dispatch(authActions.login())
    }

    return (
        <div className={styles.container}>
            <button className={styles.withGoogle}>Log in with Google</button>
            <button className={styles.withGitHub}>Log in with GitHub</button>
            <button className={styles.withFaceBook}>Log in with Facebook</button>
            <div className={styles['form-container']} >
                <form onSubmit={loginHandler}>
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