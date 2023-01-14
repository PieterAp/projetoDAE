package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import org.hibernate.Hibernate;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.*;
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
    public Repair create(String name, String email, String password, long phone, Insurance insurance) {
        Repair repair = new Repair(name, email, hasher.hash(password), phone, insurance);
        entityManager.persist(repair);
        return repair;
    }

    public List<Repair> getRepairsAssignInsurance(long occurrance_id) {
        Occurrence foundOccurrence = occurrenceBean.find(occurrance_id);

        System.out.println("I WAS HERE");
        Hibernate.initialize(Document.class);
        System.out.println("NOW i'M NOT");

        return (List<Repair>) entityManager.createNamedQuery("getRepairsAssignInsurance")
                .setParameter("insurance_id",foundOccurrence.getInsurance_id())
                .getResultList();
    }
}
