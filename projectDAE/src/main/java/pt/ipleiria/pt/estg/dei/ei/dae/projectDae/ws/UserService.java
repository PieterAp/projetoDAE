package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ws;


import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos.PolicyDTO;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos.UserDTO;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs.OccurrenceBean;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs.PolicyBean;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs.UserBean;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Policy;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.User;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("users")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class UserService {
    @EJB
    private UserBean userBean;

    @EJB
    private PolicyBean policyBean;

    @GET
    @Path("/")
    public List<UserDTO> getAllUsers() {
        return toDTOs(userBean.getAllUsers());
    }

    /*
    @GET
    @Path("/insuranceComp")
    public List<UserDTO> getAllInsuranceComps() {
        return toDTOs(userBean.findAllInsuranceComp());
    }
    */

    @GET
    @Path("/{user_id}")
    public Response getUser(@PathParam("user_id") long user_id) {
        User foundUser = userBean.find(user_id);

        if (foundUser != null) {
            return Response.ok(toDTO(foundUser)).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("ERROR_FINDING_COURSE")
                .build();
    }

    @GET
    @Path("/{user_id}/policies")
    public List<PolicyDTO> getPolicies(@PathParam("user_id") long user_id) {
        List<Policy> foundPolicies = policyBean.getPoliciesByUserId(user_id);

        if (foundPolicies.size() > 0) {
            return toDTOsPolicy(foundPolicies);
        }

        return null;
    }

    private UserDTO toDTO(User user) {
        return new UserDTO(
                user.getUser_id(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone()
        );
    }

    private List<UserDTO> toDTOs(List<User> users) {
        return users.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private PolicyDTO toDTOpolicy(Policy policy) {
        return new PolicyDTO(
                policy.getId(),
                policy.getUser_id(),
                policy.getDescription(),
                policy.getType(),
                policy.getCreatedAt()
        );
    }

    private List<PolicyDTO> toDTOsPolicy(List<Policy> policies) {
        return policies.stream().map(this::toDTOpolicy).collect(Collectors.toList());
    }
}
