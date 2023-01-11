package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@DiscriminatorValue("Insurance")
@NamedQueries(
    @NamedQuery(
            name = "getAllUsersInsurance",
            query = "SELECT i " +
                    "FROM Insurance i " +
                    "ORDER BY i.user_id ASC"
    )
)
public class Insurance extends User implements Serializable {
    private String address;
    private long share_capital;

    public Insurance() {

    }

    public Insurance(long user_id, String name, String email, String password, long phone, String address, long share_capital) {
        super(user_id, name, email, password, phone);
        this.address = address;
        this.share_capital = share_capital;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getShare_capital() {
        return share_capital;
    }

    public void setShare_capital(long share_capital) {
        this.share_capital = share_capital;
    }
}
