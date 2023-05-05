package dtos;

import entities.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewDTO {

    String description;

    public ReviewDTO(Review rm) {
        this.description = rm.getDescription();
    }

    public ReviewDTO(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<ReviewDTO> getDtos(List<Review> rms){
        List<ReviewDTO> rmdtos = new ArrayList<>();
        rms.forEach(rm -> rmdtos.add(new ReviewDTO(rm)));
        return rmdtos;
    }

    public ReviewDTO() {
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "description='" + description + '\'' +
                '}';
    }
}
