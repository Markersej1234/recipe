/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Ingredient;
import entities.Recipe;
import entities.RenameMe;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class RecipeDTO {
    private Long id;
    private String name;
    private String description;
    private String userName;
    private List<IngredientDTO> ingredients = new ArrayList<>();

    public RecipeDTO(Recipe re) {
        if(re.getId() != null)
            this.id = re.getId();
        this.name = re.getName();
        this.description = re.getDescription();
        this.userName = re.getUser().getUserName();
        for (Ingredient i : re.getIngredients()){
            this.ingredients.add(new IngredientDTO(i));
        }
    }

    public RecipeDTO(String name, String description, String userName, List<IngredientDTO> ingredients) {
        this.name = name;
        this.description = description;
        this.userName = userName;
        this.ingredients = ingredients;
    }


    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }
    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }
    public long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public static List<RecipeDTO> getDtos(List<Recipe> recipes){
        List<RecipeDTO> rmdtos = new ArrayList();
        recipes.forEach(re -> rmdtos.add(new RecipeDTO(re)));
        return rmdtos;
    }

}
