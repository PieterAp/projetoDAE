package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Repair extends User implements Serializable  {
    public Repair() {

    }

    public Repair(long user_id,String name, String email, String password, long phone) {
        super(user_id, name, email, password, phone);
    }
}
