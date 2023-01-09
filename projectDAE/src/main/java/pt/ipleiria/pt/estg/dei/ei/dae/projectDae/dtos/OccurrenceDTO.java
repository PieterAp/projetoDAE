package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos;

import javax.persistence.Id;

public class OccurrenceDTO {
    @Id
    private long occurrence_id;

    private long client_id;

    private long policy_id;

    private String description;

    public OccurrenceDTO() {

    }

    public OccurrenceDTO(long occurrence_id, long client_id, long policy_id, String description) {
        this.occurrence_id = occurrence_id;
        this.client_id = client_id;
        this.policy_id = policy_id;
        this.description = description;
    }

    public long getOccurrence_id() {
        return occurrence_id;
    }

    public void setOccurrence_id(long occurrence_id) {
        this.occurrence_id = occurrence_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
