package com.timvdg.projectapplicationframworks.Repositories;

import com.timvdg.projectapplicationframworks.models.Category;
import com.timvdg.projectapplicationframworks.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
