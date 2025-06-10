package at.htl.repositories;

import at.htl.entities.Customer;
import at.htl.entities.DepManager;
import at.htl.entities.Dish;
import at.htl.entities.Drink;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DishRepository {
    @Inject
    EntityManager em;

    @Transactional
    public void save(List<Dish> dishes) {
        for (Dish d : dishes) {
            em.persist(d);
        }
    }

    @Transactional
    public List<Dish> findAll() {
        String hql = "Select d from Dish d";
        return em.createQuery(hql, Dish.class).getResultList();
    }

    @Transactional
    public List<Dish> findByIdList(Long[] idList) {
        List<Dish> dishes = new ArrayList<>();
        for(Long id : idList){
            dishes.add(em.find(Dish.class, id));
        }
        return dishes;
    }
}
