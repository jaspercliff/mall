const message = window.$message
export function validateUsername(value:string) {
    if (!value || value.length == 0) {
        message?.warning('姓名不能为空')
        return false
    }else {
        return true
    }
}
export function validateEmail(value:string) {
    const emailRegex: RegExp = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(value)) {
        message?.warning("邮箱格式不正确")
        return false
    }
    return true
}
export function validatePassword(val1:string, val2:string) {
    if(val1.trim().length!=0 &&val2.trim().length!=0){
        if (val1 !== val2 ) {
            message?.warning("俩次密码输入不一致")
            return false
        }
    }
    return true
}

export function validateChecked(val:boolean) {
    if (val === false) {
        message?.warning("please read the statement")
        return false
    }
    return true
}
