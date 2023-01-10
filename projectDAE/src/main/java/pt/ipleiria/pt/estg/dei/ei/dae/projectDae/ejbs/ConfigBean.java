package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Policy;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.User;

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
    UserBean userBean;

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
        clientBean.create(23,"Mariana","mariana@mail.pt","123",962547452,"Leiria",45215786,0);
        User clientInd = userBean.findByNif(45215786);
        clientBean.create(35,"Margarida","margarida@mail.pt","123",985234568,"Santarém",265417929,0);
        clientBean.create(29,"Joaquim","joaquim@mail.pt","123",914257865,"Lisboa",0,1618198192);
        User clientEmp = userBean.findByNipc(1618198192);

        insuranceBean.create(1,"Generali","Generali@seguros.pt","123",967452156,"Lisboa",10000);

        expertBean.create( 2 ,"Expert - Leiria Auto","expert@mail.pt","123",985214526);

        repairBean.create(3,"Repair","Repair@mail.pt","123",958536956);
        //endregion

        //region occurrences
        //For individual client
        List<Policy> policiesClientInd = policyBean.getPoliciesByUserId(clientInd.getUser_id());
        if (policiesClientInd.size()>0) {
            occurrenceBean.create(clientInd.getUser_id(), 1, policiesClientInd.get(0).getId(), "Danos no carro","Submitted");
        }else{
            System.out.println("Client " + clientInd.getUser_id() + " does not have policies");
        }

        //For enterprise client
        List<Policy> policiesClientEmp = policyBean.getPoliciesByUserId(clientEmp.getUser_id());
        if (policiesClientEmp.size()>0) {
            occurrenceBean.create(clientEmp.getUser_id(), 1, policiesClientInd.get(0).getId(), "Incendio no armazem","Submitted");
        }else{
            System.out.println("Client " + clientEmp.getUser_id() + " does not have policies");
        }
        //endregion occurrences
    }
}

