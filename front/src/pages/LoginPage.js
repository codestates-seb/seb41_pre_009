import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";

import styles from "./LoginPage.module.css";
import GitHubIcon from "@mui/icons-material/GitHub";
import FacebookIcon from "@mui/icons-material/Facebook";
import { loginAction } from "../redux/actions";

import axios from "axios";

// const LoginPage = () => {
//   const isAuth = useSelector((state) => state.auth.isAuthenticated);

//   const dispatch = useDispatch();
//   const navigate = useNavigate();

//   // 로그인이 완료되면 메인으로 이동
//   useEffect(() => {
//     if (isAuth) {
//       navigate("/");
//     }
//   }, [isAuth]);

//   const [email, setEmail] = useState("");
//   const [emailErrorMessage, setEmailErrorMessage] = useState("");
//   const [password, setPassword] = useState("");
//   const [passwordErrorMessage, setpasswordErrorMessage] = useState("");

//   // 추가 이메일이 비어있을때
//   // function isValidEmail(email) {
//   //   if (email.length === 0) {
//   //     return false;
//   //   }
//   //   return true;
//   // }

//   // 이메일 유효성 검사 - 이메일형식 들어가야지 통과되게끔
//   const emailInputValueHandler = (e) => {
//     const emailRegex =
//       // /([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
//       /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
//     const currentEmail = e.target.value;
//     setEmail(currentEmail);

//     if (!emailRegex.test(currentEmail)) {
//       setEmailErrorMessage("올바른 이메일 형식이 아닙니다.");
//     } else {
//       setEmailErrorMessage("");
//     }
//   };

//   //패스워드 유효성 검사 -8글자 이상 + 영어 1글자 + 특수문자 1개 쳐야지 통과되게끔
//   const passwordInputValueHandler = (e) => {
//     const passwordRegex = /^(?=.*[a-zA-Z])((?=.*\d)(?=.*\W)).{8,16}$/;
//     const currentPassword = e.target.value; // setPassword(e.target.value);;
//     setPassword(currentPassword);

//     if (!passwordRegex.test(currentPassword)) {
//       setpasswordErrorMessage("비밀번호를 확인하세요.");
//     } else {
//       setpasswordErrorMessage("");
//     }
//   };

//   // 로그인 폼이 제출되면 isAuth가 true로 전환
//   const loginSubmitHandle = (event) => {
//     event.preventDefault(); // 창이 새로고침되는 것을 막는다.

//     //이메일칸 or 패스워드칸이  비어있으면 제출못하게 막는다.
//     if (email.length === 0 || password.length === 0) {
//       window.alert("이메일과 비밀번호를 입력해주세요.");
//       return;
//     }
//     axios
//       .post(
//         // "https://b647-61-252-125-198.jp.ngrok.io/members/login",
//         // "https://stackoverflowclone-7b282-default-rtdb.firebaseio.com/members/login.json",
//         // "https://b647-61-252-125-198.jp.ngrok.io/auth/login ",
//         // "https://2a4a-61-252-125-198.jp.ngrok.io/auth/login",
//         "https://463b-61-252-125-198.jp.ngrok.io/auth/login",

//         {
//           email,
//           password,
//         }
//       )
//       .then((response) => {
//         // localStorage.setItem("Token", response.headers.authorization);
//         console.log(response);
//         //토큰저장?
//         // localStorage.setItem("isLogin", res.data.token);
//         // localStorage.setItem("UserID", res.data.id);
//         window.alert("로그인 성공");
//         if ((response.status = 200)) {
//           // return navigate("/loginpage");
//           dispatch(authActions.login());
//         }
//       })
//       .catch((err) => {
//         // setMessage(err.response.data.message);
//         console.log(err);
//         alert("로그인 정보가 잘못되었습니다. 다시 시도해 주세요.");
//       });
//   };

// 원본 코드
// const LoginPage = () => {
//   const isAuth = useSelector((state) => state.auth.isAuthenticated);

//   const dispatch = useDispatch();
//   const navigate = useNavigate();

//   // 로그인이 완료되면 메인으로 이동
//   useEffect(() => {
//     if (isAuth) {
//       navigate("/");
//     }
//   }, [isAuth]);

//   // 로그인 폼이 제출되면 isAuth가 true로 전환
//   const loginHandler = (event) => {
//     event.preventDefault();

//     dispatch(authActions.login());
//   };

const LoginPage = () => {
  //로그인 초기값 설정
  const initialInfo = { email: "", password: "" };
  const [loginInfo, loginInfoSet] = useState(initialInfo);
  // 비어있는 이메일
  const [emptyEmail, emptyEmailSet] = useState(false);
  // 비어있는 패스워드
  const [emptyPassword, emptyPasswordSet] = useState(false);
  //유효성검사 이메일
  const [invalidEmail, invalidEmailSet] = useState(false);
  //유효성검사 패스워드
  const [invalidPassword, invalidPasswordSet] = useState(false);
  //로그인 실패시 나타가게 하는 메세지
  const [loginFailed, loginFailedSet] = useState(false);

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const { user } = useSelector((state) => state.loginReducer);

  useEffect(() => {
    if (user) {
      navigate("/");
      //실패시 나타나는 메세지창
      loginFailedSet(false);
    }
  }, [user]);

  const handeLogin = (email, password) => {
    // eslint-disable-next-line
    const emailRegex =
      /^(([^<>()\[\].,;:\s@"]+(\.[^<>()\[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i;
    const passwordRegex = /^[A-Za-z\d!@#$%^&*()_+~\-=]{8,40}$/;

    // 비어있으면 empty메세지 출력
    if (email === "") emptyEmailSet(true);
    // 유효하지않으면(이메일 형식) invalid 메세지 출력
    else if (!emailRegex.test(email)) {
      emptyEmailSet(false);
      invalidEmailSet(true);
    }

    // 비어있으면 empty메세지 출력
    if (password === "") emptyPasswordSet(true);
    // 유효하지않으면(8자 이상) invalid 메세지 출력
    else if (!passwordRegex.test(password)) {
      emptyPasswordSet(false);
      invalidPasswordSet(true);
    }
    // 유효한 이메일과 패스워드이면 로그인 요청
    if (emailRegex.test(email) && passwordRegex.test(password)) {
      emptyEmailSet(false);
      emptyPasswordSet(false);
      invalidEmailSet(false);

      dispatch(loginAction({ email, password }));
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
              loginInfoSet({ ...loginInfo, email: event.target.value })
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
              loginInfoSet({ ...loginInfo, password: event.target.value })
            }
          ></input>
          {emptyPassword ? <p>Password cannot be empty.</p> : null}
          {invalidPassword ? (
            <p>The password must be at least 8 characters long.</p>
          ) : null}
          {loginFailed ? <p>The email or password is incorrect.</p> : null}
          {/* {password.length > 0 && <div>{passwordErrorMessage}</div>} */}
          <button
            onClick={() => handeLogin(loginInfo.email, loginInfo.password)}
          >
            Log in
          </button>
        </form>
      </div>
    </div>
  );
};

export default LoginPage;
