package com.jasper.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author com
 * @date 2023/5/5
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createDate", LocalDateTime.now(),metaObject);
        this.setFieldValByName("modifyDate", LocalDateTime.now(),metaObject);
        this.setFieldValByName("createTime", LocalDateTime.now(),metaObject);
        this.setFieldValByName("modifyTime", LocalDateTime.now(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("modifyDate", LocalDateTime.now(),metaObject);
        this.setFieldValByName("modifyTime", LocalDateTime.now(),metaObject);
    }
}
