import {getLocal, getLocalExpire, removeLocal, setLocal} from "@/utils/storage";
import {request} from "@/utils/http";

const TOKEN_CODE = "ect_code"
const DURATION = 1 * 60 * 60*24
export function getToken(){
    return getLocal(TOKEN_CODE)
}
/**
 * 默认为一天 不传duration
 * @param token
 */
export function setToken(token:string){
    return setLocal(TOKEN_CODE,token,DURATION)
}
export function removeToken(){
    return removeLocal(TOKEN_CODE)
}

export async function refreshAccessToken(){
    const expire:number | null = getLocalExpire(TOKEN_CODE)
    // * token没有过期时间或者token离过期时间超过20分钟则不执行刷新
    if(!expire || expire - new Date().getTime() > 1000*60*20)
        return
    const res: any = await request.get("/service/refreshToken")
    if (res.code === 2001) {
        setToken(res.data.token)
    }
}




