package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ws;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos.OccurrenceDTO;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs.OccurrenceBean;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Occurrence;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("occurrences")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class OccurrenceService {
    @EJB
    private OccurrenceBean occurrenceBean;

    @GET
    @Path("/")
    public List<OccurrenceDTO> getAllOccurrences(){
        return toDTOs(occurrenceBean.getAllOccurrences());
    }

    @POST
    @Path("/")
    public Response createCourse (OccurrenceDTO occurrenceDTO) {
        Occurrence createdOccurrence = occurrenceBean.create(
                occurrenceDTO.getOccurrence_id(),
                occurrenceDTO.getClient_id(),
                occurrenceDTO.getPolicy_id(),
                occurrenceDTO.getDescription(),
                occurrenceDTO.getStatus());

        if (createdOccurrence == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).entity(toDTO(createdOccurrence)).build();
    }

    @PUT
    @Path("/{occurrenceid}")
    public Response updateCourse (@PathParam("occurrenceid") long occurrenceid, OccurrenceDTO occurrenceDTO) {
        boolean taskComplete = occurrenceBean.update(occurrenceid, occurrenceDTO.getStatus());

        if (!taskComplete) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).build();
    }


    private OccurrenceDTO toDTO(Occurrence occurrence) {
        return new OccurrenceDTO(
                occurrence.getOccurrence_id(),
                occurrence.getClient_id(),
                occurrence.getPolicy_id(),
                occurrence.getDescription(),
                occurrence.getStatus()
        );
    }

    private List<OccurrenceDTO> toDTOs(List<Occurrence> occurrences) {
        return occurrences.stream().map(this::toDTO).collect(Collectors.toList());
    }

}
