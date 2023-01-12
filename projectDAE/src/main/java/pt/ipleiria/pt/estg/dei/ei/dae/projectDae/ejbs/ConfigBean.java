package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Client;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Occurrence;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Policy;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.List;

@Singleton
@Startup
public class ConfigBean {
    @EJB
    OccurrenceBean occurrenceBean;

    @EJB
    PolicyBean policyBean;

    @EJB
    ClientBean clientBean;

    @EJB
    InsuranceBean insuranceBean;

    @EJB
    ExpertBean expertBean;

    @EJB
    RepairBean repairBean;

    @PostConstruct
    public void populateDB() {
        System.out.println("Hello Java EE!");

        //region users
        //Individual clients
        clientBean.create(3, "Mariana", "mariana@mail.pt", "123", 962547452, "Leiria", 45215786, 0);
        //User clientInd = userBean.findByNif(45215786);
        Client clientInd = clientBean.findByNif(45215786);

        clientBean.create(39, "Margarida", "margarida@mail.pt", "123", 985234568, "Santarém", 265417929, 0);
        //Enterprise client
        clientBean.create(4, "Joaquim", "joaquim@mail.pt", "123", 914257865, "Lisboa", 0, 1618198192);
        //User clientEmp = userBean.findByNipc(1618198192);
        Client clientEmp = clientBean.findByNipc(1618198192);

        insuranceBean.create(10, "Generali", "generali@seguros.pt", "123", 967452156, "Lisboa", 10000);
        insuranceBean.create(11, "Fidelidade", "fidelidade@seguros.pt", "123", 974521587, "Sintra", 10000);

        expertBean.create(12, "Peritagem André S.A.", "expertAndre@mail.pt", "123", 985214526);
        expertBean.create(13, "Peritagem Sousa e Filhos", "expertSousa@mail.pt", "123", 952528745);

        repairBean.create(14, "Leiria Auto", "repairLeiria@mail.pt", "123", 958536956);
        repairBean.create(15, "MyAuto Mecânica Multimarcas Lisboa", "repairLisboa@mail.pt", "123", 985214257);
        //endregion

        //region occurrences
        //For individual client
        List<Policy> policiesClientInd = policyBean.getPoliciesByUserId(clientInd.getUser_id());
        if (policiesClientInd.size() > 0) {
            occurrenceBean.create(clientInd.getUser_id(), 10, policiesClientInd.get(0).getId(), "Danos no carro após acidente", "Submitted");
            occurrenceBean.create(clientInd.getUser_id(), 11, policiesClientInd.get(0).getId(), "Bicicleta roubada", "Submitted");
        } else {
            System.out.println("Client " + clientInd.getUser_id() + " does not have policies");
        }

        //For enterprise client
        List<Policy> policiesClientEmp = policyBean.getPoliciesByUserId(clientEmp.getUser_id());
        if (policiesClientEmp.size() > 0) {
            Occurrence occurrence = occurrenceBean.create(clientEmp.getUser_id(), 10, policiesClientEmp.get(0).getId(), "Acidente de trabalho, viatura danificada", "Submitted");
            occurrenceBean.create(clientEmp.getUser_id(), 11, policiesClientEmp.get(0).getId(), "Incendio no armazem", "Submitted");
            occurrence.setRepair_id(14);
            occurrence.setExpert_id(12);
            occurrenceBean.update(occurrence.getOccurrence_id(), occurrence.getStatus(), occurrence.getRepair_id(),occurrence.getExpert_id());
        } else {
            System.out.println("Client " + clientEmp.getUser_id() + " does not have policies");
        }
        //endregion occurrences
    }
}

