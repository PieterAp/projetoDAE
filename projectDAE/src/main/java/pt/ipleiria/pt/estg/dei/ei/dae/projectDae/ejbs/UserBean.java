package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import org.hibernate.Hibernate;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.User;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.security.Hasher;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserBean {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    private Hasher hasher;

    public User find(String email) {

        User foundUser =(User) entityManager.createNamedQuery("findUserEmail")
                .setParameter("email",email)
                .getSingleResult();

        System.out.println("BATATAS com fiambre");
        System.out.println("THIS IS THE MAIKL " + foundUser.getName());
        System.out.printf(foundUser.getName());

        return foundUser;
    }

    public User findOrFail(String email) {
        User user = find(email);
        Hibernate.initialize(entityManager.getReference(User.class, user.getUser_id()));

        return user;
    }

    public List<User> getAllUsers() {
        return (List<User>) entityManager.createNamedQuery("getAllUsers").getResultList();
    }

    public User find(long user_id) {
        return entityManager.find(User.class, user_id);
    }


    public boolean canLogin(String email, String password) {
        var user = find(email);
        return user != null && user.getPassword().equals(hasher.hash(password));
    }
}
