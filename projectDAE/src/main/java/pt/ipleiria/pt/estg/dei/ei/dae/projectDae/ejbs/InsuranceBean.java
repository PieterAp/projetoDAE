package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Client;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Insurance;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class InsuranceBean {

    @PersistenceContext
    EntityManager entityManager;


    public Insurance create(String name, String email, String password, long phone, String address, long share_capital) {
        Insurance insurance = new Insurance(name, email, password, phone, address, share_capital);
        entityManager.persist(insurance);
        return insurance;
    }
}
