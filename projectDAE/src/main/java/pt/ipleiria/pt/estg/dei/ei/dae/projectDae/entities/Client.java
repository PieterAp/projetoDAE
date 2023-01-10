package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Client extends  User implements Serializable {

    String address;
    long nif;
    long nipc;

    public Client() {
    }

    public Client(long user_id,String name, String email, String password, long phone, String address, long nif, long nipc) {
        super(user_id, name, email, password, phone);
        this.address = address;
        this.nif = nif;
        this.nipc = nipc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getNif() {
        return nif;
    }

    public void setNif(long nif) {
        this.nif = nif;
    }

    public long getNipc() {
        return nipc;
    }

    public void setNipc(long nipc) {
        this.nipc = nipc;
    }
}
