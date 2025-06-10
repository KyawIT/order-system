package at.htl.repositories;

import at.htl.entities.Customer;
import at.htl.entities.Department;
import at.htl.entities.Dish;
import at.htl.entities.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CustomerRepository {
    @Inject
    EntityManager em;

    @Transactional
    public void save(List<Customer> customers) {
        for (Customer c : customers) {
            em.persist(c);
        }
    }

    @Transactional
    public void save(Customer customer) {
        em.persist(customer);
    }

    @Transactional
    public List<Customer> findAll() {
        String hql = "Select c from Customer c";
        return em.createQuery(hql, Customer.class).getResultList();
    }

    @Transactional
    public Customer findCustomerById(Long id) {
        return em.find(Customer.class, id);
    }

    @Transactional
    public void update(Customer customer) {
        em.merge(customer);
    }

}
