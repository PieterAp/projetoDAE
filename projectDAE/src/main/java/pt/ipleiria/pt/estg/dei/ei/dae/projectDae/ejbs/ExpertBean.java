package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Expert;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.security.Hasher;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ExpertBean {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    private Hasher hasher;
    public Expert create(long user_id, String name, String email, String password, long phone) {
        Expert expert = new Expert(user_id,name, email, hasher.hash(password), phone);
        entityManager.persist(expert);
        return expert;
    }
}
