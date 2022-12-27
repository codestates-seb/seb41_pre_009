import React from "react";
import SignupSidebar from "./SignupSidebar";
import styles from "./SignupPage.module.css";
import GitHubIcon from "@mui/icons-material/GitHub";
import FacebookIcon from "@mui/icons-material/Facebook";

import { useState } from "react";
import { Link } from "react-router-dom";

// 회원가입 창
const SignupPage = () => {
  // 초기값
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  // 오류메세지 상태 저장
  const [nameMsg, setNameMsg] = useState("");
  const [emailMsg, setEmailMsg] = useState("");
  const [passwordMsg, setPasswordMsg] = useState("");

  // 유효성 검사
  const [isName, setIsName] = useState(false);
  const [isEmail, setIsEmail] = useState(false);
  const [isPassword, setIsPassword] = useState(false);

  //password type 변경용 state
  const [passwordType, setPasswordType] = useState({
    type: "password",
    visible: false,
  });
  //password type 변경용 함수 -> ***로 보이게끔 만들어준다.
  const handlePasswordType = (e) => {
    setPasswordType(() => {
      if (!passwordType.visible) {
        return { type: "text", visible: true };
      }
      return { type: "password", visible: false };
    });
  };

  //이름
  const onChangeName = (e) => {
    const currentName = e.target.value;
    setName(currentName);

    if (currentName.length < 2) {
      setNameMsg("Name must be a minimum of 2 characters");
      setIsName(false);
    } else {
      setNameMsg(false);
      setIsName(true);
    }
  };
  //이메일 주소 유효성검사
  const onChangeEmail = (e) => {
    const currentEmail = e.target.value;
    setEmail(currentEmail);
    const emailRegExp =
      /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;

    if (!emailRegExp.test(currentEmail)) {
      setEmailMsg("This is not a valid email address.");
      setIsEmail(false);
    } else {
      setEmailMsg(false);
      setIsEmail(true);
    }
  };

  //패스워드 유효성검사 - 8글자이상
  const onChangePassword = (e) => {
    const currentPassword = e.target.value;
    setPassword(currentPassword);

    if (currentPassword.length >= 8) {
      setPasswordMsg(false);
      setIsPassword(true);
    } else {
      setPasswordMsg(true);
      setIsPassword(false);
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
                id="name"
                name="name"
                value={name}
                onChange={onChangeName}
              ></input>
              {nameMsg}
              {/* 2글자이상 5글자 이하일때 나오는 메세지 */}
              {isName && (
                <div className={styles["isvalid-check"]}>
                  Please enter a valid Display name.
                </div>
              )}
              <div>Email</div>
              <input
                id="email"
                name="email"
                value={email}
                onChange={onChangeEmail}
              ></input>
              {isEmail && (
                <div className={styles["isvalid-check"]}>
                  Please enter a valid email address.
                </div>
              )}
              {emailMsg}
              <div>Password</div>
              <input
                id="password"
                name="password"
                value={password}
                onChange={onChangePassword}
                type={passwordType.type}
              ></input>
              {/* 비밀번호 암호화로 보여줌 **로 표시 */}
              <span onClick={handlePasswordType}>
                {passwordType.visible ? <span></span> : <span></span>}
              </span>
              {isPassword && (
                <div className={styles["isvalid-check"]}>
                  Please enter a valid password.
                </div>
              )}
              {passwordMsg}
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
              <Link to="/loginpage">
                <button>Sign up</button>
              </Link>
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