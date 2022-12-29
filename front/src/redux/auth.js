import { createSlice } from "@reduxjs/toolkit";

//초기값 설정
const initialAuthState = {
  isAuthenticated: false,
};

const authSlice = createSlice({
  name: "authentication",
  initialState: initialAuthState,
  //
  reducers: {
    //변하는값 - Login
    login(state) {
      state.isAuthenticated = true;
    },
    logout(state) {
      state.isAuthenticated = false;
    },
  },
});

//action 보내는 것 - action이 필요한 컴포넌트로 보내면 된다.
export const authActions = authSlice.actions;

export default authSlice.reducer;
