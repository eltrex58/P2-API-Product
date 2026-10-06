package com.product.api.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping("/category")
public class CtrlCategory {

    @Autowired 
    SvcCategory svc;

    CtrlCategory (SvcCategory svc) {
        this.svc = svc;
    }

    @GetMapping 
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(svc.findAll())
    }

    @GetMapping("/active")
    public ResponseEntity<List<Category>> findActive(){
        return ResponseEntity.ok(svc.findActive());
    }

    @GetMapping("/{id}/childs")
    public ResponseEntity<List<Category>> findChilds (@PathVariable("id") Integer id){
        return ResponseEntity.ok(svc.findChilds(id));
    }

    @GetMapping 
    public ResponseEntity<String> create(@Valid @RequestBody DtoCategoryIn in){
        svc.create(in);
        return ResponseEntity.ok("La categoría ha sido registrada");
    }

    @GetMapping("/{id}") 
    public ResponseEntity<String> update(@PathVariable("id") Integer id, @Valid @RequestBody DtoCategoryIn in){
        svc.update(in, id);
        return ResponseEntity.ok("La categoría ha sido actualizada");
    }
    
    @GetMapping("/{id}/enable") 
    public ResponseEntity<String> enable(@PathVariable("id") Integer id){
        svc.enable(id);
        return ResponseEntity.ok("La categoría ha sido activada");
    }

    @GetMapping("/{id}/disable") 
    public ResponseEntity<String> disable(@PathVariable("id") Integer id){
        svc.disable(id);
        return ResponseEntity.ok("La categoría ha sido desactivada");
    }
    
}
