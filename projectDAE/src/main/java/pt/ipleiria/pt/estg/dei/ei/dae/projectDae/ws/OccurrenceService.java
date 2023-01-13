package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ws;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos.DocumentDTO;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos.OccurrenceDTO;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs.DocumentBean;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs.OccurrenceBean;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Document;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Occurrence;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.User;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.security.Authenticated;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("occurrences")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Authenticated
public class OccurrenceService {
    @EJB
    private OccurrenceBean occurrenceBean;

    @EJB
    private DocumentBean documentBean;

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
                occurrenceDTO.getPolicy_id(),
                occurrenceDTO.getDescription()
        );

        if (createdOccurrence == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).entity(toDTO(createdOccurrence)).build();
    }

    @PUT
    @Path("/{occurrenceid}")
    public Response updateOccurrence(@PathParam("occurrenceid") long occurrenceid, OccurrenceDTO occurrenceDTO) {
        boolean taskComplete = occurrenceBean.update(occurrenceid, occurrenceDTO.getStatus(), occurrenceDTO.getRepair_id(), occurrenceDTO.getExpert_id());

        if (!taskComplete) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("{occurrenceid}/documents")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response upload(@PathParam("occurrenceid") long occurrenceid, MultipartFormDataInput input) throws IOException {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();

        List<InputPart> inputParts = uploadForm.get("file");

        InputPart userID = uploadForm.get("id").get(0);

        var documents = new LinkedList<Document>();

        for (InputPart inputPart : inputParts) {
            MultivaluedMap<String, String> headers = inputPart.getHeaders();
            String filename = getFilename(headers);

            // convert the uploaded file to inputstream
            InputStream inputStream = inputPart.getBody(InputStream.class, null);

            byte[] bytes = IOUtils.toByteArray(inputStream);

            String homedir = System.getProperty("user.home");
            String dirpath = homedir + File.separator + "uploads" + File.separator + occurrenceid;
            mkdirIfNotExists(dirpath);

            String filepath = dirpath + File.separator + filename;
            writeFile(bytes, filepath);

            var document = documentBean.create(filepath, filename, occurrenceid, Long.valueOf(userID.getBodyAsString()));

            documents.add(document);
        }

        return Response.ok().build();
    }

    @GET
    @Path("{occurrenceid}/documents")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDocuments(@PathParam("occurrenceid") long occurrenceid) {
        var documents = documentBean.getOccurrenceDocuments(occurrenceid);
        return Response.ok(DocumentDTO.from(documents)).build();
    }

    private void writeFile(byte[] content, String filename) throws IOException {
        var file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fop = new FileOutputStream(file);

        fop.write(content);
        fop.flush();
        fop.close();

        System.out.println("Written: " + filename);
    }

    private void mkdirIfNotExists(String path) {
        File file = new File(path);

        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private String getFilename(MultivaluedMap<String, String> headers) {
        String[] contentDisposition = headers.getFirst("Content-Disposition").split(";");
        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                return name[1].trim().replaceAll("\"", "");
            }
        }

        return "unknown";
    }

    private List<OccurrenceDTO> toDTOs(List<Occurrence> occurrences) {
        return occurrences.stream().map(this::toDTO).collect(Collectors.toList());
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

}
