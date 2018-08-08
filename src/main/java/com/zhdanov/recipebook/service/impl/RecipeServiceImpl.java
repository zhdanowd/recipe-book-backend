package com.zhdanov.recipebook.service.impl;

import com.zhdanov.recipebook.entity.Recipe;
import com.zhdanov.recipebook.repository.RecipeRepository;
import com.zhdanov.recipebook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RecipeServiceImpl implements RecipeService {

  @Autowired
  private RecipeRepository recipeRepository;

  @Override
  public Page<Recipe> getRecipes(int pageNumber, int size) {
    Pageable pageable = PageRequest.of(pageNumber, size);

    return recipeRepository.findAll(pageable);
  }

  @Override
  public Recipe saveRecipe(Recipe recipe) {

    recipe.getIngredients().stream().forEach(ingredientModel -> ingredientModel.setRecipe(recipe));

    return recipeRepository.save(recipe);
  }

  @Override
  public Recipe getRecipeById(Integer id) {
    return recipeRepository.findById(id).orElseThrow(NoSuchElementException::new);
  }

  @Override
  public Long countPages() {
    return recipeRepository.count();
  }

  @Override
  public void deleteRecipe(int id) {
    recipeRepository.deleteById(id);
  }
}
