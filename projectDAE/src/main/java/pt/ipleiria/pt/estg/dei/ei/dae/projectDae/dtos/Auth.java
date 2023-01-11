package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class Auth implements Serializable {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public Auth() {}

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
}
