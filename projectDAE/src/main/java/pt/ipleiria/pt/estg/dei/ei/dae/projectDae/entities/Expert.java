package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Expert")
public class Expert extends User implements Serializable {

    public Expert() {

    }

    public Expert(long user_id, String name, String email, String password, long phone) {
        super(user_id, name, email, password, phone);
    }

}
