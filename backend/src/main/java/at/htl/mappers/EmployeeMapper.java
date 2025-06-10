package at.htl.mappers;

import at.htl.dtos.EmployeeDto;
import at.htl.dtos.PostEmployeeDto;
import at.htl.entities.DepManager;
import at.htl.entities.Department;
import at.htl.entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper {

    public static Employee fromPostDto(PostEmployeeDto dto, Department department, DepManager depManager) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setUserName(dto.getUserName());
        employee.setPassword(dto.getPassword());
        employee.setSalary(dto.getSalary());
        employee.setDepartment(department);
        employee.setDepManager(depManager);
        employee.setOrders(null);
        return employee;
    }

    public static EmployeeDto toDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setSalary(employee.getSalary());
        dto.setDepartmentName(
                employee.getDepartment() != null ? employee.getDepartment().getName() : null
        );
        return dto;
    }

    public static List<EmployeeDto> toDtoList(List<Employee> employees) {
        List<EmployeeDto> dtoList = new ArrayList<>();
        for (Employee employee : employees) {
            dtoList.add(toDto(employee));
        }
        return dtoList;
    }

}
