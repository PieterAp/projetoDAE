package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Client;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ClientBean {

    @PersistenceContext
    EntityManager entityManager;


    public Client create(String name, String email, String password, long phone, String address, long nif, long nipc) {
        Client client = new Client(name, email, password, phone, address, nif, nipc);
        entityManager.persist(client);
        return client;
    }
}
