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

    public Occurrence create (long client_id, long insurance_id,long policy_id, String description) {
        Occurrence occurrence = new Occurrence(client_id, insurance_id, policy_id, description);
        entityManager.persist(occurrence);
        return occurrence;
    }
}
