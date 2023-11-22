package com.jasper.vo;


import java.util.List;

public record GoodsCategoryVo(Long id, String name, List<GoodsCategoryVo> list) {
}
