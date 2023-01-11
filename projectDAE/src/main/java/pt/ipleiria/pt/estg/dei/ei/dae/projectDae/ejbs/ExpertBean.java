package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Expert;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ExpertBean {

    @PersistenceContext
    EntityManager entityManager;

    public Expert create(long user_id, String name, String email, String password, long phone) {
        Expert expert = new Expert(user_id,name, email, password, phone);
        entityManager.persist(expert);
        return expert;
    }
}
