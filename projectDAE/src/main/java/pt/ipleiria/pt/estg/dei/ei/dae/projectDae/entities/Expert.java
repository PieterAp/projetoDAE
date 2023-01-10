package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Expert extends User implements Serializable {

    public Expert() {
    }

    public Expert(String name, String email, String password, long phone) {
        super(name, email, password, phone);
    }

}
