import axios from "axios";
import { getToken, removeToken } from "@/utils/auth.js";
const DBAxios = axios.create({
  baseURL: import.meta.env.DB_API_URL ,
  // baseURL: process.env.VITE_APP_API_URL,
  // baseURL: "http://localhost:8080/api", + "/api"
});

DBAxios.interceptors.request.use(
  (config) => {
    let token = getToken();
    if (token) {
    //   config.headers["Access-Control-Allow-Origin"] = "http://localhost:8888"; // 允许跨域请求的来源
    //   config.headers["Access-Control-Allow-Methods"] =
    //     "GET, POST, OPTIONS, PUT, PATCH, DELETE"; // 允许的HTTP方法
    //   config.headers["Access-Control-Allow-Headers"] =
    //     "Content-Type, Authorization, token"; // 允许的HTTP头部信息
      config.headers["token"] = token;
    }
    console.log("拦截token");
    console.log(token);
    console.log("拦截token");
    return config;
  },
  (error) => {
    // 对请求错误做些什么
    return Promise.reject(error);
  }
);

// 添加响应拦截器
DBAxios.interceptors.response.use(
  (response) => {
    // 对响应数据做点什么
    const res = response.data;
    if (res.code && res.code !== 1) {
      // `token` 过期或者账号已在别处登录
      return Promise.reject(response);
    } else {
      return Promise.resolve(res);
    }
  },
  (error) => {
    // 对响应错误做点什么
    return Promise.reject(error);
  }
);

/**
 * 通用请求封装
 * @param config
 */
export const request = (config) => {
  return DBAxios.request(config);
};

/**
 * post请求
 */
export const postRequest = (url, data) => {
  return request({ data, url, method: "post" });
};

/**
 * get请求
 */
export const getRequest = (url, params) => {
  return request({ url, method: "get", params });
};
