package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class ConfigBean {

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

        clientBean.create("client","client@mail.pt","123",912222222,"teste street",238273918,0);
        clientBean.create("client E","client_E@mail.pt","123",912222222,"teste street",0,238273918);

        insuranceBean.create("Insurance","Insurance@mail.pt","123",912222222,"teste street",10000);

        expertBean.create("Expert","Expert@mail.pt","123",912222222);

        repairBean.create("Repair","Repair@mail.pt","123",912222222);

    }


}

