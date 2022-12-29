import React from "react";
import SignupSidebar from "./SignupSidebar";
import styles from "./SignupPage.module.css";
import GitHubIcon from "@mui/icons-material/GitHub";
import FacebookIcon from "@mui/icons-material/Facebook";

import { useState } from "react";
// import { Link } from "react-router-dom";

import { useNavigate } from "react-router-dom";
import axios from "axios";

const SignupPage = () => {
  // 이름,이메일,비밀번호 보내기
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  // const [loading, setLoading] = useState(false);

  // 유효성검사
  const [isValidName, setIsValidName] = useState(false);
  const [isValidEmail, setIsValidEmail] = useState(false);
  const [isValidPassword, setIsValidPassword] = useState(false);

  // 회원가입 완료 시 로그인 페이지 이동
  const navigate = useNavigate();

  // 회원가입 데이터 전송
  const signUpSubmit = () => {
    axios
      .post(
        // "https://stackoverflowclone-7b282-default-rtdb.firebaseio.com/members.json",
        "https://5623-61-252-125-198.jp.ngrok.io/members",
        //data
        {
          name,
          email,
          password,
        }
      )
      // 성공시 response
      .then(() => {
        alert("회원가입 성공!");
        navigate("/loginpage");
      })
      // 오류발생시 실행
      .catch((error) => {
        alert("회원가입 실패!");
        console.log(error);
      });
  };

  // try {
  //   const response = await axios
  //     .post(
  //       "https://stackoverflowclone-7b282-default-rtdb.firebaseio.com/members.json",
  //       {
  //         email,
  //         name,
  //         password,
  //       }
  //     )
  //     .then(() => {
  //       window.alert("회원가입이 완료되었습니다.");
  //       navigate("/loginpage");
  //     });
  // } catch (error) {
  //   window.alert("입력 사항을 확인해 주세요.");
  // }

  // const signUpSubmit = (email, name, password) => {
  //   const data = { Email: email, Name: name, Password: password };
  //   Post("https://stackoverflow-2ba22-default-rtdb.firebaseio.com.json", data);
  //   navigate("/loginpage");
  // };

  // name 유효성 검사 - 1글자 이상 사용
  const validationNameCheck = () => {
    if (name.length >= 1) {
      return true;
    } else {
      return false;
    }
  };

  // email 유효성 검사
  const validationEmailCheck = () => {
    const emailRegex =
      // 이메일  정규표현식
      /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
    return emailRegex.test(email);
  };

  // Password 유효성 검사 체크  특문1글자+한글1글자+글자수8자이상  -> 근데 왜 9자 해야만 되는지?
  const validationPasswordCheck = () => {
    const passwordRegex = /^(?=.*[a-zA-Z])((?=.*\d)(?=.*\W)).{8,16}$/;
    return passwordRegex.test(password);
  };

  // 이름
  const onNameHandler = (e) => {
    //창 새로고침 방지
    let targetValue = e.currentTarget.value;
    setName(targetValue);
    if (validationNameCheck(targetValue)) {
      setIsValidName(false);
    } else {
      setIsValidName(true);
    }
  };

  // 이메일
  const onEmailHandler = (e) => {
    let targetValue = e.currentTarget.value;
    setEmail(targetValue);
    if (validationEmailCheck(targetValue)) {
      setIsValidEmail(false);
    } else {
      setIsValidEmail(true);
    }
  };

  // 패스워드
  const onPasswordHandler = (e) => {
    let targetValue = e.currentTarget.value;
    setPassword(targetValue);
    if (validationPasswordCheck(targetValue)) {
      setIsValidPassword(false);
    } else {
      setIsValidPassword(true);
    }
  };

  // 회원가입 기능 구현
  const onSignupHandler = (e) => {
    //창 새로고침 방지
    e.preventDefault();
    let validationName = validationNameCheck(name);
    let validationEmail = validationEmailCheck(email);
    let validationPassword = validationPasswordCheck(password);
    if (validationName && validationEmail && validationPassword) {
      signUpSubmit();
    } else {
      setIsValidName(!validationName);
      setIsValidEmail(!validationEmail);
      setIsValidPassword(!validationPassword);
    }
  };

  return (
    <div className={styles.container}>
      <SignupSidebar />
      <div className={styles["signup-right"]}>
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

        <div className={styles["signup-container"]}>
          <form>
            <div className={styles.input}>
              <div>Display name </div>
              <input
                // id="name"
                // name="name"
                value={name}
                onChange={onNameHandler}
              ></input>
              {/* {nameMsg} */}
              {/* 1글자면 나오는 메세지 */}
              {isValidName && (
                <div className={styles["isvalid-check"]}>
                  Please enter a valid Display name.
                </div>
              )}
              <div>Email</div>
              <input
                // id="email"
                // name="email"
                value={email}
                onChange={onEmailHandler}
              ></input>
              {isValidEmail && (
                <div className={styles["isvalid-check"]}>
                  Please enter a valid email address.
                </div>
              )}
              {/* {emailMsg} */}
              <div>Password</div>
              <input
                // id="password"
                // name="password"
                value={password}
                type="password"
                onChange={onPasswordHandler}
              ></input>
              {isValidPassword && (
                <div className={styles["isvalid-check"]}>
                  Please enter a valid password.
                </div>
              )}
              {/* {passwordMsg} */}
            </div>
            <div className={styles.isvalid}>
              Passwords must contain at least eight characters, including at
              least 1 special letter and 1 number.
            </div>
            <div className={styles["checkbox-input"]}>
              <input type="checkbox"></input>
              Opt-in to receive occasional product updates, user research
              invitations, company announcements, and digests.
            </div>
            <div className={styles["signup-button"]}>
              {/* <Link to="/loginpage"> */}
              <button onClick={onSignupHandler}> Sign up</button>
              {/* </Link> */}
            </div>
            <div className={styles["signup-policy"]}>
              By clicking “Sign up”, you agree to our terms of service, privacy
              policy and cookie policy
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default SignupPage;