package at.htl.database;

import io.quarkus.runtime.Startup;
import jakarta.inject.Singleton;

@Startup
@Singleton
public class DataIntitializer {
    @Inject
    EntityManager em;

    @Transactional
    @PostConstruct
    void init() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("Max Mustermann");
        customer1.setAge(30);
        customer1.setMail("max.mustermann@example.com");
        customer1.setCity("Vienna");
        customer1.setPin(1234);
        customer1.setAddress("Hauptstraße 1");
        customer1.setKtmScore(75);

        em.persist(customer1);

        Dish dish1 = new Dish();
        dish1.setId(1L);
        dish1.setName("C-Burge");
        dish1.setType("Burges");
        dish1.setVegan("N");
        dish1.setPrice(5.99);

        em.persist(dish1);

        // Order anlegen
        Order order1 = new Order();
        order1.setId(101L);
        order1.setPrice(49.99);
        order1.setKtmScore(70);
        order1.setCustomer(customer1); // Beziehung setzen
        order1.setOrderDate(LocalDate.of(2023, 10, 1));

        em.persist(order1);

        // Die Zuordnung (Dish_Order) passiert automatisch, wenn du:
        order1.getDishes().add(dish1); // sofern du das in der Order-Entity definiert hast
    }
}

}
