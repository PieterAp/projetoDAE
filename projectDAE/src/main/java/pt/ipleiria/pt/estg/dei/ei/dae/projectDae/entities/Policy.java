package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.Id;

public class Policy {
    @Id
    private long id;
    private String description;
    private long nif;
    private long nipc;
    private String type;
    private String createdAt;
    private long insurance_id;

    public Policy() {

    }

    public Policy(long id, long nif, long nipc, long insurance_id, String description, String type, String createdAt) {
        this.id = id;
        this.nif = nif;
        this.nipc = nipc;
        this.insurance_id = insurance_id;
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

    public long getNif() {
        return nif;
    }

    public void setNif(long nif) {
        this.nif = nif;
    }

    public long getNipc() {
        return nipc;
    }

    public void setNipc(long nipc) {
        this.nipc = nipc;
    }

    public long getInsurance_id() {
        return insurance_id;
    }

    public void setInsurance_id(long insurance_id) {
        this.insurance_id = insurance_id;
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
}
