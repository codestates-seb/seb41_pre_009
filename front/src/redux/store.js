import { configureStore } from '@reduxjs/toolkit';

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