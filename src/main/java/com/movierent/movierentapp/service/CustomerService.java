package com.movierent.movierentapp.service;

import com.movierent.movierentapp.domen.Customer;
import com.movierent.movierentapp.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    //constructor
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //nethod to add new customer
    public Customer addCustomer(String name, String rentings){
        return customerRepository.save(new Customer(name, rentings));
    }

    //example of how to change something in Customers data (here name)
    public void updateCustomerName(Integer id, String newName) {
        Customer someCustomer = customerRepository.findAllByCustomerId(id);
        someCustomer.setName(newName);
        customerRepository.save(someCustomer);
    }

    //totak number of customers
    public long total() {return customerRepository.count();}

    //all customers
    public Iterable<Customer> lookup(){
        return customerRepository.findAll();
    }
}


