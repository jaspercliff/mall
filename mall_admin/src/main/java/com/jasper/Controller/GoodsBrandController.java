package com.jasper.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jasper.pojo.GoodsBrand;
import com.jasper.service.GoodsBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("adminService/admin")
public class GoodsBrandController {

    private final GoodsBrandService goodsBrandService;

//    分类筛选属性
    @GetMapping("getAllBrand")
    public List<GoodsBrand> getAllBrand(){
        return goodsBrandService.list();
    }

    @PutMapping("changeShowStatus")
    public Integer changeShowStatus(@RequestBody GoodsBrand goodsBrand){
        return goodsBrandService.getBaseMapper().updateById(goodsBrand);
    }
    @PutMapping("updateBrand")
    public Boolean updateBrand(@RequestBody GoodsBrand goodsBrand){
        return goodsBrandService.updateById(goodsBrand);
    }

    @GetMapping("getBrandByName/{brandName}")
    public GoodsBrand getBrandByName(@PathVariable("brandName") String brandName){
        return goodsBrandService.getOne(new LambdaQueryWrapper<GoodsBrand>().eq(GoodsBrand::getName, brandName));
    }

    @PostMapping("addBrand")
    public Boolean addBrand(@RequestBody GoodsBrand goodsBrand){
        return goodsBrandService.save(goodsBrand);
    }

    @DeleteMapping("deleteBrand/{id}")
    public Boolean deleteBrand(@PathVariable("id") String id){
        return goodsBrandService.removeById(id);
    }


}
