package dtos;

import entities.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewDTO {
    public long id;
    public String name;
    public String description;
    public String userName;
    public RecipeDTO recipe;


    public ReviewDTO() {
    }

    public ReviewDTO(String name, String description, String userName) {
        this.name = name;
        this.description = description;
        this.userName = userName;
    }

    public ReviewDTO(Review rm) {
        if (rm.getId() != null)
            this.id = rm.getId();
        this.name = rm.getName();
        this.description = rm.getDescription();
        this.userName = rm.getUser().getUserName();
        if (rm.getRecipe() != null)
            this.recipe = new RecipeDTO(rm.getRecipe());
    }


    public static List<ReviewDTO> getDtos(List<Review> rms) {
        List<ReviewDTO> rmdtos = new ArrayList<>();
        rms.forEach(rm -> rmdtos.add(new ReviewDTO(rm)));
        return rmdtos;
    }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

}
