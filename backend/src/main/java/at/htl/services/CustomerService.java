package at.htl.services;

import at.htl.dtos.CustomerDto;
import at.htl.entities.Customer;
import at.htl.mappers.CustomerMapper;
import at.htl.repositories.CustomerRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;

    @GET
    @Path("/{id}")
    public CustomerDto getCustomers(@PathParam("id") Long id) {
        Customer customer =  customerRepository.findCustomerById(id);
        return CustomerMapper.toDto(customer);
    }
}
