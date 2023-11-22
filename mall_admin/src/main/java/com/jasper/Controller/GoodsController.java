package com.jasper.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jasper.common_utils.OssUploadUtil;
import com.jasper.common_utils.R;
import com.jasper.dto.GoodsDTO;
import com.jasper.dto.GoodsSearchDTO;
import com.jasper.pojo.Goods;
import com.jasper.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("adminService/goods")
@RequiredArgsConstructor
public class GoodsController {

    private final GoodsService goodsService;

    @PostMapping("uploadGoodPicture")
    private R<String> uploadGoodPicture(@RequestPart(value = "file") MultipartFile image){
        String pictureUrl = OssUploadUtil.upload(image);
        return R.success(pictureUrl);
    }
    @GetMapping("pageGoodsList")
    private Page<Goods> pageGoodsList(GoodsSearchDTO goodsSearchDTO){
        return goodsService.pageGoodsList(goodsSearchDTO);
    }

    @PutMapping("changeStatusAll")
    private Boolean changeStatusAll(@RequestBody Goods goods){
        return goodsService.updateById(goods);
    }

    @DeleteMapping("deleteGoods/{id}")
    private Boolean deleteGoods(@PathVariable Long id){
        return goodsService.deleteGoods(id);
    }

    @PostMapping("addGoods")
    private R<?> addGoods(@RequestBody GoodsDTO goodsDTO){
        return goodsService.addGoods(goodsDTO);
    }

    @GetMapping("/getGoodById/{id}")
    private GoodsDTO getGoodById(@PathVariable("id") String id){
        return goodsService.getGoodById(id);
    }
}
