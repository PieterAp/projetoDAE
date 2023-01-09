package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="occurrences")
@NamedQueries(
    @NamedQuery(
            name = "getAllOccurrences",
            query = "SELECT o " +
                    "FROM Occurrence o " +
                    "ORDER BY o.occurrence_id ASC"
    )
)
public class Occurrence implements Serializable {

    @Id
    @GeneratedValue
    private long occurrence_id;

    private long client_id;

    private long insurance_id;

    private long policy_id;

    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "occurrence", cascade = CascadeType.REMOVE)
    List<Repair> repairs;

    public Occurrence() {
        this.repairs = new ArrayList<>();
    }

    public Occurrence(long client_id, long insurance_id, long policy_id, String description) {
        this.client_id = client_id;
        this.insurance_id = insurance_id;
        this.policy_id = policy_id;
        this.description = description;
    }

    public long getOccurrence_id() {
        return occurrence_id;
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

    public long getInsurance_id() {
        return insurance_id;
    }

    public void setInsurance_id(long insurance_id) {
        this.insurance_id = insurance_id;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public long getPolicy_id() {
        return policy_id;
    }

    public void setPolicy_id(long policy_id) {
        this.policy_id = policy_id;
    }
}
