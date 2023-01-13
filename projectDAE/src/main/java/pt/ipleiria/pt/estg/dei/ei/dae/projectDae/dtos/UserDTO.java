package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos;

import org.hibernate.Hibernate;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Insurance;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.User;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class UserDTO {

    @Id
    //@GeneratedValue
    private long user_id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String user_type;

    private String address;

    private long nif;

    private long nipc;

    private long share_capital;
    private long phone;

    String insuranceName;

    public UserDTO() {
    }

    public UserDTO(long user_id, String name, String email, String password, String user_type, long phone) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.user_type = user_type;
        this.phone = phone;
    }

    public UserDTO(long user_id, String name, String email, String password, String user_type, String address, long nif, long nipc, long phone) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.user_type = user_type;
        this.address = address;
        this.nif = nif;
        this.nipc = nipc;
        this.phone = phone;
    }

    public UserDTO(long user_id, String name, String email, String password, String user_type, String address, long share_capital, long phone) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.user_type = user_type;
        this.address = address;
        this.share_capital = share_capital;
        this.phone = phone;
    }

    public UserDTO(long user_id, String name, String email, String password, String user_type, long phone, String insuranceName) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.user_type = user_type;
        this.phone = phone;
        this.insuranceName = insuranceName;
    }

    public static UserDTO from(User user) {
        return new UserDTO(
                user.getUser_id(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                Hibernate.getClass(user).getSimpleName(),
                user.getPhone()
        );
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public long getShare_capital() {
        return share_capital;
    }

    public void setShare_capital(long share_capital) {
        this.share_capital = share_capital;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getUser_id() {
        return user_id;
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

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}
