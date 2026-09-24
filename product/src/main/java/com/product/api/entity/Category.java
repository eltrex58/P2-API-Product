package com.product.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table (name = "category")
/**
 * Clase qque representa una categoría en el sistema.
 */
public class Category {

    /*
     * Atributos de la clase Category.
     * category_id: Identificador único de la categoría.
     * category: Nombre de la categoría.
     * tag: Etiqueta asociada a la categoría.
     * parentCategoryId: Identificador de la categoría padre (si existe).
     * status: Estado de la categoría (activo/inactivo). 
     */
    @Id 
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @JsonProperty ("categoryID")
    @Column (name="category_id") 
    private Integer category_id;
    
    @JsonProperty("Category")
    @Column(name="category")
    private String category;

    @JsonProperty("tag")
    @Column(name="tag")
    private String tag;

    @JsonProperty("parent_Category_Id")
    @Column(name="parent_Category_Id")
    private Integer parentCategoryId;

    @JsonProperty("status")
    @Column(name="status")
    private Integer status;
    
    /*
     * Constructor por defecto de la clase Category.
     */
    public Category(){}

    /**
     * Constructor de la clase Category que inicializa los atributos category, tag y parentCategoryId.
     * @param category Nombre de la categoría.
     * @param tag Etiqueta asociada a la categoría.
     * @param parentID Identificador de la categoría padre (si existe).
     * @throws NullPointerException Si el nombre o tag de la categoría es vacío.
     */
    public Category(String category, String tag, Integer parentID){
        if (category.isEmpty() || tag.isEmpty())
            throw new NullPointerException("El nombre o tag de la categoría no puede ser vacío.");
        this.category = category;
        this.tag = tag;
        parentCategoryId = parentID;
    }

    /**
     * Método para obtener el ID de la categoría.
     * @return El ID de la categoría.
     */
    public Integer getCategoryId() {
        return category_id;
    }

    /**
     * Método para obtener el nombre de la categoría.
     * @return El nombre de la categoría.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Método para obtener el tag de la categoría.
     * @return El tag de la categoría.
     */
    public String getTag() {
        return tag;
    }
    public Integer getParentCategoryId() {
        return parentCategoryId;
    }
    public Integer getStatus() {
        return status;
    }

    /**
     * Método para establecer el ID de la categoría.
     * @param category_id El ID de la categoría a establecer.
     */
    public void setCategoryId(Integer category_id) {
        this.category_id = category_id;
    }

    /**
     * Método para establecer el nombre de la categoría.
     * @param category El nombre de la categoría a establecer.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Método para establecer el tag de la categoría.
     * @param tag El tag de la categoría a establecer.
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Método para establecer el ID de la categoría padre.
     * @param parentCategoryId El ID de la categoría padre a establecer.
     */
    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;   
    }

    /**
     * Método para establecer el estado de la categoría.
     * @param status El estado de la categoría a establecer.
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Método para representar la categoría en forma de String. Se representa como un objeto JSON 
     * con los atributos de la categoría.
     * @return La representación de la categoría en forma de String.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{")
        .append(category_id)
        .append(',')
        .append(category)
        .append(',')
        .append(tag)
        .append(',')
        .append(parentCategoryId)
        .append(',')
        .append(status)
        .append("}");
        return sb.toString();
    }
}