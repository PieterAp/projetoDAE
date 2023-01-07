package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Occurrence implements Serializable {

    @Id  @GeneratedValue
    private long occurrence_id;
    private String description;

    public Occurrence() {

    }

    public Occurrence(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
