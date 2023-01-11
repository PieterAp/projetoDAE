package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Client;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ClientBean {
    @PersistenceContext
    EntityManager entityManager;

    public Client create(long user_id, String name, String email, String password, long phone, String address, long nif, long nipc) {
        Client client = new Client(user_id, name, email, password, phone, address, nif, nipc);
        entityManager.persist(client);
        return client;
    }

    public Client findByNif(long nif) {
        return (Client) entityManager.createNamedQuery("findClientByNif")
                .setParameter("userNif",nif)
                .getSingleResult();
    }

    public Client findByNipc(long nipc) {
        return (Client) entityManager.createNamedQuery("findClientByNipc")
                .setParameter("userNipc",nipc)
                .getSingleResult();
    }
}
