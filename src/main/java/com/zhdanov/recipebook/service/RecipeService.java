package com.zhdanov.recipebook.service;

import com.zhdanov.recipebook.entity.Recipe;
import org.springframework.data.domain.Page;

public interface RecipeService {

  Page<Recipe> getRecipes(int pageNumber, int size);

  Recipe saveRecipe(Recipe recipe);

  Recipe getRecipeById(Integer id);

  Long countPages();

  void deleteRecipe(int i);
}
