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
                "WHERE r.insurance_user_repair_id = :insurance_id"
    )
)
public class Repair extends User implements Serializable  {

    private long insurance_user_repair_id;

    public Repair() {

    }

    public Repair(String name, String email, String password, long phone, long insurance_user_repair_id) {
        super(name, email, password, phone);
        this.insurance_user_repair_id = insurance_user_repair_id;
    }

    public long getInsurance_user_repair_id() {
        return insurance_user_repair_id;
    }

    public void setInsurance_user_repair_id(long insurance_user_repair_id) {
        this.insurance_user_repair_id = insurance_user_repair_id;
    }
}
