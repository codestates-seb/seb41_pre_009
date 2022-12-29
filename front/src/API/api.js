import axios from "axios";

export const getLocal = () => {
    return JSON.parse(localStorage.getItem('user'));
};

export const addLocal = (payload) => {
    return localStorage.setItem('user', JSON.stringify(payload));
};

export const removeLocal = () => {
    return localStorage.removeItem('user');
};


export const Post = (url, data) => {
    axios(url, {
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            // Authorization: getLocalStorage().token
        },
        data,
    }).catch((error) => console.log('Error', error.message));
};

export const Patch = (url, data) => {
    axios(url, {
      method: 'patch',
      headers: {
        'Content-Type': 'application/json',
        // Authorization: getLocalStorage().token
      },
      data,
    }).catch((error) => console.log('Error', error.message));
  };
  
  export const fetchDelete = (url) => {
    axios(url, {
      method: 'delete',
      headers: {
        // Authorization: getLocalStorage().token
      },
    }).catch((error) => console.log('Error', error.message));
  };

