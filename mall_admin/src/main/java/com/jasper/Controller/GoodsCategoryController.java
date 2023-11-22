package com.jasper.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jasper.common_utils.R;
import com.jasper.dto.GoodsCategoryDTO;
import com.jasper.pojo.GoodsCategory;
import com.jasper.service.GoodsCategoryService;
import com.jasper.vo.GoodsCategoryVo;
import com.jasper.vo.ParentCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("adminService/category")
@RequiredArgsConstructor
public class GoodsCategoryController {

    private final GoodsCategoryService goodsCategoryService;
    @GetMapping("pageList/{parentId}")
    public Page<GoodsCategory> getCategoryList(
            @PathVariable("parentId") String parentLevel,
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize
    ){
        return goodsCategoryService.pageList(parentLevel,pageNum,pageSize);
    }

    @PutMapping("changeNavStatus")
    public Boolean changeNavStatus(@RequestBody GoodsCategory goodsCategory){
        return goodsCategoryService.updateById(goodsCategory);
    }

    @DeleteMapping("deleteCategory/{id}")
    public Boolean deleteCategory(@PathVariable String id){
        return goodsCategoryService.deleteCategory(id);
    }

    @GetMapping("getParentCategory")
    public List<ParentCategory> getParentCategory(){
        return goodsCategoryService.getParentCategory();
    }

    @PostMapping("addCategory")
    public R<Boolean> addCategory(@RequestBody GoodsCategoryDTO goodsCategoryDTO){
        return goodsCategoryService.addCategory(goodsCategoryDTO);
    }

    @PostMapping("updateCategory")
    public R<Boolean> updateCategory(@RequestBody GoodsCategoryDTO goodsCategoryDTO){
        return goodsCategoryService.updateCategory(goodsCategoryDTO);
    }

    /**
     * 获取商品时的级联选择
     * @return
     */
    @GetMapping("getCategoryCascade")
    public R<List<GoodsCategoryVo>> getCascade(){
        return goodsCategoryService.getCategoryCascade();
    }

}
