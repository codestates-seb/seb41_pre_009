//configureStore는 여러개 reducer울 하나로 합칠 수 있다.
import { configureStore } from "@reduxjs/toolkit";

import authReducer from "./auth";

const store = configureStore({
  reducer: { auth: authReducer },
});

export default store;
