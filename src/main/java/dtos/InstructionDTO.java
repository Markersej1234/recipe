package dtos;

import entities.Instruction;
import entities.Recipe;
import entities.Review;

import java.util.ArrayList;
import java.util.List;

public class InstructionDTO {

    private Long id;
    private Integer stepNumber;
    private String instructionText;
    private Recipe recipe;


    public InstructionDTO() {
    }

    public InstructionDTO(Instruction re) {
        if (re.getId() != null)
            this.id = re.getId();
        this.stepNumber = re.getStepNumber();
        this.instructionText = re.getInstructionText();
        if (re.getRecipe() != null) {
            this.recipe = re.getRecipe();
        }
    }

    public InstructionDTO(Integer stepNumber, String instructionText) {
        this.stepNumber = stepNumber;
        this.instructionText = instructionText;
    }


    public static List<InstructionDTO> getDtos(List<Instruction> instructions) {
        List<InstructionDTO> rmdtos = new ArrayList();
        instructions.forEach(re -> rmdtos.add(new InstructionDTO(re)));
        return rmdtos;
    }
    public Recipe getRecipe() { return recipe; }
    public void setRecipe(Recipe recipe) { this.recipe = recipe; }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getStepNumber() {
        return stepNumber;
    }
    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }
    public String getInstructionText() {
        return instructionText;
    }
    public void setInstructionText(String instructionText) {
        this.instructionText = instructionText;
    }
    @Override
    public String toString() {
        return "InstructionDTO{" +
                "id=" + id +
                ", stepNumber=" + stepNumber +
                ", instructionText='" + instructionText + '\'' +
                '}';
    }
}
