package at.htl.services;

import at.htl.dtos.EmployeeDto;
import at.htl.dtos.PostEmployeeDto;
import at.htl.dtos.SalaryDto;
import at.htl.entities.DepManager;
import at.htl.entities.Department;
import at.htl.entities.Employee;
import at.htl.mappers.EmployeeMapper;
import at.htl.repositories.DepManagerRepository;
import at.htl.repositories.DepartmentRepository;
import at.htl.repositories.EmployeeRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeService {

    @Inject
    EmployeeRepository employeeRepository;
    @Inject
    DepartmentRepository departmentRepository;
    @Inject
    DepManagerRepository depManagerRepository;

    @GET()
    @Path("/byDepartment/{id}")
    public List<EmployeeDto> getEmployeesByDepartment(@PathParam("id") Long id) {
        List<Employee> employees = employeeRepository.getEmployeesByDepartment(id);
        if(employees == null) {
            throw new WebApplicationException("Keine Bestellung gefunden", Response.Status.NOT_FOUND);
        }
        return EmployeeMapper.toDtoList(employees);
    }

    @GET
    @Path("/all")
    public List<EmployeeDto> getAllEmployees() {
        return EmployeeMapper.toDtoList(employeeRepository.findAll());
    }

    @GET
    @Path("/{id}")
    public EmployeeDto getEmployee(@PathParam("id") Long id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        if(employee == null) {
            throw new WebApplicationException("Keine Bestellung gefunden", Response.Status.NOT_FOUND);
        }
        return EmployeeMapper.toDto(employee);
    }

    @POST()
    public EmployeeDto addEmployee(PostEmployeeDto dto) {
        DepManager depManager = depManagerRepository.findById(dto.getDepManager());
        Department department = departmentRepository.findById(dto.getDepartmentId());

        Employee employee = EmployeeMapper.fromPostDto(dto, department, depManager);
        employeeRepository.save(employee);

        departmentRepository.addEmployeeToDepartment(dto.getDepartmentId(), employee);

        return EmployeeMapper.toDto(employee);
    }

    @PATCH()
    @Path("/{id}")
    public void updateEmployeesSalary(@PathParam("id") Long id, SalaryDto salary) {
        Employee employee = employeeRepository.getEmployeeById(id);
        employee.setSalary(salary.getSalary());
        employeeRepository.update(employee);
    }

    @GET
    @Path("/getDepartment/{id}")
    public Long getDepartmentId(@PathParam("id") Long id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        return employee.getDepartment().getId();
    }
}
