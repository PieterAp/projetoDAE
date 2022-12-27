package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Repair implements Serializable  {
    @Id
    private long repair_id;
    @ManyToOne
    @JoinColumn(name = "occurrence_id")
    @NotNull
    Occurrence occurrence;

    public Repair() {
    }

    public Repair(long repair_id, Occurrence occurrence) {
        this.repair_id = repair_id;
        this.occurrence = occurrence;
    }

    public long getRepair_id() {
        return repair_id;
    }

    public void setRepair_id(long repair_id) {
        this.repair_id = repair_id;
    }

    public Occurrence getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(Occurrence occurrence) {
        this.occurrence = occurrence;
    }
}
