package at.htl.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @Column(unique = true, nullable = false, name = "customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false, name = "Name")
    String name;
    @Column(nullable = false, name = "Alter")
    int age;
    @Column(unique = true, nullable = false, name = "E-Mail")
    String email;
    @Column(name = "Stadt")
    String city;
    @Column(name = "Addresse")
    String address;
    @Column(name = "PLZ")
    int zipCode;
    @Column(name = "KTM-Score")
    int ktmScore;
    @Column(unique = true, nullable = false, name = "User-Name")
    String UserName;
    @Column(unique = true, nullable = false, name = "Password")
    String Password;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    List<Order> orders;

    public Customer(Long id, String name, int age, String email, String city, String address, int zipCode, int ktmScore, String userName, String password, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
        this.ktmScore = ktmScore;
        UserName = userName;
        Password = password;
        this.orders = orders;
    }

    public Customer() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public int getKtmScore() {
        return ktmScore;
    }

    public void setKtmScore(int ktmScore) {
        this.ktmScore = ktmScore;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addKtmScore(int ktmScore) {
        this.ktmScore += ktmScore;
    }
}
