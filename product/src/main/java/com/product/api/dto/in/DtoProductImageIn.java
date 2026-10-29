package com.product.api.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter 
@Setter 
public class DtoProductImageIn {
    @JsonProperty ("product_id")
    @NotNull (message="El productId es obligatorio")
    private Integer productId; 

    @JsonProperty("image")
    @NotNull(message="El image es obligatorio")
    private String image;
}
