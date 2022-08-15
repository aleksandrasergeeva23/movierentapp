package com.movierent.movierentapp.repo;

import com.movierent.movierentapp.domen.Renting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "filmrentings", path = "filmrentings")
public interface RentingRepository extends CrudRepository<Renting, Integer>, PagingAndSortingRepository<Renting, Integer> {
    Page<Renting> findById (Integer id, Pageable pageable);
    List<Renting> findByCustomerId(Integer customerId);

    //CRUD methods
    @Override
    Iterable<Renting> findAll(Sort sort);

    @Override
    Page<Renting> findAll(Pageable pageable);

    @Override
    <S extends Renting> S save(S entity);

    @Override
    <S extends Renting> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Renting> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    Iterable<Renting> findAll();

    @Override
    Iterable<Renting> findAllById(Iterable<Integer> integers);

    @Override
    long count();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Renting entity);

    @Override
    void deleteAllById(Iterable<? extends Integer> integers);

    @Override
    void deleteAll(Iterable<? extends Renting> entities);

    @Override
    void deleteAll();
}


