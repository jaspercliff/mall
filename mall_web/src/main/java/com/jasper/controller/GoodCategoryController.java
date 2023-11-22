package com.jasper.controller;

import com.jasper.common_utils.R;
import com.jasper.service.GoodsCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author com
 * 6/27/2023  10:16 AM
 */
@RestController
@RequestMapping("/webService/goods")
@RequiredArgsConstructor
public class GoodCategoryController {

    private final GoodsCategoryService goodsCategoryService;
    @GetMapping("getOneCategory")
    public R<?> getOneCategory(){
        return  goodsCategoryService.getOneCategory();
    }

    @GetMapping("getTwoCategory/{oneCategoryId}")
    public R<?> getTwoCategory(@PathVariable String oneCategoryId){
        return  goodsCategoryService.getTwoCategory(oneCategoryId);
    }

    @GetMapping("getHotCategory")
    public R<?> getHotCategory(){
        return goodsCategoryService.getHotCategory();
    }
}
