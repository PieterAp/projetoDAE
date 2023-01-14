package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Insurance;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class RepairDTO {
    @Id
    private long user_id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private long phone;

    private Insurance insurance;

    public RepairDTO() {

    }

    public RepairDTO(long user_id, String name, String email, String password, long phone, Insurance insurance) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.insurance = insurance;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
}
