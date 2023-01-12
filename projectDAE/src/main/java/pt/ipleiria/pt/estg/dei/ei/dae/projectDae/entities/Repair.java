package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Repair")
public class Repair extends User implements Serializable  {
    public Repair() {

    }

    public Repair(String name, String email, String password, long phone) {
        super(name, email, password, phone);
    }
}
