package at.htl.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Dish")
public class Dish {
    @Id
    @Column(unique = true, nullable = false, name = "dish_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique = true, nullable = false, name = "Name")
    String name;
    @Column(nullable = false, name = "Type")
    String type;
    @Column(nullable = false, name = "Vegan")
    Boolean vegan;
    @Column(nullable = false, name = "Preis")
    Double price;

    public Dish(Long id, String name, String type, Boolean vegan, Double price, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.vegan = vegan;
        this.price = price;
    }

    public Dish() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getVegan() {
        return vegan;
    }

    public void setVegan(Boolean vegan) {
        this.vegan = vegan;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
