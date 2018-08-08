package com.zhdanov.recipebook.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "ingredients")
@EqualsAndHashCode(of = "id")
@Entity
@NamedEntityGraph(name = "Recipe.ingredients",
  attributeNodes = @NamedAttributeNode("ingredients"))
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Integer id;

    private String name;

    private String description;

    private String imagePath;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IngredientModel> ingredients = new ArrayList<>();

}
