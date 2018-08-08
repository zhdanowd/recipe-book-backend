package com.zhdanov.recipebook.repository;

import com.zhdanov.recipebook.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

  @EntityGraph(value = "Recipe.ingredients", type = EntityGraph.EntityGraphType.LOAD)
  Page<Recipe> findAll(Pageable pageable);

}
