import {request} from "@/utils/http";
export function addCart(data:object,username:any){
    return request({
        url:`/webService/cart/addCart/${username}`,
        method:"post",
        data:data
    })
}

export function getListByIds(ids:any){
    return request({
        url:`/webService/cart/list/${ids}`,
        method:"get",
    })
}
export function getAllCart(){
    return request({
        url:`/webService/cart/list`,
        method:"get",
    })
}
export function updateQuantity(id:string,value:string){
    return request({
        url:`/webService/cart/updateQuantity/${id}/${value}`,
        method:"put",
    })
}
export function deleteCart(id:string){
    return request({
        url:`/webService/cart/deleteCart/${id}`,
        method:"delete",
    })
}
