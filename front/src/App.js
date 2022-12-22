import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Header from "./components/Header";

import QuestionsPage from "./pages/QuestionsPage";
import QuestionPage from "./pages/QuestionPage";
import LoginPage from "./pages/LoginPage";
import SignupPage from "./pages/SignupPage";

import "./App.css";

const App = () => {
  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/" element={<QuestionsPage />} />
        <Route path="/questionpage" element={<QuestionPage />} />
        <Route path="/loginpage" element={<LoginPage />} />
        <Route path="/signuppage" element={<SignupPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
