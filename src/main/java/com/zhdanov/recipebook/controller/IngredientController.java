package com.zhdanov.recipebook.controller;

import com.zhdanov.recipebook.entity.IngredientModel;
import com.zhdanov.recipebook.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ingredients")
public class IngredientController {

  @Autowired
  private IngredientService ingredientService;

  @DeleteMapping("/{id}")
  public void deleteIngredient(@PathVariable("id") Long id) {
    ingredientService.deleteIngredient(id);
  }


  @PreAuthorize("hasRole('USER')")
  @PostMapping
  public List<IngredientModel> addToShoppingList(@RequestBody List<IngredientModel> ingredients, final Authentication authentication) {
    return ingredientService.addToShoppingList(ingredients, authentication.getName());
  }


  @PreAuthorize("hasRole('USER')")
  @PutMapping
  public IngredientModel updateIngredient(@RequestBody IngredientModel ingredient, final Authentication authentication) {
    return ingredientService.updateIngredient(ingredient, authentication.getName());
  }

}
