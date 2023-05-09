package dtos;
import entities.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class UserDTO {
    private String userName;
    private String userPass;
    private List<String> roles = new ArrayList<>();
    private List<RecipeDTO> recipes = new ArrayList<>();


    public UserDTO(String userName, String userPass, List<String> roles, List<RecipeDTO> recipes) {
        this.userName = userName;
        this.userPass = userPass;
        this.roles = roles;
        this.recipes = recipes;
    }

    public UserDTO(User user) {
        this.userName = user.getUserName();
        this.userPass = user.getUserPass();
        this.roles = user.getRolesAsStrings();
        this.recipes = RecipeDTO.getDtos(user.getRecipes());
    }


    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getUserPass() { return userPass; }
    public void setUserPass(String userPass) { this.userPass = userPass; }
    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }
    public List<RecipeDTO> getRecipes() { return recipes; }
    public void setRecipes(List<RecipeDTO> recipes) { this.recipes = recipes; }
}
