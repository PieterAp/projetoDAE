package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ws;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs.OccurrenceBean;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs.PolicyBean;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.security.Authenticated;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("policies")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Authenticated
public class PolicyService {

    @EJB
    private PolicyBean policyBean;

    @EJB
    private OccurrenceBean occurrenceBean;

    @GET
    @Path("{policyid}")
    public Response getPolicy(@PathParam("policyid") long policyid) {
        return Response.ok(policyBean.find(policyid)).build();
    }

    @GET
    @Path("{policyid}/occurrences")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOccurrences(@PathParam("policyid") long policyid) {
        return Response.ok(occurrenceBean.getOccurrencesByPolicy(policyid)).build();
    }

}
