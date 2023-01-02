import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Header from "./components/Header";

import QuestionsPage from "./pages/QuestionsPage";
import AskQuestionPage from "./pages/AskQuestionPage";
import LoginPage from "./pages/LoginPage";
import SignupPage from "./pages/Signup/SignupPage";
import LoadingPage from "./pages/LoadingPage";
import MainQuestion from "./pages/MainQuestion";

import "./App.css";

const App = () => {
  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/" element={<QuestionsPage />} />
        <Route path="/askquestionpage" element={<AskQuestionPage />} />
        <Route path="/question/:id" element={<MainQuestion />} />
        <Route path="/loginpage" element={<LoginPage />} />
        <Route path="/signuppage" element={<SignupPage />} />
        <Route path="/loadingpage" element={<LoadingPage />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
