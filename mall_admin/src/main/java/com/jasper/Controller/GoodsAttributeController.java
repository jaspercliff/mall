package com.jasper.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jasper.common_utils.R;
import com.jasper.pojo.GoodsAttribute;
import com.jasper.service.GoodsAttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("adminService/attribute")
public class GoodsAttributeController {
    private final GoodsAttributeService goodsAttributeService;


    @GetMapping("pageList/{cid}")
    public Page<GoodsAttribute> pageList(
            @PathVariable Long cid,
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
            @RequestParam(value = "type",defaultValue = "0") Integer type
    ){
        return goodsAttributeService.pageList(cid,pageNum,pageSize,type);
    }

    @PostMapping("addAttribute")
    public Boolean addAttribute(@RequestBody GoodsAttribute goodsAttribute){
        return goodsAttributeService.addAttribute(goodsAttribute);
    }

    @DeleteMapping("deleteAttribute/{id}")
    public Boolean deleteAttribute(@PathVariable String id){
        return goodsAttributeService.deleteAttribute(id);
    }

    @PutMapping("updateAttribute")
    public Boolean updateAttribute(@RequestBody GoodsAttribute goodsAttribute){
        return goodsAttributeService.updateById(goodsAttribute);
    }

    @RequestMapping("/getRelationAttrInfoByCid/{cid}")
    public R<?> getRelationAttrInfoByCid(@PathVariable("cid") Long cid){
        return goodsAttributeService.getRelationAttrInfoByCid(cid);
    }


    @RequestMapping("/getRelationAttrInfoByAttrCateId/{acid}")
    public R<?> getRelationAttrInfoByAttrCateId(@PathVariable("acid") Long acid){
        return goodsAttributeService.getRelationAttrInfoByAttrCateId(acid);
    }
}
