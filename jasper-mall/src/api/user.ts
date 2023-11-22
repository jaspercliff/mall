import {request} from "@/utils/http";
export function register(data:object,emailcode:string){
    return request({
        url:`/webService/user/register/${emailcode}`,
        method:"post",
        data
    })
}
export function getUserByUsername(username:string){
    return request({
        url:`/webService/user/getUserByUsername/${username}`,
        method:"get",
    })
}
export function getUserByEmail(email:string){
    return request({
        url:`/webService/user/getUserByEmail/${email}`,
        method:"get",
    })
}
export function getCode(email:string){
    return request({
        url:`/webService/user/code/${email}`,
        method:"get",
    })
}

export function login(data:object){
    return request({
        url:`/webService/user/login`,
        method:"post",
        data
    })
}
