package com.timvdg.projectapplicationframworks.services;

import com.timvdg.projectapplicationframworks.Repositories.ProductRepository;
import com.timvdg.projectapplicationframworks.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService{
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void updateProduct(Product product){
        productRepository.save(product);
    }

    public void removeProductById(long id){
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductById(long id){
        return productRepository.findById(id);
    }

    public List<Product> getAllProductByCategoryId(long id){ return productRepository.findAllByCategoryId(id); }
}
