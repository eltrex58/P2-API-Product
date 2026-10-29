package com.product.api.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter 
@Setter 
@Entity 
@Table (name = "product_image")
public class ProductImage {

    @Id 
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column (name = "product_image_id")
    private Integer productImageId;

    @Column (name = "product_id")
    private Integer productId;

    @Column (name = "image")
    private String image;

    @Column (name = "status")
    private Integer status;

}
