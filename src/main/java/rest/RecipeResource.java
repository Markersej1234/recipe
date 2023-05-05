package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.RecipeDTO;
import entities.Recipe;
import entities.User;
import errorhandling.NotFoundException;
import facades.RecipeFacade;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
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

        Recipe recipe = new Recipe(dto.getName(),dto.getDescription(), user);
        Recipe created = recipeFacade.create(recipe);
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


}