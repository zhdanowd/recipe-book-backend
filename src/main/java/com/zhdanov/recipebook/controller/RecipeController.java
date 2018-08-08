package com.zhdanov.recipebook.controller;

import com.zhdanov.recipebook.entity.Recipe;
import com.zhdanov.recipebook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/recipes")
public class RecipeController {

  @Autowired
  private RecipeService recipeService;

  @GetMapping
  public Page<Recipe> getRecipes(@RequestParam int pageNumber, @RequestParam int pageSize) {
    return recipeService.getRecipes(pageNumber, pageSize);
  }

  @GetMapping("/count")
  public Long countRecipes() {
    return recipeService.countPages();
  }

  @GetMapping("/{id}")
  public Recipe getRecipeById(@PathVariable final String id) {
    return recipeService.getRecipeById(Integer.parseInt(id));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping
  Recipe saveRecipes(@RequestBody Recipe recipe) {
    return recipeService.saveRecipe(recipe);
  }

  @DeleteMapping("/{id}")
  @PutMapping("hasRole('ADMIN')")
  void deleteRecipe(@PathVariable final String id){
    recipeService.deleteRecipe(Integer.parseInt(id));
  }
}
