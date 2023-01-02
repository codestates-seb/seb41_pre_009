import React, { useState } from "react";
import { Link } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { loginActions } from "../redux/loginreducer";
// import { loginAction } from "../redux/actions";
// import { searchActions } from "../redux/searchSlice";

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

  const isLogin = useSelector((state) => state.isLogin);

  const logoutHandler = () => {
    dispatch(loginActions.logout());
  };

  const [searchVal, setSearchVal] = useState("");

  // 입력창이 변경 될 때 마다 searchVal에 그 값 할당
  const onChangeSearchValue = (event) => {
    setSearchVal(event.target.value);
  };

  const onFilterSearch = (event) => {
    event.preventDefault();
    // dispatch(searchActions.search(searchVal));
  };

  return (
    <div className={styles["header-main"]}>
      <div className={styles["header-left"]}>
        <div className={styles["header-left-container"]}>
          <Link to="/">
            <img
              src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/02/Stack_Overflow_logo.svg/1600px-Stack_Overflow_logo.svg.png"
              alt="logo"
              className={styles.HomeLogo}
            />
          </Link>
          <h3>About</h3>
          <h3>Products</h3>
          <h3>For Teams</h3>
        </div>
        <div className={styles["header-middle"]}>
          <form
            className={styles["header-search-container"]}
            onSubmit={onFilterSearch}
          >
            <SearchIcon />
            <input
              type="text"
              placeholder=" Search..."
              onChange={onChangeSearchValue}
              value={searchVal}
            />
          </form>
        </div>
        <div className={styles["header-right"]}>
          <div className={styles["header-right-container"]}>
            {isLogin && (
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
            {!isLogin && (
              <div className={styles["header-right-container-button"]}>
                <Link to="/loginpage">Log in</Link>
                <div className={styles["header-right-container-buttons"]}>
                  <Link to="/signuppage">Sign up</Link>
                </div>
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

export default Header;
