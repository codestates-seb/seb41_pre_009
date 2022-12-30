// 키로 부터 데이터 읽기
// localStorage.getItem("key");
export const getLocalStorage = () => {
  return JSON.parse(localStorage.getItem("user"));
};

// 키에 데이터 쓰기
// localStorage.setItem("key", value);
export const addLocalStorage = (payload) => {
  return localStorage.setItem("user", JSON.stringify(payload));
};

// 키의 데이터 삭제
// localStorage.removeItem("key");
export const removeLocalStroage = () => {
  return localStorage.removeItem("user");
};
