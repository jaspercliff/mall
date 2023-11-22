import {request} from "@/utils/http";

export function pageAttributeCategoryList(pageNum:number,pageSize:number){
    return request({
        url:`adminService/attributeCategory/pageList`,
        method:"get",
        params: {
            pageNum:pageNum,
            pageSize:pageSize
        }
    })
}
export function addAttributeCategory(attributeCategory:object){
    return request({
        url:`adminService/attributeCategory/addAttributeCategory`,
        method:"post",
        data: attributeCategory
    })
}
export function deleteAttributeCategory(id:string){
    return request({
        url:`adminService/attributeCategory/deleteAttributeCategory/${id}`,
        method:"delete",
    })
}

export function updateAttributeCategory(attributeCategory:object){
    return request({
        url:`adminService/attributeCategory/updateAttributeCategory`,
        method: "put",
        data: attributeCategory
    })
}
export function getAllAttributeCategory(){
    return request({
        url:`adminService/attributeCategory/getAllAttributeCategory`,
        method: "get",
    })
}


export function getListWithAttr(){
    return request({
        url:`adminService/attributeCategory/getListWithAttr`,
        method: "get",
    })
}
