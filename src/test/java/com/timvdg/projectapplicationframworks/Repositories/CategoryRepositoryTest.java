package com.timvdg.projectapplicationframworks.Repositories;

import com.timvdg.projectapplicationframworks.models.Category;
import com.timvdg.projectapplicationframworks.models.User;
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
class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void TestCreateCategory(){
        Category category = new Category();
        category.setName("Nutrition");
        Category category1 = categoryRepository.save(category);

        Category category2 = new Category();
        category2.setName("Habitat");
        Category savedCategory2 = categoryRepository.save(category2);

        Category category3 = new Category();
        category3.setName("Toys");
        Category savedCategory3 = categoryRepository.save(category3);

        Category existedCat = testEntityManager.find(Category.class,category1.getId());
        assertThat(existedCat.getName()).isEqualTo(category1.getName());
    }
}