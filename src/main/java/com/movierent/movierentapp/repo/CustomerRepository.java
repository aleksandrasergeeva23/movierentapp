package com.movierent.movierentapp.repo;

import com.movierent.movierentapp.domen.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerRepository extends CrudRepository<Customer, Integer>, PagingAndSortingRepository<Customer, Integer>{
    Optional<Customer> findByCustomerId(Integer id);
    Customer findAllByCustomerId(Integer customerId);

    //CRUD methods
    @Override
    <S extends Customer> S save(S entity);

    @Override
    <S extends Customer> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Customer> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    long count();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Customer entity);

    @Override
    void deleteAllById(Iterable<? extends Integer> integers);

    @Override
    void deleteAll(Iterable<? extends Customer> entities);

    @Override
    void deleteAll();
}
