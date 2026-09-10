package com.product.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.entity.Category;
import com.product.api.service.ServiceCategory;

@RestController 
@RequestMapping("/category")
public class CtrlProduct {

    @GetMapping
    public String getCategories() {
        ServiceCategory service = new ServiceCategory();
        service.createCategory(new Category("Ropa", "RP", null));
        service.createCategory(new Category("Calzado", "CLZD", 1));
	    return service.toString();
    }

    
}
