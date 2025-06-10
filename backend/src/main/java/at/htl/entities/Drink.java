package at.htl.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Drink")
public class Drink {
    @Id
    @Column(unique = true, nullable = false, name = "drink_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique = true, nullable = false, name = "Name")
    String name;
    @Column(nullable = false, name = "Quantity")
    int quantity;
    @Column(nullable = false, name = "Preis")
    Double price;

    public Drink(Long id, String name, int quantity, Double price, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Drink() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
