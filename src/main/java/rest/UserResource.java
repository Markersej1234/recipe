package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.RecipeDTO;
import dtos.UserDTO;
import entities.Recipe;
import entities.User;
import errorhandling.NotFoundException;
import facades.RecipeFacade;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author lam@cphbusiness.dk
 */
@Path("user")
public class UserResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final UserFacade userFacade = UserFacade.getUserFacade(EMF);
    private static final RecipeFacade recipeFacade = RecipeFacade.getRecipeFacade(EMF);
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"user endpoint\"}";
    }



    @GET
    @Path("{username}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPersonById(@PathParam("username") String username) throws NotFoundException {
        User user = userFacade.getUserByName(username);
        UserDTO userDTO = new UserDTO(user);
        return Response.ok().entity(GSON.toJson(userDTO)).build();
    }

    @GET
    @Path("/reaipes/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getReacipesByUsername(@PathParam("name") String name) throws EntityNotFoundException {
        return Response.ok().entity(GSON.toJson(userFacade.getReacipesByUsername(name))).build();
    }

}