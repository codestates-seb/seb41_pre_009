import { createSlice } from "@reduxjs/toolkit";

const initialSearchState = {
    searchValue: ""
};

const searchSlice = createSlice({
    name: 'searching',
    initialState: initialSearchState,
    reducers: {
        search: (state, action) => {
            state.searchValue = action.payload;
        }
    }
});

export const searchActions = searchSlice.actions;

export default searchSlice.reducer;