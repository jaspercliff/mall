import {request} from "@/utils/http";

export function login(data:object){
    return request({
        url:`/webService/admin/login`,
        method:"post",
        data
    })
}
