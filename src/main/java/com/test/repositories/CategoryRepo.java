package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
