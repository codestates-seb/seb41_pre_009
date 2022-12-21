import React from "react";
import styles from "./Header.module.css";
import { Avatar } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";
import InboxIcon from "@mui/icons-material/Inbox";
import HelpIcon from "@mui/icons-material/Help";
import EmojiEventsIcon from "@mui/icons-material/EmojiEvents";
import AcUnitIcon from "@mui/icons-material/AcUnit";
import TableRowsIcon from "@mui/icons-material/TableRows";

// import "./App.css";

function Header() {
  return (
    <div className={styles.header}>
      <div className={styles["header-left"]}>
        <div className={styles["header-left-container"]}>
          <img
            src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/02/Stack_Overflow_logo.svg/1600px-Stack_Overflow_logo.svg.png"
            alt="logo"
          />
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
            <Avatar />
            <InboxIcon />
            <EmojiEventsIcon />
            <HelpIcon />
            <AcUnitIcon />
            <TableRowsIcon />
          </div>
        </div>
      </div>
    </div>
  );
}

export default Header;
