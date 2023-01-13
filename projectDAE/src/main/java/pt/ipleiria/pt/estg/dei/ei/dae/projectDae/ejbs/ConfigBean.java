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
        clientBean.create( "Mariana", "mariana@mail.pt", "123", 962547452, "Leiria", 244930473, 0);
        Client clientInd = clientBean.findByNif(244930473);
        clientBean.create("Margarida", "margarida@mail.pt", "123", 985234568, "Santarém", 289437059, 0);
        clientBean.create("Inês", "ines@mail.pt", "123", 964125475, "Portimão", 275991237, 0);

        //Enterprise client
        clientBean.create( "Joaquim", "joaquim@mail.pt", "123", 914257865, "Lisboa", 0, 555479250);
        Client clientEmp = clientBean.findByNipc(555479250);
        clientBean.create( "Marco", "marco@mail.pt", "123", 925425365, "Braga", 0, 508461022);

        insuranceBean.create("Generali", "generali@mail.pt", "123", 967452156, "Lisboa", 10000);
        insuranceBean.create("Fidelidade", "fidelidade@mail.pt", "123", 974521587, "Sintra", 10000);
        insuranceBean.create("okteleseguros", "okteleseguros@mail.pt", "123", 952151425, "Porto", 10000);

        expertBean.create("Peritagem André S.A.", "expertAndre@mail.pt", "123", 985214526);
        expertBean.create("Peritagem Sousa e Filhos", "expertSousa@mail.pt", "123", 952528745);

        repairBean.create("Leiria Auto", "repairLeiria@mail.pt", "123", 958536956);
        repairBean.create("MyAuto Mecânica Multimarcas Lisboa", "repairLisboa@mail.pt", "123", 985214257);
        //endregion

        //region occurrences
        //For individual client
        List<Policy> policiesClientInd = policyBean.getPoliciesByUserNif(clientInd.getNif());
        if (policiesClientInd.size() > 0) {
            occurrenceBean.create(clientInd.getUser_id(), policiesClientInd.get(0).getId(), "Acidente causado após outra viatura embater na traseira");
            occurrenceBean.create(clientInd.getUser_id(), policiesClientInd.get(1).getId(), "Infiltração prolongada de água provocou danos estruturais");
        } else {
            System.out.println("Client " + clientInd.getUser_id() + " does not have policies");
        }

        //For enterprise client
        List<Policy> policiesClientEmp = policyBean.getPoliciesByUserNipc(clientEmp.getNipc());
        if (policiesClientEmp.size() > 0) {
            Occurrence occurrence = occurrenceBean.create(clientEmp.getUser_id(), policiesClientEmp.get(0).getId(), "Acidente de trabalho, viatura danificada");
            occurrenceBean.create(clientEmp.getUser_id(), policiesClientEmp.get(2).getId(), "Incêndio no armazem");
            occurrence.setRepair_id(9);
            occurrence.setExpert_id(11);
            occurrenceBean.update(occurrence.getOccurrence_id(), occurrence.getStatus(), occurrence.getRepair_id(),occurrence.getExpert_id());
        } else {
            System.out.println("Client " + clientEmp.getUser_id() + " does not have policies");
        }
        //endregion occurrences
    }
}

