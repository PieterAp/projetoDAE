package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import com.sun.istack.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Entity
@Table(name = "occurrences")
@NamedQueries(
        {@NamedQuery(
                name = "getAllOccurrences",
                query = "SELECT o " +
                        "FROM Occurrence o " +
                        "ORDER BY o.occurrence_id ASC"
        ), @NamedQuery(
                name = "getAllUserOccurrences",
                query = "SELECT o " +
                        "FROM Occurrence o " +
                        "WHERE o.client_id = :user_id"
        ), @NamedQuery(
                name = "getAllInsuranceOccurrences",
                query = "SELECT o " +
                        "FROM Occurrence o " +
                        "WHERE o.insurance_id = :insurance_id"
        ), @NamedQuery(
                name = "getAllRepairOccurrences",
                query = "SELECT o " +
                        "FROM Occurrence o " +
                        "WHERE o.repair_id = :repair_id OR o.repair_id = null"
        ), @NamedQuery(
                name = "getAllExpertOccurrences",
                query = "SELECT o " +
                        "FROM Occurrence o " +
                        "WHERE o.expert_id = :expert_id OR o.expert_id = null"
        )}
)
public class Occurrence implements Serializable {

    @Id
    @GeneratedValue
    private long occurrence_id;

    private long client_id;

    private long insurance_id;

    private long policy_id;

    @Null
    private long repair_id;
    @Null
    private long expert_id;

    private String description;

    private String status;

    /*
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "occurrence", cascade = CascadeType.REMOVE)
    List<Repair> repairs;
    */

    public Occurrence() {

    }

    public Occurrence(long client_id, long insurance_id, long policy_id, String description, String status) {
        this.client_id = client_id;
        this.insurance_id = insurance_id;
        this.policy_id = policy_id;
        this.description = description;
        this.status = status;
    }

    public long getOccurrence_id() {
        return occurrence_id;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public long getInsurance_id() {
        return insurance_id;
    }

    public void setInsurance_id(long insurance_id) {
        this.insurance_id = insurance_id;
    }

    public long getPolicy_id() {
        return policy_id;
    }

    public void setPolicy_id(long policy_id) {
        this.policy_id = policy_id;
    }

    public long getRepair_id() {
        return repair_id;
    }

    public void setRepair_id(long repair_id) {
        this.repair_id = repair_id;
    }

    public long getExpert_id() {
        return expert_id;
    }

    public void setExpert_id(long expert_id) {
        this.expert_id = expert_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
