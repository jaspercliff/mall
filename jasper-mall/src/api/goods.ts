import {request} from "@/utils/http";
export function getRecommend(){
    return request({
        url:`/webService/goods/getRecommend`,
        method:"get",
    })
}
export function getGoodDetail(id:any){
    return request({
        url:`/webService/goods/getGoodDetail/${id}`,
        method:"get",
    })
}
