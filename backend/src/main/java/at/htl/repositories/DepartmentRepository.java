package at.htl.repositories;

import at.htl.entities.Customer;
import at.htl.entities.Department;
import at.htl.entities.Dish;
import at.htl.entities.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class DepartmentRepository {

    @Inject
    EntityManager em;

    @Transactional
    public void save(List<Department> departs) {
        for (Department d : departs) {
            em.persist(d);
        }
    }

    @Transactional
    public List<Department> findAll() {
        String hql = "Select d from Department d";
        return em.createQuery(hql, Department.class).getResultList();
    }

    @Transactional
    public Department findById(Long id) {
        return em.find(Department.class, id);
    }

    @Transactional
    public void addEmployeeToDepartment(Long id, Employee employee) {
        Department managedDepartment = findById(id);
        managedDepartment.addEmployee(employee);
        em.merge(managedDepartment);
    }
}
