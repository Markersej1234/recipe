package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ReviewDTO;
import facades.ReviewFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("rewiew")
public class ReviewResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final ReviewFacade FACADE = ReviewFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllReviews() {
        List<ReviewDTO> rns = FACADE.getAllReviews();
        return Response.ok().entity(GSON.toJson(rns)).build();
    }

    @POST
    @Path("createReview")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    //@RolesAllowed("user")
    public Response createReview(ReviewDTO reviewDTO) {
        reviewDTO = FACADE.createReview(reviewDTO);
        return Response.ok().entity(GSON.toJson(reviewDTO)).build();

    }


}
