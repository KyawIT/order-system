package at.htl.repositories;

import at.htl.entities.Dish;
import at.htl.entities.Drink;
import at.htl.entities.Employee;
import at.htl.entities.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class EmployeeRepository {
    @Inject
    EntityManager em;

    @Transactional
    public void save(List<Employee> employees) {
        for (Employee e : employees) {
            em.persist(e);
        }
    }

    @Transactional
    public void save(Employee employee) {
        em.persist(employee);
    }

    @Transactional
    public List<Employee> findAll() {
        String hql = "Select e from Employee e";
        return em.createQuery(hql, Employee.class).getResultList();
    }

    @Transactional
    public List<Employee> getEmployeesByDepartment(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        Root<Employee> root = cq.from(Employee.class);

        root.fetch("department");
        cq.select(root).where(cb.equal(root.get("department").get("id"), id));

        try{
            return em.createQuery(cq).getResultList();
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public void update(Employee e) {
        em.merge(e);
    }

    @Transactional
    public Employee getEmployeeById(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        Root<Employee> root = cq.from(Employee.class);

        root.fetch("department");
        cq.select(root).where(cb.equal(root.get("id"), id));
        try{
            return em.createQuery(cq).getSingleResult();
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<Employee> getEmployeesWithLeastOrder() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);


        root.fetch("orders", JoinType.LEFT);

        Predicate department1 = cb.equal(root.get("department").get("id"), 1);
        Predicate department2 = cb.equal(root.get("department").get("id"), 2);
        cq.where(cb.or(department1, department2));

        cq.orderBy(cb.asc(cb.size(root.get("orders"))));

        return em.createQuery(cq)
                .setMaxResults(2)
                .getResultList();
    }
}
