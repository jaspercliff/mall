package com.jasper.aspect;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jasper.common_utils.R;
import jakarta.annotation.Nonnull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;

/**
 * @author com
 * @date 2023/5/5
 */
@RestControllerAdvice("com")
public class ResponseAdvice implements  ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(@Nonnull MethodParameter returnType,@Nonnull Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                   MethodParameter returnType,
                                  @Nonnull MediaType selectedContentType,
                                  @Nonnull Class selectedConverterType,
                                  @Nonnull ServerHttpRequest request,
                                   ServerHttpResponse response) {
        if (body instanceof R) {
            return body;
        }
        if(body instanceof Page<?> page){
            HashMap<Object, Object> map = new HashMap<>();
            map.put("total",page.getTotal());
            map.put("records",page.getRecords());
            map.put("current",page.getCurrent());
            map.put("size",page.getSize());
            return R.success(map);
        }
        return R.success(body);
    }
}
