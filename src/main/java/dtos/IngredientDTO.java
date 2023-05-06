/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Ingredient;
import entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class IngredientDTO {
    private int id;
    private String name;
    private Double quantity;
    private String measurementUnit;
    private RecipeDTO recipe;


    public IngredientDTO(String name, Double quantity, String measurementUnit, RecipeDTO recipe) {
        this.name = name;
        this.quantity = quantity;
        this.measurementUnit = measurementUnit;
        this.recipe = recipe;
    }

    public IngredientDTO(Ingredient i){
        this.name = i.getName();
        this.quantity = i.getQuantity();
        this.measurementUnit = i.getMeasurementUnit();
        if (i.getRecipe() != null)
        this.recipe = new RecipeDTO(i.getRecipe());
    }
}
