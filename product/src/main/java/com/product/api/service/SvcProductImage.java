package com.product.api.service;

import java.util.List;

import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.ProductImage;

public interface SvcProductImage {
    List<ProductImage> getProducImages(Integer productId);
    String createProductImage(DtoProductImageIn in);
    String deleteProductImage(Integer productId, Integer productImageId);
}
