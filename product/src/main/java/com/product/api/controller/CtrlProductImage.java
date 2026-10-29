package com.product.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.ProductImage;
import com.product.api.service.SvcProductImage;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping("/product")
public class CtrlProductImage {

    @Autowired 
    SvcProductImage svc;

    CtrlProductImage (SvcProductImage svc){
        this.svc = svc;
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<List<ProductImage>> getProductImages(@PathVariable("id") Integer id){
        return ResponseEntity.ok(svc.getProducImages(id));
    }

    @PostMapping ("/{id}/image")
    public ResponseEntity<String> createProductImage (@PathVariable("id") Integer id, @Valid @RequestBody DtoProductImageIn in){
        in.setProductId(id);
        return ResponseEntity.ok(svc.createProductImage(in));
    }

    @DeleteMapping ("/{id}/image/{product-image-id}") 
    public ResponseEntity<String> deleteProducImage (@PathVariable("id") Integer id, @PathVariable ("product-image-id") Integer imageId){
        return ResponseEntity.ok(svc.deleteProductImage(id, imageId));
    }
    
}
