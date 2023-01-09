package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class UserDTO {
    @Id
    private long user_id;

    private String name;
    private String email;
    private String address;
    private long phone;
    private String user_type;
    private long nif;
    private long nipc;

    public UserDTO() {

    }

    public UserDTO(long user_id, String name, String email, String address, long phone, String user_type, long nif, long nipc) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.user_type = user_type;
        this.nif = nif;
        this.nipc = nipc;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
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
}
