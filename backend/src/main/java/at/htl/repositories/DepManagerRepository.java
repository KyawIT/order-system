package at.htl.repositories;

import at.htl.entities.DepManager;
import at.htl.entities.Dish;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class DepManagerRepository {
    @Inject
    EntityManager em;

    @Transactional
    public void save(List<DepManager> depManagers) {
        for (DepManager d : depManagers) {
            em.persist(d);
        }
    }

    @Transactional
    public List<DepManager> findAll() {
        String hql = "Select d from DepManager d";
        return em.createQuery(hql, DepManager.class).getResultList();
    }

    @Transactional
    public DepManager findById(Long id) {
        return em.find(DepManager.class, id);
    }
}
