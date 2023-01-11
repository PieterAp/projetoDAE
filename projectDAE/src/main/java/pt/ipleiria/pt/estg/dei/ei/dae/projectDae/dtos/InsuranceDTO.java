package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class InsuranceDTO {
    @Id
    private long user_id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private long phone;
    private String address;
    private long share_capital;

    public InsuranceDTO() {

    }

    public InsuranceDTO(long user_id, String name, String email, String password, long phone, String address, long share_capital) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.share_capital = share_capital;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getShare_capital() {
        return share_capital;
    }

    public void setShare_capital(long share_capital) {
        this.share_capital = share_capital;
    }
}
