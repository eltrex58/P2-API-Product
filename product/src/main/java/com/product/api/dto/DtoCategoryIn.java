package com.product.api.dto;

import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class DtoCategoryIn {

    @JsonProperty ("category")
    @NotNull (message="La categoria es obligatoria.")
    private String category;

    @JsonProperty ("tag")
    @NotNull (message = "La etiqueta es obligatoria")
    private String tag;

    @JsonProperty ("parentCategoryId")
    private Integer parentCategoryId;


}
