package rest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.IngredientDTO;
import facades.IngredientFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("ingredient")
public class IngredientRecourse {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final IngredientFacade ingredientFacade = IngredientFacade.getIngredientFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Ingredient endpoint\"}";
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllIngredients() {
        List<IngredientDTO> allIngredients = ingredientFacade.getAllIngredients();
        return Response.ok().entity(GSON.toJson(allIngredients)).build();
    }


}
