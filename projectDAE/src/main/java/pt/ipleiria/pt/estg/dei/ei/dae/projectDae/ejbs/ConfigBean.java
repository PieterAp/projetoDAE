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

    @PostConstruct
    public void populateDB() {
        System.out.println("Hello Java EE!");

        userBean.create(23,"Mariana","mariana@mail.pt","Leiria", 962547452, "Client", 45215786,0);
        User client = userBean.findByNif(45215786);
        userBean.create(35,"Margarida","margarida@mail.pt","Santarém", 985234568, "Client", 265417929,0);
        userBean.create(29,"Joaquim","joaquim@mail.pt","Lisboa", 914257865, "Client", 1618198192,0);

        userBean.create(12,"Generali","Generali@seguros.pt","Leiria", 967452156, "Insurance",66325415,0);
        User insuranceCompany = userBean.findByNif(66325415);

        List<Policy> policies = policyBean.getPoliciesByUserId(client.getUser_id());
        if (policies.size()>0) {
            occurrenceBean.create(client.getUser_id(), insuranceCompany.getUser_id(), policies.get(0).getId(), "Danos no carro","Submitted");
        }else{
            System.out.println("Could not create occurence, no policies found for created user");
        }


    }
}

