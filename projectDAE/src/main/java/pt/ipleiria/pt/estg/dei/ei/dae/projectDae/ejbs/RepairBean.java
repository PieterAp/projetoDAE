package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Repair;
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
    public Repair create(long user_id, String name, String email, String password, long phone) {
        Repair repair = new Repair(user_id, name, email, hasher.hash(password), phone);
        entityManager.persist(repair);
        return repair;
    }
}
