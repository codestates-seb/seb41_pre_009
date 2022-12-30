import { createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

//비동기 작업을 처리하기 위해서 createAsyncThunk
export const loginAction = createAsyncThunk(
  "loginSlice/loginAction",
  async (payload) => {
    const response = await axios(
      "https://463b-61-252-125-198.jp.ngrok.io/auth/login",
      {
        method: "post",
        headers: {
          "Content-Type": "application/json",
        },
        data: payload,
      }
    );

    // const getProfile = await axios(
    //   "https://463b-61-252-125-198.jp.ngrok.io/users/profile",
    //   {
    //     headers: {
    //       authorization: response.headers.authorization,
    //     },
    //   }
    // );

    // return { ...getProfile.data, token: response.headers.authorization };
    return { token: response.headers.authorization };
  }
);
