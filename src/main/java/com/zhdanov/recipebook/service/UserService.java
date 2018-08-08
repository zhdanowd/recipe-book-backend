package com.zhdanov.recipebook.service;

import com.zhdanov.recipebook.dto.UserDto;
import com.zhdanov.recipebook.entity.IngredientModel;
import com.zhdanov.recipebook.entity.UserModel;

import java.util.List;

public interface UserService {

  UserModel save(UserDto user);

  List<UserModel> findAll();

  void delete(long id);

  UserModel findByEmail(String email);

  UserModel findById(Long id);

}
