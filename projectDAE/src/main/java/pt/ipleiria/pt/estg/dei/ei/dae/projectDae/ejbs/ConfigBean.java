package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class ConfigBean {

    @PostConstruct
    public void populateDB() {
        System.out.println("Hello Java EE!");
    }
}

