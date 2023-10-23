import axios from 'axios';

const DBAxios = axios.create({
    baseURL:import.meta.env.DB_API_URL,
    // baseURL: process.env.VITE_APP_API_URL,
    // baseURL: "http://localhost:8080/api",
});


DBAxios.interceptors.request.use(
    (config) => {
        // 在发送请求之前
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
    return request({ data, url, method: 'post' });
};

/**
 * get请求
 */
export const getRequest = (url, params) => {
    return request({ url, method: 'get', params });
};
