package at.htl.repositories;

import at.htl.entities.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OrderRepository {

    @Inject
    EntityManager em;
    @Inject
    EmployeeRepository employeeRepository;
    @Inject
    CustomerRepository customerRepository;

    @Transactional
    public void save(List<Order> orders) {
        for (Order o : orders) {
            em.persist(o);
        }
    }

    @Transactional
    public void save(Order order) {
        em.persist(order);
    }

    @Transactional
    public List<Order> findAll() {
        String hql = "SELECT DISTINCT o FROM Order o " +
                "LEFT JOIN FETCH o.dishes " +
                "LEFT JOIN FETCH o.customer";

        List<Order> orders = em.createQuery(hql, Order.class).getResultList();

        for (Order order : orders) {
            order.getDrinks().size();
        }

        return orders;
    }

    @Transactional
    public List<Order> findAllByEmployee(Long id) {
        String hql = "SELECT DISTINCT o FROM Employee e " +
                "JOIN e.orders o " +
                "LEFT JOIN FETCH o.dishes " +
                "LEFT JOIN FETCH o.customer " +
                "WHERE e.id = :id";

        try {
            List<Order> orders = em.createQuery(hql, Order.class)
                    .setParameter("id", id)
                    .getResultList();

            for (Order o : orders) {
                o.getDrinks().size();
            }

            return orders;
        } catch (NoResultException n){
            return new ArrayList<>();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public Order findLatestByEmployee(Long id) {
        String hql = "SELECT o FROM Employee e " +
                "JOIN e.orders o " +
                "LEFT JOIN FETCH o.customer " +
                "LEFT JOIN FETCH o.dishes " +
                "WHERE e.id = :id " +
                "ORDER BY o.date desc ";

        try{
            Order order = em.createQuery(hql, Order.class)
                    .setParameter("id", id)
                    .setMaxResults(1)
                    .getSingleResult();

            order.getDrinks().size();

            return order;
        } catch (NoResultException n){
            return new Order();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<Order> findAllByCustomer(Long id) {
        String hql = "SELECT o FROM Order o " +
                "LEFT JOIN FETCH o.customer " +
                "LEFT JOIN FETCH o.dishes " +
                "WHERE o.customer.id = :id ";
        try{
            List<Order> orders = em.createQuery(hql, Order.class)
                    .setParameter("id", id)
                    .getResultList();

            for (Order o : orders) {
                o.getDrinks().size();
            }

            return orders;
        } catch (NoResultException n){
            return new ArrayList<>();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public Order findLatestByCustomer(Long id) {
        String hql = "SELECT o FROM Order o " +
                "LEFT JOIN FETCH o.customer " +
                "LEFT JOIN FETCH o.dishes " +
                "WHERE o.customer.id = :id " +
                "ORDER BY o.date desc";

        try{
            Order order = em.createQuery(hql, Order.class)
                    .setParameter("id", id)
                    .setMaxResults(1)
                    .getSingleResult();

            order.getDrinks().size();

            return order;
        } catch (NoResultException n){
            return new Order();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    @Transactional
    public void deleteById(Long id) {
        em.remove(findById(id));
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = findById(orderId);

        if (order == null) return;

        List<Employee> employees = employeeRepository.findAll();
        for (Employee e : employees) {
            if (e.getOrders().contains(order)) {
                e.getOrders().remove(order);
                employeeRepository.update(e);
            }
        }

        Customer customer = order.getCustomer();
        if (customer != null && customer.getOrders() != null) {
            customer.getOrders().remove(order);
            order.setCustomer(null);
            customerRepository.update(customer);
        }

        deleteById(order.getId());
    }
}
