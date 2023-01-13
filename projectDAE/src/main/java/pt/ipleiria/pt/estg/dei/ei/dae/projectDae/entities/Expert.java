package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Expert")
@NamedQueries(
        {
                @NamedQuery(
                        name = "findExpertByUserId",
                        query = "SELECT e " +
                                "FROM Expert e " +
                                "WHERE e.user_id = :userID"
                )}
)
public class Expert extends User implements Serializable {

    @ManyToOne
    private Insurance insurance;

    public Expert() {
    }

    public Expert(String name, String email, String password, long phone, Insurance insurance) {
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
