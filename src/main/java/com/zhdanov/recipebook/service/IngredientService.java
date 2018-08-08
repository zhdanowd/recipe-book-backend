package com.zhdanov.recipebook.service;

import com.zhdanov.recipebook.entity.IngredientModel;

import java.util.List;

public interface IngredientService {

  void deleteIngredient(Long id);

  List<IngredientModel> addToShoppingList(List<IngredientModel> ingredient, String email);

  IngredientModel updateIngredient(IngredientModel ingredient, String email);
}
