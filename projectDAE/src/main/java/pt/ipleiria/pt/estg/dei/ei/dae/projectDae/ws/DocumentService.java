package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ws;

import java.io.File;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs.DocumentBean;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.security.Authenticated;

@Path("documents")
@Authenticated
public class DocumentService {

    @EJB
    private DocumentBean documentBean;

    @Context
    private SecurityContext securityContext;

    @GET
    @Path("download/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("id") Long id) {
        var document = documentBean.findOrFail(id);
        var response = Response.ok(new File(document.getFilepath()));
        response.header("Content-Disposition", "attachment;filename=" + document.getFilename());
        return response.build();
    }
}
