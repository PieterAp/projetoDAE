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

    private String clientName;
    private String insuranceName;

    private String policyName;

    private String expertName;

    private String repairName;

    public OccurrenceDTO() {

    }

    public OccurrenceDTO(long occurrence_id,String clientName ,long client_id, long insurance_id, long policy_id, long repair_id, long expert_id, String description, String status, String insuranceName, String policyName, String expertName, String repairName) {
        this.clientName = clientName;
        this.occurrence_id = occurrence_id;
        this.client_id = client_id;
        this.insurance_id = insurance_id;
        this.policy_id = policy_id;
        this.repair_id = repair_id;
        this.expert_id = expert_id;
        this.description = description;
        this.status = status;
        this.insuranceName = insuranceName;
        this.policyName = policyName;
        this.expertName = expertName;
        this.repairName = repairName;
    }

    public OccurrenceDTO(long occurrence_id, String clientName,long client_id, long insurance_id, long policy_id, long repair_id, long expert_id, String description, String status, String insuranceName, String policyName) {
        this.clientName = clientName;
        this.occurrence_id = occurrence_id;
        this.client_id = client_id;
        this.insurance_id = insurance_id;
        this.policy_id = policy_id;
        this.repair_id = repair_id;
        this.expert_id = expert_id;
        this.description = description;
        this.status = status;
        this.insuranceName = insuranceName;
        this.policyName = policyName;
    }

    public OccurrenceDTO(long occurrence_id, String clientName,long client_id, long insurance_id, long policy_id, long repair_id, long expert_id, String description, String status, String insuranceName, String policyName, String expertName) {
        this.clientName = clientName;
        this.occurrence_id = occurrence_id;
        this.client_id = client_id;
        this.insurance_id = insurance_id;
        this.policy_id = policy_id;
        this.repair_id = repair_id;
        this.expert_id = expert_id;
        this.description = description;
        this.status = status;
        this.insuranceName = insuranceName;
        this.policyName = policyName;
        this.expertName = expertName;
    }


    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getRepairName() {
        return repairName;
    }

    public void setRepairName(String repairName) {
        this.repairName = repairName;
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
