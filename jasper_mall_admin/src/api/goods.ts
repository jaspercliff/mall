import {request} from "@/utils/http";
export function pageGoodsList(params:any){
    return request({
        url:`/adminService/goods/pageGoodsList`,
        method:"get",
        params: params
    })
}
export function changeStatusAll(goods:any){
    return request({
        url:`/adminService/goods/changeStatusAll`,
        method:"put",
        data:goods
    })
}
export function deleteGoods(id:string){
    return request({
        url:`/adminService/goods/deleteGoods/${id}`,
        method:"delete",
    })
}
export function uploadGoodPicture(file:object){
    return request({
        url:`/adminService/goods/uploadGoodPicture`,
        method:"post",
        headers: {
            'Content-Type': 'multipart/form-data',
        },
        data:file
    })
}
export function addGood(goods:object){
    return request({
        url:`/adminService/goods/addGoods`,
        method:"post",
        data:goods
    })
}
export function getGoodById(id:string){
    return request({
        url:`/adminService/goods/getGoodById/${id}`,
        method:"get",
    })
}
