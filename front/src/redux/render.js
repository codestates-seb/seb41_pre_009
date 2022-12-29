import { createSlice } from "@reduxjs/toolkit";

const initialRenderState = {
    render: false
};

const renderSlice = createSlice({
    name: 'rendering',
    initialState: initialRenderState,
    reducers: {
        rendering(state) {
            state.render = !state.render;
        },
    }
});

export const renderActions = renderSlice.actions;

export default renderSlice.reducer;