package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Expert;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Insurance;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.User;
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

    public Expert create(String name, String email, String password, long phone, Insurance insurance) {
        Expert expert = new Expert(name, email, hasher.hash(password), phone, insurance);
        entityManager.persist(expert);
        return expert;
    }

    public Expert find(long user_id) {
        return entityManager.find(Expert.class, user_id);
    }

    public Expert findUserId(long userID) {
        return (Expert) entityManager.createNamedQuery("findExpertByUserId")
                .setParameter("userID",userID)
                .getSingleResult();
    }
}
