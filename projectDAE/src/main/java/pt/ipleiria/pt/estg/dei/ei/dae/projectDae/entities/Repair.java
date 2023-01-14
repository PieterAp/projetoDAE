package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Repair")
@NamedQueries(
    @NamedQuery(
        name = "getRepairsAssignInsurance",
        query = "SELECT r " +
                "FROM Repair r " +
                "WHERE r.insurance.user_id = :insurance_id"
    )
)
public class Repair extends User implements Serializable  {

    @ManyToOne
    private Insurance insurance;

    public Repair() {

    }

    public Repair(String name, String email, String password, long phone, Insurance insurance) {
        super(name, email, password, phone);
        this.insurance = insurance;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
}
