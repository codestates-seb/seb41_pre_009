import React from "react";
import { Link } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";

import { authActions } from "../redux/auth";

import styles from "./Header.module.css";
import { Avatar } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";
import InboxIcon from "@mui/icons-material/Inbox";
// import HelpIcon from "@mui/icons-material/Help";
// import EmojiEventsIcon from "@mui/icons-material/EmojiEvents";
// import AcUnitIcon from "@mui/icons-material/AcUnit";
import TableRowsIcon from "@mui/icons-material/TableRows";

// import "./App.css";

function Header() {
  const dispatch = useDispatch();

  const isAuth = useSelector((state) => state.auth.isAuthenticated);

  const logoutHandler = () => {
    dispatch(authActions.logout());
  };

  return (
    <div className={styles.header}>
      <div className={styles["header-left"]}>
        <div className={styles["header-left-container"]}>
          <Link to="/">
            <img
              src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/02/Stack_Overflow_logo.svg/1600px-Stack_Overflow_logo.svg.png"
              alt="logo"
              className={styles.HomeLogo}
            />
          </Link>
          <h3>Products</h3>
        </div>
        <div className={styles["header-middle"]}>
          <div className={styles["header-search-container"]}>
            <SearchIcon />
            <input type="text" placeholder=" Searh..." />
          </div>
        </div>
        <div className={styles["header-right"]}>
          <div className={styles["header-right-container"]}>
            {isAuth && (
              <div>
                <Avatar />
                <InboxIcon />
                {/* <EmojiEventsIcon />
                <HelpIcon />
                <AcUnitIcon /> */}
                <TableRowsIcon />
                <button onClick={logoutHandler}>로그아웃</button>
              </div>
            )}
            {!isAuth && (
              <div>
                <Link to="/loginpage">로그인</Link>
                <Link to="/signuppage">회원가입</Link>
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

export default Header;
