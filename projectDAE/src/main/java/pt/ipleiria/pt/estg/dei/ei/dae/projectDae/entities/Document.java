package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "documents")
@NamedQueries(
        @NamedQuery(
                name = "getOccurrenceDocuments",
                query = "SELECT doc " +
                        "FROM Document doc "+
                        "WHERE doc.occurrence.occurrence_id = :id"

        )
)
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String filepath;

    @NotNull
    private String filename;

    @ManyToOne
    private Occurrence occurrence;

    @ManyToOne
    private User user;

    public Document() {
    }

    public Document(String filepath, String filename, Occurrence occurrence, User user) {
        this.filepath = filepath;
        this.filename = filename;
        this.occurrence = occurrence;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Occurrence getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(Occurrence occurrence) {
        this.occurrence = occurrence;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}