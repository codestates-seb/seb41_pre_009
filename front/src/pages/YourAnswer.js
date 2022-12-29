import React from "react";
import MDEditor from "@uiw/react-md-editor";

import styles from "./YourAnswer.module.css";

//MainQuestion - Your Answer 부분 마크다운
const YourAnswer = (props) => {

  const [value, setValue] = React.useState("");

  return (
    <div className={styles["YourAnswer-container"]}>
      {/* <MDEditor value={value} onChange={setValue} />
      <MDEditor.Markdown source={value} style={{ whiteSpace: "pre-wrap" }} /> */}
      <h3>YourAnswer</h3>
      <div className={styles.Editor}>
        <MDEditor height={200} value={value} onChange={setValue} />
        <div data-color-mode="light"></div>
        <MDEditor.Markdown
          style={{ padding: 15 }}
          source={value}
          linkTarget="_blank"
        // previewOptions={{
        //   linkTarget: "_blank"
        // }}
        />
      </div>
      <div className={styles["YourAnswer-button"]}>
        <button onClick={() => props.handleAnswerSubmit(value)}>Post Your Answer</button>
      </div>
    </div>
  );
};

export default YourAnswer;