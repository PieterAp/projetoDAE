package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos.PolicyDTO;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Policy;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class PolicyBean {

    public List<Policy> getPoliciesByUserId(long user_id) {
        Client client = ClientBuilder.newClient();
        Policy[] responsePolicies = client.target(System.getenv("MOCKAPI_url") + "/policies?user_id=" + user_id)
                .request(MediaType.APPLICATION_JSON)
                .get(Policy[].class);

        //filter array to *strictly* only get policies from user id
        List<Policy> policies = new LinkedList<>();
        for (Policy policy : responsePolicies) {
            if (policy.getUser_id() == user_id) {
                policies.add(policy);
            }
        }

        return policies;
    }

    public Policy find(long policy_id) {
        Client client = ClientBuilder.newClient();
        Policy policy = client.target(System.getenv("MOCKAPI_url") + "/policies/" + policy_id)
                .request(MediaType.APPLICATION_JSON)
                .get(Policy.class);

        return policy;
    }

    private PolicyDTO toDTO(Policy policy) {
        return new PolicyDTO(
                policy.getId(),
                policy.getUser_id(),
                policy.getInsurance_id(),
                policy.getDescription(),
                policy.getType(),
                policy.getCreatedAt()
        );
    }

    private List<PolicyDTO> toDTOs(List<Policy> subjects) {
        return subjects.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
