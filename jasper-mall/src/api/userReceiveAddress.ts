import {request} from "@/utils/http";
export function addUserAddress(data:object){
    return request({
        url:`/webService/userAddress/addAddress`,
        method:"post",
        data:data
    })
}
export function deleteAddress(id:string){
    return request({
        url:`/webService/userAddress/deleteAddress/{id}`,
        method:"delete",
    })
}
