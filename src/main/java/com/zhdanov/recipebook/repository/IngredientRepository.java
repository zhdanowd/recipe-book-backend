package com.zhdanov.recipebook.repository;

import com.zhdanov.recipebook.entity.IngredientModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<IngredientModel, Long> {
}
