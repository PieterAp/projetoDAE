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
                "WHERE r.insurance_user_repair_id.user_id = :insurance_id"
    )
)
public class Repair extends User implements Serializable  {

    @ManyToOne
    @JoinColumn(name="insurance_user_repair_id")
    private Insurance insurance_user_repair_id;

    public Repair() {

    }

    public Repair(String name, String email, String password, long phone, Insurance insurance_user_repair_id) {
        super(name, email, password, phone);
        this.insurance_user_repair_id = insurance_user_repair_id;
    }

    public Insurance getInsurance() {
        return insurance_user_repair_id;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance_user_repair_id = insurance;
    }
}
