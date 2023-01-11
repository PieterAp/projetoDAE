package pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ws;

import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.dtos.InsuranceDTO;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.ejbs.InsuranceBean;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.entities.Insurance;
import pt.ipleiria.pt.estg.dei.ei.dae.projectDae.security.Authenticated;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("insurances")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Authenticated
public class InsuranceService {
    @EJB
    InsuranceBean insuranceBean;

    @GET
    @Path("/")
    public List<InsuranceDTO> getAllInsuranceComps() {
        return toDTOs(insuranceBean.getAllInsurances());
    }

    private InsuranceDTO toDTO(Insurance insurance) {
        return new InsuranceDTO(
                insurance.getUser_id(),
                insurance.getName(),
                insurance.getEmail(),
                insurance.getPassword(),
                insurance.getPhone(),
                insurance.getAddress(),
                insurance.getShare_capital()
        );
    }

    private List<InsuranceDTO> toDTOs(List<Insurance> insurances) {
        return insurances.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
