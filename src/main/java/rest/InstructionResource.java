package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.InstructionDTO;
import facades.InstructionFacade;
import utils.EMF_Creator;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("instruction")
public class InstructionResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final InstructionFacade FACADE = InstructionFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Instruction endpoint\"}";
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllInstructions() {
        List<InstructionDTO> rns = FACADE.getAllInstructions();
        return Response.ok().entity(GSON.toJson(rns)).build();
    }

    @POST
    @Path("createInstruction")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    //@RolesAllowed("user")
    public Response createInstruction(InstructionDTO instructionDTO) {
        instructionDTO = FACADE.createInstruction(instructionDTO);
        return Response.ok().entity(GSON.toJson(instructionDTO)).build();

    }

}
