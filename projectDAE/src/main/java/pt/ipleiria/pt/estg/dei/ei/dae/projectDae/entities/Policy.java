package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.Id;

public class Policy {

    /*
        "createdAt": "2022-12-22T03:34:31.523Z",
        "type": "type 1",
        "user_id": 39,
        "description": "description 1",
        "id": "1"
     */
    @Id
    private long id;
    private long user_id;
    private String description;
    private String type;
    private String createdAt;

    public Policy() {

    }

    public Policy(long id, long user_id, String description, String type, String createdAt) {
        this.id = id;
        this.user_id = user_id;
        this.description = description;
        this.type = type;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /*
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
    */
}
