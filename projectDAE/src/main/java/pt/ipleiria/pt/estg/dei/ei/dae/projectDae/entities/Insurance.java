package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.Id;
import java.io.Serializable;

public class Insurance implements Serializable {
    @Id
    private long insurance_entity_id;

    private String name;
    private String email;
    private String address;
    private long phone;
    private long share_capital;


    public Insurance() {
    }

    public Insurance(long insurance_entity_id, String name, String email, String address, long phone, long share_capital) {
        this.insurance_entity_id = insurance_entity_id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.share_capital = share_capital;
    }


    public long getInsurance_entity_id() {
        return insurance_entity_id;
    }

    public void setInsurance_entity_id(long insurance_entity_id) {
        this.insurance_entity_id = insurance_entity_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public long getShare_capital() {
        return share_capital;
    }

    public void setShare_capital(long share_capital) {
        this.share_capital = share_capital;
    }
}
