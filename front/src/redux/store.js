//configureStore는 여러개 reducer울 하나로 합칠 수 있다.
import { configureStore } from "@reduxjs/toolkit";

import authReducer from "./auth";
import searchReducer from "./search";
import renderReducer from "./render";

const store = configureStore({
    reducer: { 
        auth: authReducer,
        search: searchReducer,
        render: renderReducer
    }
});

export default store;
