package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos;

import javax.persistence.Id;

public class OccurrenceDTO {
    @Id
    private long occurrence_id;
    private long client_id;
    private long insurance_id;
    private long policy_id;

    private long repair_id;

    private long expert_id;
    private String description;
    private String status;

    public OccurrenceDTO() {

    }

    public OccurrenceDTO(long occurrence_id, long client_id, long insurance_id, long policy_id,long repair_id, long expert_id, String description, String status) {
        this.occurrence_id = occurrence_id;
        this.client_id = client_id;
        this.insurance_id = insurance_id;
        this.policy_id = policy_id;
        this.repair_id = repair_id;
        this.expert_id = expert_id;
        this.description = description;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getInsurance_id() {
        return insurance_id;
    }

    public void setInsurance_id(long insurance_id) {
        this.insurance_id = insurance_id;
    }
}
