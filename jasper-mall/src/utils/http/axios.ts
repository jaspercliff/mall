import axios from "axios";
import {getToken} from "@/utils/auth/auth";
import router from "@/router";
const axiosInstance = axios.create({
    // Vite 在一个特殊的 import.meta.env 对象上暴露环境变量。这里有一些在所有情况下都可以使用的内建变量
    baseURL:import.meta.env.VITE_BASE_API,
    timeout: 5000
})
// @ts-ignore
const message = window.$message
axiosInstance.interceptors.request.use(
    config =>{
        if(getToken()){
            console.log('Bearer '+ getToken())
            config.headers['Authorization'] = 'Bearer '+ getToken();
        }
        return config;
    },
    error => {
        console.log(error)
        return Promise.reject(error)
    }
)
axiosInstance.interceptors.response.use(response => {
        const res = response.data;
        if(res.code!==2001){
            message?.warning(res.message)
        }
        return res;
    },
    error => {
        if (error.response.status === 403){
            message?.error("请您先登录")
            router.push("/signin")
        }
        return Promise.reject(error)
    }
)
export { axiosInstance as request };
