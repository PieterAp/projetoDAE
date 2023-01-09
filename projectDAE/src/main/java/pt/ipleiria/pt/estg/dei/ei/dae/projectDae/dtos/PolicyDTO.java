package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos;

import javax.persistence.Id;

public class PolicyDTO {

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

    public PolicyDTO() {

    }

    public PolicyDTO(long id, long user_id, String description, String type, String createdAt) {
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
}
