package entities;

import javax.persistence.*;

@Table(name = "ingredience")
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "measurementUnit")
    private String measurementUnit;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;


    public Ingredient() {
    }

    public Ingredient(String name, Double quantity, String measurementUnit, Recipe recipe) {
        this.name = name;
        this.quantity = quantity;
        this.measurementUnit = measurementUnit;
        this.recipe = recipe;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }
    public String getMeasurementUnit() { return measurementUnit; }
    public void setMeasurementUnit(String measurementUnit) { this.measurementUnit = measurementUnit; }
    public Recipe getRecipe() { return recipe; }
    public void setRecipe(Recipe recipe) { this.recipe = recipe; }
}