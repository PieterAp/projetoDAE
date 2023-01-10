package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements Serializable {

    @Id
    @GeneratedValue
    private long user_id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;

    private long phone;

    public User() {
    }

    public User(String name, String email, String password, long phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
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
}
