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

    public User find(long user_id) {
        return entityManager.find(User.class, user_id);
    }
}
