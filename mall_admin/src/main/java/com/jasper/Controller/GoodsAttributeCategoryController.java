package com.jasper.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jasper.pojo.GoodsAttributeCategory;
import com.jasper.service.GoodsAttributeCategoryService;
import com.jasper.vo.AttributeCategoryVo;
import com.jasper.vo.GoodsAttributeCategoryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("adminService/attributeCategory")
public class GoodsAttributeCategoryController {

    private final GoodsAttributeCategoryService attributeCategoryService;

    @GetMapping("pageList")
    public Page<GoodsAttributeCategory> getCategoryList(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize
    ){
        return attributeCategoryService.pageList(pageNum,pageSize);
    }

    @GetMapping("getAllAttributeCategory")
    public List<AttributeCategoryVo> getAllAttributeCategory(){
        return attributeCategoryService.getAllAttributeCategory();
    }

    @PostMapping("addAttributeCategory")
    public Boolean  addAttributeCategory(@RequestBody GoodsAttributeCategory goodsAttributeCategory){
        return attributeCategoryService.addAttributeCategory(goodsAttributeCategory);
    }

    @DeleteMapping("deleteAttributeCategory/{id}")
    public Boolean deleteAttributeCategory(
            @PathVariable("id") String id

    ){
        return attributeCategoryService.deleteAttributeCategory(id);
    }

    @PutMapping("updateAttributeCategory")
    public Boolean updateAttributeCategory(@RequestBody GoodsAttributeCategory goodsAttributeCategory){
        return attributeCategoryService.updateById(goodsAttributeCategory);
    }

    @GetMapping("getListWithAttr")
    public List<GoodsAttributeCategoryVO> getListWithAttr(){
        return attributeCategoryService.getListWithAttr();
    }
}
