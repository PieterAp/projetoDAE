package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Insurance")
@NamedQueries(
        {
                @NamedQuery(
                        name = "getAllUsersInsurance",
                        query = "SELECT i " +
                                "FROM Insurance i " +
                                "ORDER BY i.user_id ASC"
                ), @NamedQuery(
                name = "findInsuranceByUserId",
                query = "SELECT i " +
                        "FROM Insurance i " +
                        "WHERE i.user_id = :userID"
        )}
)
public class Insurance extends User implements Serializable {
    private String address;
    private long share_capital;

    @OneToMany(mappedBy = "insurance", fetch = FetchType.EAGER)
    private List<Expert> experts;

    @OneToMany(mappedBy = "insurance", fetch = FetchType.EAGER)
    private List<Repair> repairs;

    public Insurance() {
        this.experts = new ArrayList<>();
        this.repairs = new ArrayList<>();
    }

    public Insurance(String name, String email, String password, long phone, String address, long share_capital) {
        super(name, email, password, phone);
        this.address = address;
        this.share_capital = share_capital;
        this.experts = new ArrayList<>();
        this.repairs = new ArrayList<>();
    }

    public List<Expert> getExperts() {
        return experts;
    }

    public void setExperts(List<Expert> experts) {
        this.experts = experts;
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

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }
}
