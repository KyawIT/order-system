package at.htl.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @Column(unique = true, nullable = false, name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false, name = "Price")
    Double price;
    @Column(nullable = false, name = "KTM-Score")
    int ktmScore;
    @Column(nullable = false, name = "Datum")
    Date date;

    @ManyToMany()
    @JoinTable(name ="drink_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "drink_id"))
    List<Drink> drinks;

    @ManyToMany()
    @JoinTable(name ="dish_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    List<Dish> dishes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cutsomer_id")
    Customer customer;

    public Order(Long id, Double price, int ktmScore, Date date, List<Dish> dishes, List<Drink> drinks, Customer customer) {
        this.id = id;
        this.price = price;
        this.ktmScore = ktmScore;
        this.date = date;
        this.dishes = dishes;
        this.drinks = drinks;
        this.customer = customer;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getKtmScore() {
        return ktmScore;
    }

    public void setKtmScore(int ktmScore) {
        this.ktmScore = ktmScore;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
