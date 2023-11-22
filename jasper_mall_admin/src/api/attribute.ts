import {request} from "@/utils/http";

export function pageAttributeList(cid:any,type:any,pageNum:number,pageSize:number){
    return request({
        url:`adminService/attribute/pageList/${cid}`,
        method:"get",
        params: {
            pageNum:pageNum,
            pageSize:pageSize,
            type:type
        }
    })
}
export function addAttribute(attribute:object){
    return request({
        url:`adminService/attribute/addAttribute`,
        method:"post",
        data: attribute
    })
}
export function deleteAttribute(id:string){
    return request({
        url:`adminService/attribute/deleteAttribute/${id}`,
        method:"delete"
    })
}
export function updateAttribute(attribute:object){
    return request({
        url:`adminService/attribute/updateAttribute`,
        method:"put",
        data: attribute
    })
}
export function getRelationAttrInfoByCid(cid:string){
    return request({
        url:`adminService/attribute/getRelationAttrInfoByCid/${cid}`,
        method:"get",
    })
}
export function getRelationAttrInfoByAttrCateId(acid:string){
    return request({
        url:`adminService/attribute/getRelationAttrInfoByAttrCateId/${acid}`,
        method:"get",
    })
}
