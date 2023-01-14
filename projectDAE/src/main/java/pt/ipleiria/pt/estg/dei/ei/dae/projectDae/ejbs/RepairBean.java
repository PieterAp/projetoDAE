package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Insurance;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Repair;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.User;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.security.Hasher;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class RepairBean {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    private Hasher hasher;
    public Repair create(String name, String email, String password, long phone) {
        Repair repair = new Repair(name, email, hasher.hash(password), phone);
        entityManager.persist(repair);
        return repair;
    }

    public Repair find(long user_id) {
        return entityManager.find(Repair.class, user_id);
    }

}
