package com.jasper.handler;

import com.jasper.common_utils.R;
import com.jasper.config.CustomException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author com
 * @date 2023/5/7
 */
//抛出的异常不够具体，只是简单地把错误信息放到了 Exception 中
//        抛出异常后，controller 不能具体地根据异常做出反馈
// 而统一拦截异常的目的一个是为了可以与前面定义下来的统一包装返回结构能对应上，
// 另一个是我们希望无论系统发生什么异常，Http 的状态码都要是 200 ，尽可能由业务来区分系统的异常。
@RestControllerAdvice
public class GlobalExceptionHandler {
//    全局异常处理
    @ExceptionHandler(Exception.class)
//    @ResponseBody
    public R<?> error(Exception e){
        e.printStackTrace();
        return R.fail("未知异常，请联系管理员");
    }
//特定异常
    @ExceptionHandler(ArithmeticException.class)
//    @ResponseBody
    public R<?> error1(Exception e){
        e.printStackTrace();
        return R.fail("执行了特定异常处理");
    }
//自定义异常

    @ExceptionHandler(CustomException.class)
//    @ResponseBody
    public R<?> error2(CustomException e){
        System.out.println(e.getMessage());
        return R.fail(e.getCode(),e.getMessage());
    }


}
