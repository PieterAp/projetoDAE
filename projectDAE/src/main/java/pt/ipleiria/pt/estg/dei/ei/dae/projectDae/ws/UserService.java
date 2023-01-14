package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ws;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos.OccurrenceDTO;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos.RepairDTO;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos.UserDTO;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs.*;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Path("users")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class UserService {
    @EJB
    private UserBean userBean;

    @EJB
    private ExpertBean expertBean;

    @EJB
    private ClientBean clientBean;

    @EJB
    private InsuranceBean insuranceBean;

    @EJB
    private PolicyBean policyBean;

    @EJB
    private RepairBean repairBean;

    @EJB
    private OccurrenceBean occurrenceBean;

    public UserService() {
    }

    @GET
    @Path("/")
    public List<UserDTO> getAllUsers() {
        return toDTOs(userBean.getAllUsers());
    }

    @GET
    @Path("/{user_id}")
    public Response getUser(@PathParam("user_id") long user_id) {
        User foundUser = userBean.find(user_id);

        if (foundUser.getUserType().equals("Client")) {
            return Response.ok(toClientDTO(foundUser)).build();
        } else if (foundUser.getUserType().equals("Insurance")) {
            return Response.ok(toInsuranceDTO(foundUser)).build();
        } else if (foundUser.getUserType().equals("Expert")) {
            return Response.ok(toExpertDTO(foundUser)).build();
        }

        return Response.ok(toDTO(foundUser)).build();
    }

    @GET
    @Path("/{user_id}/occurrences")
    public List<OccurrenceDTO> getUserOccurrences(@PathParam("user_id") long user_id) {
        User foundUser = userBean.find(user_id);

        if (foundUser != null) {
            if (Objects.equals(foundUser.getUserType(), "Client"))
                return oToDTOs(occurrenceBean.getAllUserOccurrences(user_id));
            if (Objects.equals(foundUser.getUserType(), "Insurance"))
                return oToDTOs(occurrenceBean.getAllInsuranceOccurrences(user_id));
            if (Objects.equals(foundUser.getUserType(), "Repair")) {
                Repair repair = repairBean.find(user_id);
                return oToDTOs(occurrenceBean.getAllRepairOccurrences(user_id, repair.getInsurance_user_repair_id()));
            }
            if (Objects.equals(foundUser.getUserType(), "Expert")) {
                Expert expert = expertBean.findUserId(user_id);
                return oToDTOs(occurrenceBean.getAllExpertOccurrences(user_id, expert.getInsurance().getUser_id()));
            }
        }

        return null;
    }

    @GET
    @Path("/{occurrance_id}/repairs")
    public List<RepairDTO> getRepairsFromInsurance(@PathParam("occurrance_id") long occurrance_id) {
        return toDTOsRepair(repairBean.getRepairsAssignInsurance(occurrance_id));
    }

    private List<OccurrenceDTO> oToDTOs(List<Occurrence> occurrences) {
        return occurrences.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private UserDTO toDTO(User user) {
        return new UserDTO(
                user.getUser_id(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getUserType(),
                user.getPhone()
        );
    }

    private UserDTO toClientDTO(User user) {
        Client client = clientBean.findUserId(user.getUser_id());
        return new UserDTO(
                user.getUser_id(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getUserType(),
                client.getAddress(),
                client.getNif(),
                client.getNipc(),
                user.getPhone()
        );
    }

    private UserDTO toInsuranceDTO(User user) {
        Insurance insurance = insuranceBean.findUserId(user.getUser_id());
        return new UserDTO(
                user.getUser_id(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getUserType(),
                insurance.getAddress(),
                insurance.getShare_capital(),
                user.getPhone()
        );
    }

    private UserDTO toExpertDTO(User user) {
        Expert expert = expertBean.findUserId(user.getUser_id());

        return new UserDTO(
                user.getUser_id(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getUserType(),
                user.getPhone(),
                expert.getInsurance().getName()
        );
    }

    private OccurrenceDTO toDTO(Occurrence occurrence) {

        Insurance insurance = insuranceBean.findUserId(occurrence.getInsurance_id());
        Policy policy = policyBean.find(occurrence.getPolicy_id());
        User user = userBean.find(occurrence.getClient_id());

        if (occurrence.getExpert_id() != 0 && occurrence.getRepair_id() == 0) {
            User expert = userBean.find(occurrence.getExpert_id());
            return new OccurrenceDTO(
                    occurrence.getOccurrence_id(),
                    user.getName(),
                    occurrence.getClient_id(),
                    occurrence.getInsurance_id(),
                    occurrence.getPolicy_id(),
                    occurrence.getRepair_id(),
                    occurrence.getExpert_id(),
                    occurrence.getDescription(),
                    occurrence.getStatus(),
                    insurance.getName(),
                    policy.getDescription(),
                    expert.getName()
            );
        } else if (occurrence.getExpert_id() != 0 && occurrence.getRepair_id() != 0) {
            User expert = userBean.find(occurrence.getExpert_id());
            User repair = userBean.find(occurrence.getRepair_id());

            return new OccurrenceDTO(
                    occurrence.getOccurrence_id(),
                    user.getName(),
                    occurrence.getClient_id(),
                    occurrence.getInsurance_id(),
                    occurrence.getPolicy_id(),
                    occurrence.getRepair_id(),
                    occurrence.getExpert_id(),
                    occurrence.getDescription(),
                    occurrence.getStatus(),
                    insurance.getName(),
                    policy.getDescription(),
                    expert.getName(),
                    repair.getName()
            );
        }

        return new OccurrenceDTO(
                occurrence.getOccurrence_id(),
                user.getName(),
                occurrence.getClient_id(),
                occurrence.getInsurance_id(),
                occurrence.getPolicy_id(),
                occurrence.getRepair_id(),
                occurrence.getExpert_id(),
                occurrence.getDescription(),
                occurrence.getStatus(),
                insurance.getName(),
                policy.getDescription()
        );

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

    private List<UserDTO> toDTOs(List<User> users) {
        return users.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private List<RepairDTO> toDTOsRepair(List<Repair> repairs) {
        return repairs.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
