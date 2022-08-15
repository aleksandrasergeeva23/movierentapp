package com.movierent.movierentapp.repo;

import com.movierent.movierentapp.domen.Film;
import com.movierent.movierentapp.domen.StyleCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "films", path = "films")
public interface FilmRepository extends CrudRepository<Film, Integer>, PagingAndSortingRepository <Film, Integer> /*Integer, cause type of the tour ID is integer*/ {
    List<Film> findByTitle(String title); //find by the name of the film
    //Optional<Film> findById(Integer id);
    Film findByReleaseDate(LocalDate date); //find by release date
    List<Film> findByAvailability(boolean availability); //find all the films, which are available or not available(rented)
    Film findAllByMainActorsContains(String nimi); //find movies with particular actor as main actor in this folm
    Page<Film> findByStyleCategory(StyleCategory category, Pageable pageable); //Page woth all the film of some defined category
    void deleteFilmById(Integer ID); //delete film by its ID
    void deleteFilmByTitle(String title); //delete film by title
    Film findAllById(Integer ID);
    Optional<Film> findByIdAndAvailability(int filmId, boolean availability);

    //managing rights for film repository methods, so only competent people could use those methods (save and delete)
    //CRUD methods
    @Override
    @RestResource(exported = false)
    <S extends Film> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends Film> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);

    @Override
    @RestResource(exported = false)
    void delete(Film entity);

    @Override
    @RestResource(exported = false)
    void deleteAllById(Iterable<? extends Integer> integers);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Film> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
