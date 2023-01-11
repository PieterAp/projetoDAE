package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ws;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos.OccurrenceDTO;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs.OccurrenceBean;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Occurrence;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.User;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.security.Authenticated;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("occurrences")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Authenticated
public class OccurrenceService {
    @EJB
    private OccurrenceBean occurrenceBean;

    @GET
    @Path("/")
    public List<OccurrenceDTO> getAllOccurrences() {
        return toDTOs(occurrenceBean.getAllOccurrences());
    }

    @POST
    @Path("/")
    public Response createOccurrence(OccurrenceDTO occurrenceDTO) {
        Occurrence createdOccurrence = occurrenceBean.create(
                occurrenceDTO.getClient_id(),
                occurrenceDTO.getInsurance_id(),
                occurrenceDTO.getPolicy_id(),
                occurrenceDTO.getDescription(),
                occurrenceDTO.getStatus());

        if (createdOccurrence == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).entity(toDTO(createdOccurrence)).build();
    }

    //todo: WARNING, ONLY UPDATES THE STATUS!! ↓ ↓ ↓ ↓
    @PUT
    @Path("/{occurrenceid}")
    public Response updateOccurrence(@PathParam("occurrenceid") long occurrenceid, OccurrenceDTO occurrenceDTO) {
        boolean taskComplete = occurrenceBean.update(occurrenceid, occurrenceDTO.getStatus(), occurrenceDTO.getRepair_id(), occurrenceDTO.getExpert_id());

        if (!taskComplete) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/{occurrenceid}")
    public Response getOccurrence(@PathParam("occurrenceid") long occurrenceid) {
        return Response.ok(occurrenceBean.getOccurrenceDetails(occurrenceid)).build();
    }

    private OccurrenceDTO toDTO(Occurrence occurrence) {
        return new OccurrenceDTO(
                occurrence.getOccurrence_id(),
                occurrence.getClient_id(),
                occurrence.getInsurance_id(),
                occurrence.getPolicy_id(),
                occurrence.getRepair_id(),
                occurrence.getExpert_id(),
                occurrence.getDescription(),
                occurrence.getStatus()
        );
    }

    private List<OccurrenceDTO> toDTOs(List<Occurrence> occurrences) {
        return occurrences.stream().map(this::toDTO).collect(Collectors.toList());
    }

}
