package at.htl.services;

import at.htl.dtos.LoginDto;
import at.htl.dtos.PostLoginDto;
import at.htl.entities.Customer;
import at.htl.entities.DepManager;
import at.htl.entities.Employee;
import at.htl.repositories.LoginRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginService {
    @Inject
    LoginRepository loginRepository;

    @POST
    public LoginDto login(PostLoginDto dto){
        if(dto.getUsername().length() != 4) {
            throw new WebApplicationException("Password falsch oder User falsch", Response.Status.NOT_FOUND);
        }

        LoginDto loginDto = new LoginDto();
        if(dto.getUsername().charAt(2)=='0'){
            DepManager depManager = loginRepository.findDepManager(dto.getPassword());
            if(depManager!=null){
                if(depManager.getUserName().equals(dto.getUsername())){
                    loginDto.setId(depManager.getId());
                    loginDto.setType("DepManager");
                }
            }
            else{
                throw new WebApplicationException("Password falsch oder User falsch", Response.Status.NOT_FOUND);
            }
        }
        else if(dto.getUsername().charAt(2)=='1'){
            Customer customer = loginRepository.findCustomer(dto.getPassword());
            if(customer!=null){
                if(customer.getUserName().equals(dto.getUsername())){
                    loginDto.setId(customer.getId());
                    loginDto.setType("Customer");
                }
            }
            else{
                throw new WebApplicationException("Password falsch oder User falsch", Response.Status.NOT_FOUND);
            }
        }
        else if(dto.getUsername().charAt(2)=='2'){
            Employee employee = loginRepository.findEmployee(dto.getPassword());
            if(employee!=null){
                if(employee.getUserName().equals(dto.getUsername())){
                    loginDto.setId(employee.getId());
                    loginDto.setType("Employee");
                }
            }
            else{
                throw new WebApplicationException("Password falsch oder User falsch", Response.Status.NOT_FOUND);
            }
        }
        else {
            throw new WebApplicationException("Password falsch oder User falsch", Response.Status.NOT_FOUND);
        }
        return loginDto;
    }
}