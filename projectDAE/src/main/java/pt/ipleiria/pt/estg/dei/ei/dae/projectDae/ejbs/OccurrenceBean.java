package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Occurrence;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class OccurrenceBean {

    @PersistenceContext
    EntityManager entityManager;

    public List<Occurrence> getAllOccurrences() {
        return (List<Occurrence>) entityManager.createNamedQuery("getAllOccurrences").getResultList();
    }

    public Occurrence create (long client_id, long insurance_id,long policy_id, String description, String status) {
        Occurrence occurrence = new Occurrence(client_id, insurance_id, policy_id, description, status);
        entityManager.persist(occurrence);
        return occurrence;
    }

    public boolean update (long occurrenceid, String newStatus) {
        Occurrence occurrence = find(occurrenceid);
        occurrence.setStatus(newStatus);

        Occurrence editedCourse = entityManager.find(Occurrence.class, occurrenceid);
        if (!editedCourse.getStatus().equals(newStatus)){
            return false;
        }
        return true;
    }

    public Occurrence find(long occurrenceid) {
        return entityManager.find(Occurrence.class, occurrenceid);
    }

}
