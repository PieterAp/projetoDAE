package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Client;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Insurance;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.security.Hasher;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class InsuranceBean {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    private Hasher hasher;
    public Insurance create(String name, String email, String password, long phone, String address, long share_capital) {
        Insurance insurance = new Insurance(name, email, hasher.hash(password), phone, address, share_capital);
        entityManager.persist(insurance);
        return insurance;
    }


    public Insurance findUserId(long userID) {
        return (Insurance) entityManager.createNamedQuery("findInsuranceByUserId")
                .setParameter("userID",userID)
                .getSingleResult();
    }
    public List<Insurance> getAllInsurances() {
        return (List<Insurance>) entityManager.createNamedQuery("getAllUsersInsurance").getResultList();
    }
}
