import {request} from "@/utils/http";
export function pageList(parentId:number,pageNum:number,pageSize:number){
    return request({
        url:`/adminService/category/pageList/${parentId}`,
        method:"get",
        params: {
            pageNum:pageNum,
            pageSize:pageSize
        }
    })
}
export function changeNavStatus(goodsBrand:Object){
    return request(
        {
            url:`/adminService/category/changeNavStatus`,
            method: 'put',
            data: goodsBrand
        }
    )
}
export function deleteCategory(id:string){
    return request(
        {
            url:`/adminService/category/deleteCategory/${id}`,
            method: 'delete',
        }
    )
}
export function getParentCategory(){
    return request(
        {
            url:`/adminService/category/getParentCategory`,
            method: 'get',
        }
    )
}
export function addCategory(category:Object){
    return request(
        {
            url:`/adminService/category/addCategory`,
            method: 'post',
            data: category
        }
    )
}
export function updateCategory(category:Object){
    return request(
        {
            url:`/adminService/category/updateCategory`,
            method: 'post',
            data: category
        }
    )
}
export function getCategoryCascade(){
    return request(
        {
            url:`adminService/category/getCategoryCascade`,
            method: 'get',
        }
    )
}
