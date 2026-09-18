package com.product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.api.entity.Category;

@Repository 
public interface RepoCategory extends JpaRepository<Category,Integer>{
    
    List<Category> getCategories();

    List<Category> findByStatusOrderByCategory(Integer status);
}
