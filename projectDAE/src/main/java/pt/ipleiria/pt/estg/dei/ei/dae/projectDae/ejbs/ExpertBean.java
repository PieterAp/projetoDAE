package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Expert;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Insurance;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ExpertBean {

    @PersistenceContext
    EntityManager entityManager;


    public Expert create(String name, String email, String password, long phone) {
        Expert expert = new Expert(name, email, password, phone);
        entityManager.persist(expert);
        return expert;
    }
}
