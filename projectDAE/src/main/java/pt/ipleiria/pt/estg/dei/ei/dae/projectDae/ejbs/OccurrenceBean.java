package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import org.hibernate.Hibernate;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Occurrence;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Policy;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Stateless
public class OccurrenceBean {

    @PersistenceContext
    EntityManager entityManager;

    @EJB
    PolicyBean policyBean;

    @EJB
    UserBean userBean;

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

    public List<Occurrence> getAllExpertOccurrences(long expert_id,long insurance_id) {
        return (List<Occurrence>) entityManager.createNamedQuery("getAllExpertOccurrences")
                .setParameter("expert_id", expert_id)
                .setParameter("insurance_id", insurance_id)
                .getResultList();
    }

    public List<Occurrence> getOccurrencesByPolicy(long policy_id) {
        List<Occurrence> occurrences = entityManager.createNamedQuery("getOccurrencesByPolicy")
                .setParameter("policy_id", policy_id)
                .getResultList();

        Hibernate.initialize(occurrences);
        return occurrences;
    }

    public Object getOccurrenceDetails(long occurrenceID) {
        Occurrence occurrence = find(occurrenceID);
        String repairName = "";
        String expertName = "";
        String policyDescription = "";

        String clientName = userBean.find(occurrence.getClient_id()).getName();
        String insuranceName = userBean.find(occurrence.getInsurance_id()).getName();
        if (occurrence.getRepair_id() != 0) {
            repairName = userBean.find(occurrence.getRepair_id()).getName();
        }
        if (occurrence.getExpert_id() != 0) {
            expertName = userBean.find(occurrence.getExpert_id()).getName();
        }
        if (occurrence.getPolicy_id() != 0) {
            policyDescription = policyBean.find(occurrence.getPolicy_id()).getDescription();
        }


        JsonObject occurrenceDetails = Json.createObjectBuilder()
                .add("occurrence_id", occurrence.getOccurrence_id())
                .add("status", occurrence.getStatus())
                .add("description", occurrence.getDescription())
                .add("clientName", clientName)
                .add("insuranceName", insuranceName)
                .add("repairName", repairName)
                .add("expertName", expertName)
                .add("policyDescription", policyDescription)
                .add("repair_id",occurrence.getRepair_id())
                .build();

        return occurrenceDetails;
    }

    public Occurrence create(long client_id, long policy_id, String description) {
        Policy policy = policyBean.find(policy_id);
        Occurrence occurrence = new Occurrence(client_id, policy.getInsurance_id(), policy_id, description, "Submitted");
        entityManager.persist(occurrence);
        return occurrence;

    }

    public boolean update(long occurrenceid, String status, long repair_id, long expert_id) {
        Occurrence occurrence = find(occurrenceid);

        if (status != null)
            occurrence.setStatus(status);
        occurrence.setRepair_id(repair_id);
        if (expert_id != 0)
            occurrence.setExpert_id(expert_id);

        return true;
    }

    public Occurrence find(long occurrenceid) {
        return entityManager.find(Occurrence.class, occurrenceid);
    }

    public Occurrence findOrFail(long id) {
        var occurrence = entityManager.getReference(Occurrence.class, id);
        Hibernate.initialize(occurrence);

        return occurrence;
    }

}
