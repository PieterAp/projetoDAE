package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ws;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos.Auth;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos.UserDTO;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs.UserBean;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.security.Authenticated;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.security.TokenIssuer;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("auth")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class AuthService {
    @Inject
    private TokenIssuer issuer;

    @EJB
    private UserBean userBean;

    @Context
    private SecurityContext securityContext;

    @POST
    @Path("/login")
    public Response authenticate(@Valid Auth auth) {
        if (userBean.canLogin(auth.getEmail(), auth.getPassword())) {
            String token = issuer.issue(auth.getEmail());
            return Response.ok(token).build();
        }

        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Authenticated
    @Path("/user")
    public Response getAuthenticatedUser() {
        var email = securityContext.getUserPrincipal().getName();
        var user = userBean.findOrFail(email);

        return Response.ok(UserDTO.from(user)).build();
    }
}
