package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Client")
@NamedQueries(
        {
                @NamedQuery(
                        name = "getAllClients",
                        query = "SELECT c " +
                                "FROM Client c " +
                                "ORDER BY c.user_id ASC"
                ),
                @NamedQuery(
                        name = "findClientByNif",
                        query = "SELECT c " +
                                "FROM Client c " +
                                "WHERE c.nif = :userNif "
                ),
                @NamedQuery(
                        name = "findClientByNipc",
                        query = "SELECT c " +
                                "FROM Client c " +
                                "WHERE c.nipc = :userNipc "
                ), @NamedQuery(
                name = "findClientByUserId",
                query = "SELECT c " +
                        "FROM Client c " +
                        "WHERE c.user_id = :userID "
        )

        }
)

public class Client extends User implements Serializable {

    String address;
    long nif;
    long nipc;

    public Client() {

    }

    public Client(String name, String email, String password, long phone, String address, long nif, long nipc) {
        super(name, email, password, phone);
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
