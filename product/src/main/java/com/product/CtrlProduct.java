package com.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/category")
public class CtrlProduct {

    @GetMapping
    public String categoryJSON() {
	    return "Hello World";
    }

    
}
