package com.bootcamp.EcommerceShop.repository;

import com.bootcamp.EcommerceShop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
