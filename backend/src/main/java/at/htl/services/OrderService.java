package at.htl.services;

import at.htl.dtos.EmployeeDto;
import at.htl.dtos.OrderDto;
import at.htl.dtos.OrderEmployeeDto;
import at.htl.dtos.PostOrderDto;
import at.htl.entities.*;
import at.htl.mappers.OrderMapper;
import at.htl.repositories.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderService {

    @Inject
    OrderRepository orderRepository;
    @Inject
    CustomerRepository customerRepository;
    @Inject
    DrinkRepository drinkRepository;
    @Inject
    DishRepository dishRepository;
    @Inject
    EmployeeRepository employeeRepository;

    @POST
    public OrderDto addOrder(PostOrderDto dto) {
        List<Drink> drinks = drinkRepository.findByIdList(dto.getDrinkIds());
        List<Dish> dishes = dishRepository.findByIdList(dto.getDishIds());
        Customer customer = customerRepository.findCustomerById(dto.getCustomerId());

        Order order = OrderMapper.fromPostDto(dto, dishes, drinks, customer);

        List<Employee> employees = employeeRepository.getEmployeesWithLeastOrder();

        orderRepository.save(order);

        for(Employee e: employees) {
            e.addOrder(order);
            employeeRepository.update(e);
        }

        return OrderMapper.toDto(order);
    }

    @GET()
    @Path("/Employee/latest/{id}")
    public OrderEmployeeDto getLatestEmployeeOrder(@PathParam("id") Long id) {
        Order order = orderRepository.findLatestByEmployee(id);
        if(order == null) {
            throw new WebApplicationException("Keine Bestellung gefunden", Response.Status.NOT_FOUND);
        }
        else if (order.getId() == null) {
            return null;
        }
        return OrderMapper.toEmployeeDto(order);
    }

    @GET
    @Path("/Employee/all/{id}")
    public List<OrderEmployeeDto> getAllEmployeeOrders(@PathParam("id") Long id) {
        List<Order> orders = orderRepository.findAllByEmployee(id);
        if(orders == null) {
            throw new WebApplicationException("Keine Bestellung gefunden", Response.Status.NOT_FOUND);
        }
        else if (orders.isEmpty()) {
            return null;
        }
        return OrderMapper.toEmployeeDtoList(orders);
    }

    @GET()
    @Path("/Customer/latest/{id}")
    public OrderDto getLatestCustomerOrder(@PathParam("id") Long id) {
        Order order = orderRepository.findLatestByCustomer(id);
        if(order == null) {
            throw new WebApplicationException("Keine Bestellung gefunden", Response.Status.NOT_FOUND);
        }
        else if (order.getId() == null) {
            return null;
        }
        return OrderMapper.toDto(order);
    }

    @GET
    @Path("/Customer/all/{id}")
    public List<OrderDto> getAllCustomerOrders(@PathParam("id") Long id) {
        List<Order> orders = orderRepository.findAllByCustomer(id);
        if(orders == null) {
            throw new WebApplicationException("Keine Bestellung gefunden", Response.Status.NOT_FOUND);
        }
        else if(orders.isEmpty()) {
            return null;
        }
        return OrderMapper.toDtoList(orders);
    }
}
