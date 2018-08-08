package com.zhdanov.recipebook.controller;

import com.zhdanov.recipebook.dto.UserDto;
import com.zhdanov.recipebook.entity.IngredientModel;
import com.zhdanov.recipebook.entity.UserModel;
import com.zhdanov.recipebook.service.IngredientService;
import com.zhdanov.recipebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private IngredientService ingredientService;

  @PreAuthorize("hasRole('ADMIN')")
  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public List<UserModel> listUser() {
    return userService.findAll();
  }

  @PreAuthorize("hasRole('USER')")
  ////@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
  public UserModel getOne(@PathVariable(value = "userId") Long id) {
    return userService.findById(id);
  }

  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public UserModel saveUser(@RequestBody UserDto user) {
    return userService.save(user);
  }

  @GetMapping("/users/{email}/exist")
  public boolean userExist(@PathVariable String email) {
    return userService.findByEmail(email) != null;
  }

  @PreAuthorize("hasRole('USER')")
  @GetMapping("/users/shopping-list")
  public List<IngredientModel> getUserShoppingList(final Authentication authentication) {
    System.out.println(authentication.getName());
    return userService.findByEmail(authentication.getName()).getShoppingList();
  }

}
