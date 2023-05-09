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
    private Long id;
    private String name;
    private Double quantity;
    private String measurementUnit;


    public IngredientDTO(String name, Double quantity, String measurementUnit) {
        this.name = name;
        this.quantity = quantity;
        this.measurementUnit = measurementUnit;

    }

    public IngredientDTO(Ingredient i){
        if (i.getId() != null)
            this.id = i.getId();
        this.name = i.getName();
        this.quantity = i.getQuantity();
        this.measurementUnit = i.getMeasurementUnit();
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
    public Double getQuantity() {
        return quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    public String getMeasurementUnit() {
        return measurementUnit;
    }
    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }
}
