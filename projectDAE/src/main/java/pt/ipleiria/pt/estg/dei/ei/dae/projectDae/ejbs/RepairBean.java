package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import org.hibernate.Hibernate;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.*;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Insurance;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Repair;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.User;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.security.Hasher;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class RepairBean {

    @PersistenceContext
    EntityManager entityManager;

    @EJB
    OccurrenceBean occurrenceBean;

    @Inject
    private Hasher hasher;
    public Repair create(String name, String email, String password, long phone, long insurance_user_repair_id) {
        Repair repair = new Repair(name, email, hasher.hash(password), phone, insurance_user_repair_id);
        entityManager.persist(repair);
        return repair;
    }

    public List<Repair> getRepairsAssignInsurance(long occurrance_id) {
        Occurrence foundOccurrence = entityManager.find(Occurrence.class, occurrance_id);

        List<Repair> resuilts =  (List<Repair>) entityManager.createNamedQuery("getRepairsAssignInsurance")
                                            .setParameter("insurance_id",foundOccurrence.getInsurance_id())
                                            .getResultList();

        return  resuilts;
    }
    
    public Repair find(long user_id) {
        return entityManager.find(Repair.class, user_id);
    }

}
