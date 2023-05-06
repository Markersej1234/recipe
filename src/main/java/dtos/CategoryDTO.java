package dtos;

import entities.Category;
import entities.Instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryDTO {

    private Long id;
    private String name;
    private List<RecipeDTO> recipes;


    public CategoryDTO() {
    }

    public CategoryDTO(String name, List<RecipeDTO> recipes) {
        this.name = name;
        this.recipes = recipes;
    }

    public CategoryDTO(Category category) {
       if (category.getId() != null) {
           this.id = category.getId();
       }
       this.name = category.getName();
        if (category.getRecipes() != null){
            this.recipes = category.getRecipes().stream().map(r -> new RecipeDTO(r)).collect(Collectors.toList());
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<RecipeDTO> getRecipes() { return recipes; }
    public void setRecipes(List<RecipeDTO> recipes) { this.recipes = recipes; }
}

