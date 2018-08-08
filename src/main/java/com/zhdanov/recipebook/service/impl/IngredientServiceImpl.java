package com.zhdanov.recipebook.service.impl;

import com.zhdanov.recipebook.entity.IngredientModel;
import com.zhdanov.recipebook.entity.UserModel;
import com.zhdanov.recipebook.repository.IngredientRepository;
import com.zhdanov.recipebook.repository.UserRepository;
import com.zhdanov.recipebook.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

  @Autowired
  private IngredientRepository ingredientRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public void deleteIngredient(Long id) {
    ingredientRepository.deleteById(id);
  }

  @Override
  public List<IngredientModel> addToShoppingList(List<IngredientModel> ingredients, String email  ) {
    UserModel user = userRepository.findByEmail(email);

    ingredients.stream().forEach(ingredient -> ingredient.setUser(user));
    return ingredientRepository.saveAll(ingredients);
  }

  @Override
  public IngredientModel updateIngredient(IngredientModel ingredient, String email) {
    UserModel user  = userRepository.findByEmail(email);

    ingredient.setUser(user);
    return ingredientRepository.save(ingredient);
  }
}
