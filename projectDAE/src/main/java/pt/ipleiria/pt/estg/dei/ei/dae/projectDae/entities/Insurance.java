package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.Entity;
import java.io.Serializable;
@Entity
public class Insurance extends User implements Serializable {

    private String address;
    private long share_capital;

    public Insurance() {
    }

    public Insurance(String name, String email, String password, long phone, String address, long share_capital) {
        super(name, email, password, phone);
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
