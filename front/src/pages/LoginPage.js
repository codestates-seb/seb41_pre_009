import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";

import styles from "./LoginPage.module.css";
import GitHubIcon from "@mui/icons-material/GitHub";
import FacebookIcon from "@mui/icons-material/Facebook";
import { loginActions } from "../redux/loginreducer";

import axios from "axios";

const LoginPage = () => {
  //로그인 초기값 설정
  const initialInfo = { email: "", password: "" };
  const [loginInfo, setLoginInfo] = useState(initialInfo);
  // 비어있는 이메일
  const [emptyEmail, setEmptyEmail] = useState(false);
  // 비어있는 패스워드
  const [emptyPassword, setEmptyPassword] = useState(false);
  //유효성검사 이메일
  const [invalidEmail, setInvalidEmail] = useState(false);
  //유효성검사 패스워드
  const [invalidPassword, setInvalidPassword] = useState(false);
  //로그인 실패시 나타가게 하는 메세지
  const [loginFailed, setLoginFailed] = useState(false);

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const isLogin = useSelector((state) => state.isLogin);

  useEffect(() => {
    if (isLogin) {
      navigate("/");
      //실패시 나타나는 메세지창
      setLoginFailed(false);
    }
  }, [isLogin]);

  const loginHandler = (email, password) => {
    // eslint-disable-next-line
    const emailRegex =
      /^(([^<>()\[\].,;:\s@"]+(\.[^<>()\[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i;
    const passwordRegex = /^[A-Za-z\d!@#$%^&*()_+~\-=]{8,40}$/;

    // 비어있으면 empty메세지 출력
    if (email === "") setEmptyEmail(true);
    // 유효하지않으면(이메일 형식) invalid 메세지 출력
    else if (!emailRegex.test(email)) {
      setEmptyEmail(false);
      setInvalidEmail(true);
    }

    // 비어있으면 empty메세지 출력
    if (password === "") setEmptyPassword(true);
    // 유효하지않으면(8자 이상) invalid 메세지 출력
    else if (!passwordRegex.test(password)) {
      setEmptyPassword(false);
      setInvalidPassword(true);
    }
    // 유효한 이메일과 패스워드이면 로그인 요청
    if (emailRegex.test(email) && passwordRegex.test(password)) {
      setEmptyEmail(false);
      setEmptyPassword(false);
      setInvalidEmail(false);

      axios
        .post(
          "http://ec2-43-201-34-210.ap-northeast-2.compute.amazonaws.com:8080/auth/login",
          {
            email: email,
            password: password,
          }
        )
        .then((data) => {
          dispatch(loginActions.login());
          localStorage.clear();
          localStorage.setItem("accessToken", data.headers.authorization);
          localStorage.setItem("memberId", data.data.memberId);
          navigate("/");
          window.alert("success", "Login Complete !");
        })
        .then(() => {
          console.log(localStorage.getItem("accessToken"));
          console.log(localStorage.getItem("memberId"));
        });
      // 일치하는 유저가 존재 X
      // } catch (error) {
      //  alert("로그인 실패!");
      //   throw new Error(error);
      // }
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.socialbox}>
        <button className={styles.withGoogle}>
          {/* 구글아이콘 */}
          <svg aria-hidden="true" width="18" height="18" viewBox="0 0 18 18">
            <path
              d="M16.51 8H8.98v3h4.3c-.18 1-.74 1.48-1.6 2.04v2.01h2.6a7.8 7.8 0 0 0 2.38-5.88c0-.57-.05-.66-.15-1.18Z"
              fill="#4285F4"
            ></path>
            <path
              d="M8.98 17c2.16 0 3.97-.72 5.3-1.94l-2.6-2a4.8 4.8 0 0 1-7.18-2.54H1.83v2.07A8 8 0 0 0 8.98 17Z"
              fill="#34A853"
            ></path>
            <path
              d="M4.5 10.52a4.8 4.8 0 0 1 0-3.04V5.41H1.83a8 8 0 0 0 0 7.18l2.67-2.07Z"
              fill="#FBBC05"
            ></path>
            <path
              d="M8.98 4.18c1.17 0 2.23.4 3.06 1.2l2.3-2.3A8 8 0 0 0 1.83 5.4L4.5 7.49a4.77 4.77 0 0 1 4.48-3.3Z"
              fill="#EA4335"
            ></path>
          </svg>
          Sign up with Google
        </button>

        {/* 깃허브아이콘 */}
        <button className={styles.withGitHub}>
          <GitHubIcon />
          Sign up with GitHub
        </button>

        {/* 페이스북 아이콘 */}
        <button className={styles.withFaceBook}>
          <FacebookIcon />
          Sign up with Facebook
        </button>
      </div>

      <div className={styles["form-container"]}>
        <form>
          <div>Email</div>
          <input
            id="email"
            type="email"
            value={loginInfo.email}
            onChange={(event) =>
              setLoginInfo({ ...loginInfo, email: event.target.value })
            }
          ></input>
          {emptyEmail ? <p>Email cannot be empty.</p> : null}
          {invalidEmail ? <p>The email is not a valid email address.</p> : null}
          {loginFailed ? <p>The email or password is incorrect.</p> : null}
          {/* {email.length > 0 && <div>{emailErrorMessage}</div>} */}
          <div>PassWord</div>
          {/* type="password" 이면 *처럼나옴 */}
          <input
            id="password"
            type="password"
            value={loginInfo.password}
            onChange={(event) =>
              setLoginInfo({ ...loginInfo, password: event.target.value })
            }
          ></input>
          {emptyPassword ? <p>Password cannot be empty.</p> : null}
          {invalidPassword ? (
            <p>The password must be at least 8 characters long.</p>
          ) : null}
          {loginFailed ? <p>The email or password is incorrect.</p> : null}
          {/* {password.length > 0 && <div>{passwordErrorMessage}</div>} */}
          <button
            onClick={() => loginHandler(loginInfo.email, loginInfo.password)}
          >
            Log in
          </button>
        </form>
      </div>
    </div>
  );
};

export default LoginPage;
