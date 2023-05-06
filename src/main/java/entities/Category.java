package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "category")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Recipe> recipes;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, List<Recipe> recipes) {
        this.name = name;
        this.recipes = recipes;
    }



    //getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Recipe> getRecipes() { return recipes; }
    public void setRecipes(List<Recipe> recipes) { this.recipes = recipes; }
}