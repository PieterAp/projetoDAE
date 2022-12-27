package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Occurrence implements Serializable {

    @Id
    private long occurrence_id;

    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "occurrence", cascade = CascadeType.REMOVE)
    List<Repair> repairs;

    public Occurrence() {
        this.repairs = new ArrayList<>();
    }

    public Occurrence(long occurrence_id, String description) {
        this.occurrence_id = occurrence_id;
        this.description = description;
        this.repairs = new ArrayList<>();
    }

    public long getOccurrence_id() {
        return occurrence_id;
    }

    public void setOccurrence_id(long occurrence_id) {
        this.occurrence_id = occurrence_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }
}
