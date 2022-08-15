package com.movierent.movierentapp.domen;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Renting {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)//so that IDs won't repeat
    private Integer id;

    @Column
    private Integer customerId;

    @Column
    private String rentings;

    @Column
    private Double price;

    //constructor
    public Renting(Integer customerId, String rentings, Double price) {
        this.customerId = customerId;
        this.rentings = rentings;
        this.price = price;
    }

    protected Renting(){}

    //Getter and setter methods
    public Integer getId() { return id; }

    public Integer getCustomerId() { return customerId; }

    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public String getRentings() { return rentings; }

    public void setRentings(String rentings) { this.rentings = rentings; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    @Override
    public String toString() {
        return "Renting{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", rentings='" + rentings + '\'' +
                ", price=" + price +
                '}';
    }

    //equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Renting renting = (Renting) o;
        return Objects.equals(id, renting.id) &&
                Objects.equals(customerId, renting.customerId) &&
                Objects.equals(rentings, renting.rentings) &&
                Objects.equals(price,renting.price);
    }

    //hash method
    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, rentings, price);
    }
}
