package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.Id;

public class Policy {

    @Id
    private long policy_id;

    private long  client_id ;

    private long  insurance_entity_id;

    private String policy_type;

    public Policy() {
    }

    public Policy(long policy_id, long client_id, long insurance_entity_id, String policy_type) {
        this.policy_id = policy_id;
        this.client_id = client_id;
        this.insurance_entity_id = insurance_entity_id;
        this.policy_type = policy_type;
    }

    public long getPolicy_id() {
        return policy_id;
    }

    public void setPolicy_id(long policy_id) {
        this.policy_id = policy_id;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public long getInsurance_entity_id() {
        return insurance_entity_id;
    }

    public void setInsurance_entity_id(long insurance_entity_id) {
        this.insurance_entity_id = insurance_entity_id;
    }

    public String getPolicy_type() {
        return policy_type;
    }

    public void setPolicy_type(String policy_type) {
        this.policy_type = policy_type;
    }
}
