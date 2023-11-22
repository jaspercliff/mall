import {request} from "@/utils/http";

export function getAllBrand(){
    return request({
        url:`/adminService/admin/getAllBrand`,
        method:"get"
    })
}

export function changeShowStatus(goodsBrand:Object){
    return request(
        {
            url:`/adminService/admin/changeShowStatus`,
            method: 'put',
            data: goodsBrand
        }
    )
}

export function updateBrand(goodsBrand:Object){
    return request(
        {
            url:`/adminService/admin/updateBrand`,
            method: 'put',
            data: goodsBrand
        }
    )
}
export function getBrandByName(brandName:string){
    return request(
        {
            url:`/adminService/admin/getBrandByName/${brandName}`,
            method: 'get',
        }
    )
}
export function addBrand(brand:Object){
    return request(
        {
            url:`/adminService/admin/addBrand`,
            method: 'post',
            data: brand
        }
    )
}
export function deleteBrand(id:string){
    return request(
        {
            url:`/adminService/admin/deleteBrand/${id}`,
            method: 'delete',
        }
    )
}

