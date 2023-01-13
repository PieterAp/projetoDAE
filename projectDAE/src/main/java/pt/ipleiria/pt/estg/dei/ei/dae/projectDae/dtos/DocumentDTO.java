package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Document;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentDTO implements Serializable {

    private Long id;

    private String filename;

    private String owner;


    public DocumentDTO() {
    }

    public DocumentDTO(Long id, String filename, String owner) {
        this.owner = owner;
        this.id = id;
        this.filename = filename;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public static DocumentDTO from(Document document) {
        return new DocumentDTO(document.getId(), document.getFilename(), document.getUser().getName() + " [" + document.getUser().getUserType() + "]");
    }

    public static List<DocumentDTO> from(List<Document> documents) {
        return documents.stream().map(DocumentDTO::from).collect(Collectors.toList());
    }

}
