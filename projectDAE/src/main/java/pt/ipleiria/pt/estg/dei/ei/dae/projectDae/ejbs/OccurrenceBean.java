package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Occurrence;

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

    public Occurrence create (long client_id, long insurance_id, long policy_id, String description, String status) {
        Occurrence occurrence = new Occurrence(client_id, insurance_id, policy_id, description, status);
        entityManager.persist(occurrence);
        return occurrence;
    }

    //todo: WARNING, ONLY UPDATES THE STATUS!!
    public boolean update (long occurrenceid, String newStatus) {
        Occurrence occurrence = find(occurrenceid);
        occurrence.setStatus(newStatus);

        Occurrence editedOccurrence = entityManager.find(Occurrence.class, occurrenceid);
        if (!editedOccurrence.getStatus().equals(newStatus)){
            return false;
        }
        return true;
    }

    public Occurrence find(long occurrenceid) {
        return entityManager.find(Occurrence.class, occurrenceid);
    }

}
