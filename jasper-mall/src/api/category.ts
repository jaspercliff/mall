import {request} from "@/utils/http";
export function getOneCategory(){
    return request({
        url:`/webService/goods/getOneCategory`,
        method:"get",
    })
}
export function getTwoCategory(id:string){
    return request({
        url:`/webService/goods/getTwoCategory/${id}`,
        method:"get",
    })
}
export function getHotCategory(){
    return request({
        url:`/webService/goods/getHotCategory`,
        method:"get",
    })
}
