package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserBean {

    @PersistenceContext
    EntityManager entityManager;

    public List<User> getAllUsers() {
        return (List<User>) entityManager.createNamedQuery("getAllUsers").getResultList();
    }

    public User create (long user_id ,String name, String email, String address, long phone, String user_type, long nif, long nipc) {
        User user = new User(user_id,name, email, address, phone, user_type, nif, nipc);
        entityManager.persist(user);
        return user;
    }

    public User find(long user_id) {
        return entityManager.find(User.class, user_id);
    }

    public User findByNif(long nif) {
        return (User) entityManager.createNamedQuery("findUserByNif")
                                    .setParameter("userNif",nif)
                                    .getSingleResult();
    }

    public List<User> findAllInsuranceComp() {
        return (List<User>) entityManager.createNamedQuery("findInsuranceComp")
                .setParameter("insuranceComp","Insurance")
                .getResultList();
    }
}
