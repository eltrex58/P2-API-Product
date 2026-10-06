package com.product.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

@Service 
public class SvcCategoryImp implements SvcCategory {
    @Autowired 
    RepoCategory repo;
    
    SvcCategoryImp (RepoCategory repo){
        this.repo = repo;
    }

    @Override 
    public List<Category> findAll(){
        try{
            return repo.findAll();
        }catch (DataAccessException e){
            throw new DBAccessException(e);
        }
    }

    @Override 
    public List<Category> findActive(){
        try {
            return repo.findByStatusOrderByCategoryIdAsc(1);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public List<Category> findChilds(Integer id){
        try {
            return repo.findByParentCategoryId(id);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override 
    public void create(DtoCategoryIn in){
        if(in.getParentCategoryId() != null) {
            if(repo.findById(in.getParentCategoryId()).isEmpty()){
            throw new ApiException(HttpStatus.NOT_FOUND, "La categoría padre no existe");
            }
            if(repo.findById(in.getParentCategoryId()).get().getCategoryId() == 0)
                throw new ApiException(HttpStatus.BAD_REQUEST, "La categoría padre está desactivada.");
        }
        try {
            Category category = new Category();
            category.setCategory(in.getCategory());
            category.setTag(in.getTag());
            category.setParentCategoryId(in.getParentCategoryId());
            category.setStatus(1);
            repo.save(category);
        } catch (DataAccessException e) {
            if(e.getLocalizedMessage().contains("ux_category"))
                throw new ApiException(HttpStatus.CONFLICT, "El nombre de la categoría ya existe");
            if(e.getLocalizedMessage().contains("ux_tag"))
                throw new ApiException(HttpStatus.CONFLICT, "El tag de la categoría ya existe");
            throw new DBAccessException(e);
        }

    }
    @Override 
    public void update(DtoCategoryIn in, Integer id){
        validateId(id);
        if (in.getParentCategoryId() != null){
            validateParentId(in.getParentCategoryId());
            if(in.getParentCategoryId() == id)
                throw new ApiException(HttpStatus.BAD_REQUEST, "La categoría no puede ser padre de sí misma");
        }
        try {
            Category category = repo.findById(id).get();
            category.setCategory(in.getCategory());
            category.setTag(in.getTag());
            category.setParentCategoryId(in.getParentCategoryId());
            repo.save(category);
        } catch (DataAccessException e) {
            if(e.getLocalizedMessage().contains("ux_category"))
                throw new ApiException(HttpStatus.CONFLICT, "El nombre de la categoría ya existe");
            if(e.getLocalizedMessage().contains("ux_tag"))
                throw new ApiException(HttpStatus.CONFLICT, "El tag de la categoría ya existe");
            throw new DBAccessException(e);
        }
    }
    @Override 
    public void enable(Integer id){
        validateId(id);
        try {
            Category category = repo.findById(id).get();
            category.setStatus(1);
            repo.save(category);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }
    @Override 
    public void disable(Integer id){
        validateId(id);
        if (!repo.findByParentCategoryId(id).isEmpty()){
            throw new ApiException(HttpStatus.BAD_REQUEST, "No es posible desactivar una categoría con hijas");
        }
        try {
            Category category = repo.findById(id).get();
            category.setStatus(0);;
            repo.save(category);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }        
    }

    private void validateId(Integer id){
        if(repo.findById(id).isEmpty()){
            throw new ApiException(HttpStatus.NOT_FOUND, "La categoría no existe");
        }
    }

    private void validateParentId(Integer parentId){
        if(repo.findById(parentId).isEmpty()){
            throw new ApiException(HttpStatus.NOT_FOUND, "La categoría padre no existe");
        }
        if(repo.findById(parentId).get().getCategoryId() == 0)
            throw new ApiException(HttpStatus.BAD_REQUEST, "La categoría padre está desactivada.");
    }
}
