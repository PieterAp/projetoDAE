package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos.PolicyDTO;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Policy;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Client;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class PolicyBean {
    @PersistenceContext
    EntityManager entityManager;

    @EJB
    ClientBean clientBean;

    private String resource = "/policiesv2/";

    public List<Policy> getPoliciesByUserNif (long nif) {
        javax.ws.rs.client.Client client = ClientBuilder.newClient();
        Policy[] responsePolicies = client.target(System.getenv("MOCKAPI_url")+ resource +"?nif=" + nif)
                .request(MediaType.APPLICATION_JSON)
                .get(Policy[].class);

        return Arrays.asList(responsePolicies);
    }

    public List<Policy> getPoliciesByUserNipc (long mipc) {
        javax.ws.rs.client.Client client = ClientBuilder.newClient();
        Policy[] responsePolicies = client.target(System.getenv("MOCKAPI_url")+ resource +"?mipc=" + mipc)
                .request(MediaType.APPLICATION_JSON)
                .get(Policy[].class);

        return Arrays.asList(responsePolicies);
    }

    public List<Policy> getPoliciesByUserId (long user_id) {
        Client foundClient = entityManager.find(Client.class, user_id);
        javax.ws.rs.client.Client client = ClientBuilder.newClient();
        List<Policy> policies = new LinkedList<>();
        if (clientBean.isIndividual(user_id)) {
            policies = getPoliciesByUserNif(foundClient.getNif());
        } else if (clientBean.isCompany(user_id)) {
            policies = getPoliciesByUserNipc(foundClient.getNipc());
        }

        return policies;
    }

    public Policy find(long policy_id) {
        javax.ws.rs.client.Client client = ClientBuilder.newClient();
        Policy policy = client.target(System.getenv("MOCKAPI_url")+ resource + policy_id)
                .request(MediaType.APPLICATION_JSON)
                .get(Policy.class);

        return policy;
    }
}
