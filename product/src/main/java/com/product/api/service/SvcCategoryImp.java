package com.product.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.exception.DBAccessException;

@Service 
public class SvcCategoryImp implements SvcCategory {
    @Autowired 
    RepoCategory repo;
    
    SvcCategoryImp (RepoCategory repo){
        this.repo = repo;
    }

    @Override 
    public ResponseEntity<List<Category>> getCategories(){
        try{
            return repo.getCategories();
        }catch (DataAccessException e){
            throw new DBAccessException(e);
        }
    }

    @Override 
    public ResponseEntity<List<Category>> getActiveCategories(){
        return repo.findByStatusOrderByCategory(1);
    }
}
