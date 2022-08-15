package com.movierent.movierentapp.domen;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Customer {
    //generated ID
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)//so that IDs won't repeat
    private Integer customerId;

    //Customer's name
    @Column
    private String name;

    //Customer's renting
    @Column
    private String rentingData;

    protected Customer(){}

    //constructor
    public Customer(String name, String  rentingData) {
        this.name = name;
        this.rentingData = rentingData;
    }

    //Getter and Setter methods
    public Integer getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRentingsHistory() {
        return rentingData;
    }

    public void setRentingsHistory(String  rentingsHistory) { this.rentingData = rentingsHistory; }

    //toString method
    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", rentingsHistory=" + rentingData +
                '}';
    }

    //equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customer customer = (Customer) obj;
        return Objects.equals(customerId, customer.customerId) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(rentingData, customer.rentingData);
    }

    //hash method
    @Override
    public int hashCode() {
        return Objects.hash(customerId, name, rentingData);
    }
}
