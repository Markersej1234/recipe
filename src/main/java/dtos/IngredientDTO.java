/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Ingredient;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class IngredientDTO {
    private Long id;
    private String name;
    private double quantity;
    private String measurementUnit;



    public IngredientDTO(String name) {
        this.name = name;
    }


    public IngredientDTO() {
    }

    public IngredientDTO(Ingredient i){
        if (i.getId() != null)
            this.id = i.getId();
        this.name = i.getName();
    }

    public static List<IngredientDTO> getDtos(List<Ingredient> ingredients) {
        List<IngredientDTO> rmdtos = new ArrayList();
        ingredients.forEach(re -> rmdtos.add(new IngredientDTO(re)));
        return rmdtos;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }
}
