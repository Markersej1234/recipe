package entities;

import javax.persistence.*;

@Table(name = "instructions")
@Entity
public class Instruction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "step_number")
    private Integer stepNumber;

    @Column(name = "instruction_text")
    private String instructionText;

    @ManyToOne
    private Recipe recipe;

    public Instruction() {
    }

    public Instruction(Integer stepNumber, String instructionText, Recipe recipe) {
        this.stepNumber = stepNumber;
        this.instructionText = instructionText;
        this.recipe = recipe;
    }




    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getStepNumber() { return stepNumber; }
    public void setStepNumber(Integer stepNumber) { this.stepNumber = stepNumber; }
    public String getInstructionText() { return instructionText; }
    public void setInstructionText(String instructionText) { this.instructionText = instructionText; }
    public Recipe getRecipe() { return recipe; }
    public void setRecipe(Recipe recipe) { this.recipe = recipe; }
}