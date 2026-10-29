package com.product.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.dto.in.DtoProductIn;
import com.product.api.dto.out.DtoProductListOut;
import com.product.api.dto.out.DtoProductOut;
import com.product.api.entity.ProductImage;

public interface SvcProduct {

	public List<DtoProductListOut> getProducts();
	public DtoProductOut getProduct(Integer id);
	public String createProduct(DtoProductIn in);
	public String updateProduct(Integer id, DtoProductIn in);
	public String enableProduct(Integer id);
	public String disableProduct(Integer id);

}
