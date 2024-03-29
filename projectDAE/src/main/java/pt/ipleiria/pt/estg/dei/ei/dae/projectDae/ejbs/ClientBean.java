package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Client;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.security.Hasher;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ClientBean {
    @PersistenceContext
    EntityManager entityManager;

    @Inject
    private Hasher hasher;

    public Client create(String name, String email, String password, long phone, String address, long nif, long nipc) {
        Client client = new Client(name, email, hasher.hash(password), phone, address, nif, nipc);
        entityManager.persist(client);
        return client;
    }

    public Client findUserId(long userID) {
        return (Client) entityManager.createNamedQuery("findClientByUserId")
                .setParameter("userID",userID)
                .getSingleResult();
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

    public boolean isIndividual(long userID) {
        Client client = findUserId(userID);
        if (client.getNif() == 0) {
            return false;
        }
        return true;
    }

    public boolean isCompany(long userID) {
        Client client = findUserId(userID);
        if (client.getNipc() == 0) {
            return false;
        }
        return true;
    }
}
