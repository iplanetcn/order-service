package com.phenix.service;

import com.phenix.dto.CartDTO;
import com.phenix.entity.ProductInfo;
import com.phenix.enums.ProductStatus;
import com.phenix.enums.Result;
import com.phenix.exception.SellException;
import com.phenix.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductInfoRepository productInfoRepository;

    @Autowired
    public ProductServiceImpl(ProductInfoRepository productInfoRepository) {
        this.productInfoRepository = productInfoRepository;
    }

    @Override
    public ProductInfo findProductId(String productId) {
        return productInfoRepository.findById(productId).orElse(null);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatus.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = findProductId(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(Result.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStack() + cartDTO.getProductQuantity();
            productInfo.setProductStack(result);
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = findProductId(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(Result.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStack() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(Result.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStack(result);
            productInfoRepository.save(productInfo);
        }
    }
}
