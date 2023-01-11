package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Repair;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RepairBean {

    @PersistenceContext
    EntityManager entityManager;


    public Repair create(long user_id, String name, String email, String password, long phone) {
        Repair repair = new Repair(user_id, name, email, password, phone);
        entityManager.persist(repair);
        return repair;
    }
}
