import {request} from "@/utils/http";
export function confirmOrder(ids:any){
    return request({
        url:`/webService/order/confirmOrder/${ids}`,
        method:"get",
    })
}
export function addOrder(order:any){
    return request({
        url:`/webService/order/addOrder`,
        method:"post",
        data:order
    })
}
export function getOrderDetail(orderId:any){
    return request({
        url:`/webService/order/getOrderDetail/${orderId}`,
        method:"get",
    })
}
export function getOrderById(orderId:any){
    return request({
        url:`/webService/order/getOrderById/${orderId}`,
        method:"get",
    })
}
export function getAllOrder(){
    return request({
        url:`/webService/order/getAllOrder`,
        method:"get",
    })
}
export function getOrderByStatus(status:any){
    return request({
        url:`/webService/order/getOrderByStatus/${status}`,
        method:"get",
    })
}
export function buy(){
    return request({
        url:`/webService/order/buy`,
        method:"put",
    })
}
