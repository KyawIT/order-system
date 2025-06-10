package at.htl.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Employee")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Employee {
    @Id
    @Column(unique = true, nullable = false, name = "employee_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false, name = "Name")
    String name;
    @Column(unique = true, nullable = false, name = "User-Name")
    String UserName;
    @Column(unique = true, nullable = false, name = "Password")
    String Password;
    @Column(nullable = false, name = "Gehalt")
    Double salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "depManager_id")
    DepManager depManager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    Department department;

    @ManyToMany()
    @JoinTable(name ="employee_order",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    List<Order> orders;

    public Employee(Long id, String name, String userName, String password, Double salary, DepManager depManager, Department department, List<Order> orders) {
        this.id = id;
        this.name = name;
        UserName = userName;
        Password = password;
        this.salary = salary;
        this.depManager = depManager;
        this.department = department;
        this.orders = orders;
    }

    public Employee() {
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

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public DepManager getDepManager() {
        return depManager;
    }

    public void setDepManager(DepManager depManager) {
        this.depManager = depManager;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }
}
