package com.phenix.controller;

import com.phenix.entity.ProductCategory;
import com.phenix.entity.ProductInfo;
import com.phenix.service.CategoryService;
import com.phenix.service.ProductService;
import com.phenix.util.ResultUtils;
import com.phenix.vo.ProductInfoVO;
import com.phenix.vo.ProductVO;
import com.phenix.vo.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "/buyer/product", description = "商品")
@RestController
@RequestMapping("/buyer/product")
@Slf4j
public class BuyerProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public BuyerProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Operation(description = "商品列表", summary = "列出所有已上线的商品")
    @GetMapping("/list")
    public ResultVO list() {
        // 查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        // 查询类目（一次性查完）
        List<Integer> categoryTypeList = productInfoList.stream()
                                                        .map(ProductInfo::getCategoryType)
                                                        .distinct()
                                                        .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        // 拼装数据
        List<ProductVO<?>> data = concatListData(productInfoList, productCategoryList);
        return ResultUtils.success(data);
    }

    /**
     * 将商品信息列表和商品类目列表拼装在一起
     *
     * @param productInfoList     品信息列表
     * @param productCategoryList 商品类目列表
     * @return 商品表
     */
    private List<ProductVO<?>> concatListData(List<ProductInfo> productInfoList,
                                           List<ProductCategory> productCategoryList) {
        return productCategoryList.stream()
                                  .map(category -> new ProductVO<List<ProductInfoVO>>().of(category))
                                  .peek(productVO -> {
                                      /* 将食物列表赋值给ProductVO */
                                      List<ProductInfoVO> foodList =
                                              productInfoList.stream()
                                                             .filter(productInfo ->
                                                                     productInfo.getCategoryType()
                                                                                .equals(productVO.getCategoryType()))
                                                             .map(productInfo ->
                                                                     new ProductInfoVO().of(productInfo))
                                                             .collect(Collectors.toList());
                                      productVO.setFoodList(foodList);
                                  })
                                  .collect(Collectors.toList());
    }

}
