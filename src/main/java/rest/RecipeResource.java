package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.IngredientDTO;
import dtos.RecipeDTO;
import entities.Ingredient;
import entities.Recipe;
import entities.User;
import errorhandling.NotFoundException;
import facades.IngredientFacade;
import facades.RecipeFacade;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
@Path("recipe")
public class RecipeResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final UserFacade userFacade = UserFacade.getUserFacade(EMF);
    private static final RecipeFacade recipeFacade = RecipeFacade.getRecipeFacade(EMF);
    private static final IngredientFacade ingredientFacade = IngredientFacade.getIngredientFacade(EMF);
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"recipe endpoint\"}";
    }


    @Path("create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(String jsonContext) throws NotFoundException {
        RecipeDTO dto = GSON.fromJson(jsonContext, RecipeDTO.class);
        User user = userFacade.getUserByName(dto.getUserName());
        List<Ingredient> ingredients = new ArrayList<>();
        for (IngredientDTO ingredientDTO : dto.getIngredients()) {
            ingredients.add(new Ingredient(ingredientDTO.getName()));
        }
        Recipe temp = new Recipe(dto.getName(), dto.getDescription(), user, ingredients);
        Recipe created = recipeFacade.create(temp);

        for (Ingredient i : created.getIngredients()) {
            i.setRecipe(created); // This is needed to make the relationship work
            ingredientFacade.update(i);
        }
        RecipeDTO recipeDTO = new RecipeDTO(created);
        return Response.ok().entity(GSON.toJson(recipeDTO)).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPersonById(@PathParam("id") Long id) throws NotFoundException {
        RecipeDTO recipeDTO = recipeFacade.getRecipeById(id);
        return Response.ok().entity(GSON.toJson(recipeDTO)).build();
    }
    @POST
    @Path("addNewIngredient/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addNewIngredient(@PathParam("id") Long id, String jsonContext) {
        IngredientDTO ingredientDTO = GSON.fromJson(jsonContext, IngredientDTO.class);
        Ingredient ingredient = new Ingredient(ingredientDTO.getName());


        Recipe updated = recipeFacade.addNewIngredientToRecipe(id, ingredient);
        RecipeDTO recipeDTO = new RecipeDTO(updated);
        return Response.ok().entity(GSON.toJson(recipeDTO)).build();
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<RecipeDTO> rns = recipeFacade.getAllRecipes();
        return Response.ok().entity(GSON.toJson(rns)).build();
    }
    @Path("create2")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create2(String jsonContext) throws NotFoundException {
        RecipeDTO dto = GSON.fromJson(jsonContext, RecipeDTO.class);
        User user = userFacade.getUserByName(dto.getUserName());
        List<Ingredient> ingredients = new ArrayList<>();
        for (IngredientDTO ingredientDTO : dto.getIngredients()) {
            ingredients.add(new Ingredient(ingredientDTO.getName()));
        }
        Recipe temp = new Recipe(dto.getName(), dto.getDescription(), user, ingredients);
        Recipe created = recipeFacade.create(temp);

        for (Ingredient i : created.getIngredients()) {
            i.setRecipe(created); // This is needed to make the relationship work
            ingredientFacade.update(i);
        }
        RecipeDTO recipeDTO = new RecipeDTO(created);
        return Response.ok().entity(GSON.toJson(recipeDTO)).build();
    }



}