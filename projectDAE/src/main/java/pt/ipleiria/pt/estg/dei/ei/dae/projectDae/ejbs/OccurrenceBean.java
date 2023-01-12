package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Occurrence;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Policy;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class OccurrenceBean {

    @PersistenceContext
    EntityManager entityManager;

    @EJB
    PolicyBean policyBean;

    public List<Occurrence> getAllOccurrences() {
        return (List<Occurrence>) entityManager.createNamedQuery("getAllOccurrences").getResultList();
    }

    public List<Occurrence> getAllUserOccurrences(long user_id) {
        return (List<Occurrence>) entityManager.createNamedQuery("getAllUserOccurrences")
                .setParameter("user_id", user_id)
                .getResultList();
    }

    public List<Occurrence> getAllInsuranceOccurrences(long insurance_id) {
        return (List<Occurrence>) entityManager.createNamedQuery("getAllInsuranceOccurrences")
                .setParameter("insurance_id", insurance_id)
                .getResultList();
    }

    public List<Occurrence> getAllRepairOccurrences(long repair_id) {
        return (List<Occurrence>) entityManager.createNamedQuery("getAllRepairOccurrences")
                .setParameter("repair_id", repair_id)
                .getResultList();
    }

    public List<Occurrence> getAllExpertOccurrences(long expert_id) {
        return (List<Occurrence>) entityManager.createNamedQuery("getAllExpertOccurrences")
                .setParameter("expert_id", expert_id)
                .getResultList();
    }

    public Occurrence create(long client_id, long policy_id, String description, String status) {
        Policy foundPolicy = policyBean.find(policy_id);
        Occurrence occurrence = new Occurrence(client_id, foundPolicy.getInsurance_id(), policy_id, description, "Submitted");
        entityManager.persist(occurrence);
        return occurrence;

    }

    //todo: WARNING, ONLY UPDATES THE STATUS!!
    public boolean update(long occurrenceid, String status, long repair_id, long expert_id) {
        Occurrence occurrence = find(occurrenceid);
        occurrence.setStatus(status);
        occurrence.setRepair_id(repair_id);
        occurrence.setExpert_id(expert_id);

        Occurrence editedOccurrence = entityManager.find(Occurrence.class, occurrenceid);
        if (!editedOccurrence.getStatus().equals(status)) {
            return false;
        }
        return true;
    }

    public Occurrence find(long occurrenceid) {
        return entityManager.find(Occurrence.class, occurrenceid);
    }

}
