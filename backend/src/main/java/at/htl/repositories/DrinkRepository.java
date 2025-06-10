package at.htl.repositories;

import at.htl.entities.Customer;
import at.htl.entities.Dish;
import at.htl.entities.Drink;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DrinkRepository {
    @Inject
    EntityManager em;

    @Transactional
    public void save(List<Drink> drinks) {
        for (Drink d : drinks) {
            em.persist(d);
        }
    }

    @Transactional
    public List<Drink> findAll() {
        String hql = "Select d from Drink d";
        return em.createQuery(hql, Drink.class).getResultList();
    }

    @Transactional
    public List<Drink> findByIdList(Long[] idList) {
        List<Drink> drinks = new ArrayList<>();
        for(Long id : idList){
            drinks.add(em.find(Drink.class, id));
        }
        return drinks;
    }
}
