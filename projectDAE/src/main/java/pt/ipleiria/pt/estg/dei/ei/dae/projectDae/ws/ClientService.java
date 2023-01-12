package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ws;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos.PolicyDTO;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs.ClientBean;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs.PolicyBean;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Policy;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.security.Authenticated;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("clients")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Authenticated
public class ClientService {
    @EJB
    private ClientBean clientBean;

    @EJB
    private PolicyBean policyBean;

    @GET
    @Path("/{client_id}/policies")
    public List<PolicyDTO> getPolicies(@PathParam("client_id") long client_id) {
        List<Policy> foundPolicies = policyBean.getPoliciesByUserId(client_id);

        if (foundPolicies.size() > 0) {
            return toDTOsPolicy(foundPolicies);
        }

        return null;
    }

    private PolicyDTO toDTOpolicy(Policy policy) {
        return new PolicyDTO(
                policy.getId(),
                policy.getUser_id(),
                policy.getInsurance_id(),
                policy.getDescription(),
                policy.getType(),
                policy.getCreatedAt()
        );
    }

    private List<PolicyDTO> toDTOsPolicy(List<Policy> policies) {
        return policies.stream().map(this::toDTOpolicy).collect(Collectors.toList());
    }
}
