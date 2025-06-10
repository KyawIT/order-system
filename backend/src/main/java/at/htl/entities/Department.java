package at.htl.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Department")
public class Department {
    @Id
    @Column(unique = true, nullable = false, name = "department_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique = true, nullable = false, name = "Name")
    String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    List<Employee> employees;

    public Department(Long id, String name, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    public Department() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }
}
