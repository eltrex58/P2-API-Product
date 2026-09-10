package com.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List; 
import java.util.ArrayList;

@RestController 
@RequestMapping("/category")
public class CtrlProduct {

    @GetMapping
    public String categoryJSON() {
        ServiceCategory service = new ServiceCategory();
        service.createCategory(new Category("Ropa", "RP", null));
        service.createCategory(new Category("Calzado", "CLZD", 1));
        
	    return service.toString();
    }

    
}
