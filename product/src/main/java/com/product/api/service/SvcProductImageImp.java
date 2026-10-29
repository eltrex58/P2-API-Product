package com.product.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.ProductImage;
import com.product.api.repository.RepoProductImage;
import com.product.exception.ApiException;

@Service 
public class SvcProductImageImp implements SvcProductImage{

    @Autowired 
    RepoProductImage repo;

    @Value ("${app.upload.dir}")
    private String uploadDir;

    @Value ("${app.upload.images}")
    private String uploadImages;
    
    @Override 
    public List<ProductImage> getProducImages (Integer productId) {
        return repo.findByProductIdAndStatus(productId, 1);
    }

    @Override 
    public String createProductImage(DtoProductImageIn in){
        try {
            if(in.getImage().startsWith("data:image")) {
                int commaIndex = in.getImage().indexOf(",");
                if(commaIndex != -1) {
                    in.setImage(in.getImage().substring(commaIndex + 1));
                }
            }

            byte[] imageBytes = Base64.getDecoder().decode(in.getImage());
            String fileName = UUID.randomUUID().toString() + ".png";
            Path imagePath = Paths.get(uploadDir, uploadImages, "product", fileName);

            Files.createDirectories(imagePath.getParent());
            Files.write(imagePath, imageBytes);

            ProductImage productImage = new ProductImage();
            productImage.setProductId(in.getProductId());
            productImage.setImage("/product/" + fileName);
            productImage.setStatus(1);
            repo.save(productImage);
            return "La imagen ha sido resgistrada";
        } catch (IOException e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el archivo");
        }
    }

    @Override 
    public String deleteProductImage (Integer productId, Integer productImageId){
        ProductImage image = repo.findById(productId)
            .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "La imagen no existe"));
        image.setStatus(0);
        repo.save(image);
        return "La imagen ha sido eliminada";
    }
}
