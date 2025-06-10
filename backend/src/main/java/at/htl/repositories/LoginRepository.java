package at.htl.repositories;

import at.htl.entities.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class LoginRepository {
    @Inject
    EntityManager em;

    @Transactional
    public DepManager findDepManager(String password) {
        String hql = "SELECT d FROM DepManager d " +
                "WHERE d.Password = :password ";

        try{
            DepManager depManager = em.createQuery(hql, DepManager.class)
                    .setParameter("password", password)
                    .getSingleResult();
            return depManager;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public Customer findCustomer(String password) {
        String hql = "SELECT c FROM Customer c " +
                "WHERE c.Password = :password ";

        try{
            Customer customer = em.createQuery(hql, Customer.class)
                    .setParameter("password", password)
                    .getSingleResult();
            return customer;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public Employee findEmployee(String password) {
        String hql = "SELECT e FROM Employee e " +
                "WHERE e.Password = :password ";

        try{
            Employee employee = em.createQuery(hql, Employee.class)
                    .setParameter("password", password)
                    .getSingleResult();
            return employee;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
}
