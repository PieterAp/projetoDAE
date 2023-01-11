package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos;

import org.hibernate.Hibernate;
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
    private long phone;

    public UserDTO() {
    }

    public UserDTO(long user_id, String name, String email, String password, String user_type,long phone) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.user_type = user_type;
        this.phone = phone;
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
