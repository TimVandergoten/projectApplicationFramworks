package com.timvdg.projectapplicationframworks.Repositories;

import com.timvdg.projectapplicationframworks.models.Category;
import com.timvdg.projectapplicationframworks.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void TestCreateCategory(){
        Category category1 = categoryRepository.getById(1L);
        Category category2 = categoryRepository.getById(2L);
        Category category3 = categoryRepository.getById(3L);
        Product product = new Product();
        product.setName("Product 1");
        product.setCategory(category1);
        product.setDescription("Nullam imperdiet id libero non commodo");
        product.setImageName("Default");
        product.setPrice(14.99);
        Product savedProduct = productRepository.save(product);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setCategory(category2);
        product2.setDescription("Nullam imperdiet id libero non commodo");
        product2.setImageName("Default");
        product2.setPrice(19.99);
        Product savedProduct2 = productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("Product 3");
        product3.setCategory(category1);
        product3.setDescription("Nullam imperdiet id libero non commodo");
        product3.setImageName("Default");
        product3.setPrice(24.99);
        Product savedProduct3 = productRepository.save(product3);

        Product product4 = new Product();
        product4.setName("Product 4");
        product4.setCategory(category3);
        product4.setDescription("Nullam imperdiet id libero non commodo");
        product4.setImageName("Default");
        product4.setPrice(9.99);
        Product savedProduct4 = productRepository.save(product4);

        Product existedProd = testEntityManager.find(Product.class,savedProduct.getId());
        assertThat(existedProd.getName()).isEqualTo(product.getName());

        Product existedProd2 = testEntityManager.find(Product.class,savedProduct2.getId());
        assertThat(existedProd2.getName()).isEqualTo(product2.getName());

        Product existedProd3 = testEntityManager.find(Product.class,savedProduct3.getId());
        assertThat(existedProd3.getName()).isEqualTo(product3.getName());

        Product existedProd4 = testEntityManager.find(Product.class,savedProduct4.getId());
        assertThat(existedProd4.getName()).isEqualTo(product4.getName());


    }

}