package com.zhdanov.recipebook.service.impl;

import com.zhdanov.recipebook.dto.UserDto;
import com.zhdanov.recipebook.entity.IngredientModel;
import com.zhdanov.recipebook.entity.UserModel;
import com.zhdanov.recipebook.repository.RoleRepository;
import com.zhdanov.recipebook.repository.UserRepository;
import com.zhdanov.recipebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private BCryptPasswordEncoder bcryptEncoder;

  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserModel user = Optional.ofNullable(findByEmail(email))
      .orElseThrow(() -> new UsernameNotFoundException("Invalid email or password"));

    return new User(user.getEmail(), user.getPassword(), getAuthority(user));
  }

  public List<UserModel> findAll() {
    List<UserModel> list = new ArrayList<>();
    userRepository.findAll().iterator().forEachRemaining(list::add);
    return list;
  }

  @Override
  public void delete(long id) {
    userRepository.deleteById(id);
  }

  @Override
  public UserModel findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public UserModel findById(Long id) {
    return userRepository.findById(id).get();
  }

  @Override
  public UserModel save(UserDto user) {
    UserModel newUser = new UserModel();
    newUser.setEmail(user.getEmail());
    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
    newUser.setRoles(Stream.of(roleRepository.findByRoleName("USER")).collect(Collectors.toSet()));
    return userRepository.save(newUser);
  }

  private Set<SimpleGrantedAuthority> getAuthority(UserModel user) {
    Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    user.getRoles().forEach(role -> {
      authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
    });
    return authorities;
  }
}
