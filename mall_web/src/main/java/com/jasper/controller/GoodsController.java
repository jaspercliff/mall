package com.jasper.controller;

import com.jasper.pojo.Goods;
import com.jasper.service.GoodsService;
import com.jasper.vo.GoodDetailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("webService/goods")
@RequiredArgsConstructor
public class GoodsController {
    private final GoodsService goodsService;

    @GetMapping("getRecommend")
    private List<Goods> getRecommend(){
        return goodsService.getRecommend();
    }

    @GetMapping("getGoodDetail/{id}")
    private GoodDetailVo getGoodDetail(@PathVariable("id")String id){
        return goodsService.getGoodDetail(id);
    }

}
