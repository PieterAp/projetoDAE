package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ws;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos.OccurrenceDTO;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos.PolicyDTO;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos.RepairDTO;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs.OccurrenceBean;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs.RepairBean;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Occurrence;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Policy;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Repair;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.security.Hasher;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("repairs")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class RepairService {

    @EJB
    private RepairBean repairBean;

    @EJB
    private OccurrenceBean occurrenceBean;

    @Inject
    private Hasher hasher;

    @POST
    @Path("/")
    public Response createRepair (RepairDTO repairDTO) {
        Occurrence occurrence = occurrenceBean.find(repairDTO.getInsurance_user_repair_id());
        Repair createdRepair = repairBean.create(repairDTO.getName(), repairDTO.getEmail(), "123", repairDTO.getPhone(), occurrence.getInsurance_id());
        occurrenceBean.update(occurrence.getOccurrence_id(), "Approved",createdRepair.getUser_id(),0);

        if (createdRepair == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).entity(toDTO(createdRepair)).build();
    }


    private RepairDTO toDTO(Repair repair) {
        return new RepairDTO(
                repair.getUser_id(),
                repair.getName(),
                repair.getEmail(),
                repair.getPassword(),
                repair.getPhone(),
                repair.getInsurance_user_repair_id()
        );
    }

}
