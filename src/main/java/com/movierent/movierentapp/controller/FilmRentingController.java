package com.movierent.movierentapp.controller;

import com.movierent.movierentapp.FilmIsNotAvailableException;
import com.movierent.movierentapp.domen.*;
import com.movierent.movierentapp.repo.CustomerRepository;
import com.movierent.movierentapp.repo.FilmRepository;
import com.movierent.movierentapp.repo.RentingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static java.time.temporal.ChronoUnit.DAYS;

@RestController
@RequestMapping(path="/customers/{customerId}/rentings") //path, where th ratings will be stored and from where customer could get them

//calling out all the repositories
public class FilmRentingController {
    FilmRepository filmRepository;
    CustomerRepository customerRepository;
    RentingRepository rentingRepository;
    Renting renting;

    //Constructor method
    @Autowired
    public FilmRentingController(FilmRepository filmRepository, CustomerRepository customerRepository, RentingRepository rentingRepository) {
        this.filmRepository = filmRepository;
        this.customerRepository = customerRepository;
        this.rentingRepository = rentingRepository;
    }

    protected FilmRentingController(){}

    //main method for renting
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void rentFilms(@PathVariable(value = "customerId") Integer customerId, @RequestBody @Validated RentingDto rentingDto) {;
        String weeks = rentingDto.getWeeks();
        String filmsId = rentingDto.getFilmsIds();
        String[] filmsIdList = filmsId.split(";");
        String[] weeksList = weeks.split(";");
        LocalDate today = LocalDate.now();
        //Th data is saved to /customers/{customerId}/rentings/filmrentings
        try {
            for (int i = 0; i < filmsIdList.length; i++) {
                Film film = verifyFilm(Integer.parseInt(filmsIdList[i]));
                if(film.getAvailability().equals("available")){
                        LocalDate releaseDate = film.getReleaseDate();
                        long rentingTime = DAYS.between(releaseDate, today);
                        Double price = 0.0;
                        String rentings = filmsIdList[i] + ":" + today + " - " + (today.plusDays(Integer.parseInt(weeksList[i])* 7));
                        if((rentingTime + Integer.parseInt(weeksList[i])* 7) < 52*7){
                            price = Integer.parseInt(weeksList[i]) * 5.0;
                            rentingRepository.save(new Renting(customerId, rentings, price));
                        } else {
                            for (int j = 0; j < Integer.parseInt(weeksList[i]); j++) {
                                if(rentingTime < (52*7)){
                                 price += 5.0;
                                 rentingTime += 7;
                                }
                                else if (rentingTime < 156*7) {
                                    price += 3.49;
                                    rentingTime += 7;
                                }
                                else {
                                     price += 1.99;
                                     rentingTime += 7;
                                }
                            }
                            rentingRepository.save(new Renting(customerId, rentings, price));
                        }
                } else {
                    i++;
                }
        }
        }
        catch (FilmIsNotAvailableException | NoSuchElementException e) {
        }
        System.out.println(customerId);
    }

    //method, that controls if the film, which the user wants to rent, does or does not exist in DB
    private Film verifyFilm(int filmId) throws NoSuchElementException {
        return filmRepository.findById(filmId).orElseThrow(() ->
                new NoSuchElementException("We do not have film with id " + filmId + " in our store"));
    }


    //method to check if the customer exist in DB or not
    private Customer verifyCustomer(int customerId) throws NoSuchElementException {
        return customerRepository.findByCustomerId(customerId).orElseThrow(() ->
                new NoSuchElementException("We do not have such customer " + customerId + " in our store"));
    }

    //No such element error if something does not exist in DB
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException e) {
        return e.getMessage();
    }

    //Film is not available error if the film, which the user wants to rent, is already rented by someone else
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ExceptionHandler(FilmIsNotAvailableException.class)
    public String return400(FilmIsNotAvailableException e) {
        return e.getMessage();
    }

    //method to get the rentings of one customer
    @GetMapping(path = "/filmrentings")
    public List<Renting> getRenting (@PathVariable(value = "customerId") Integer customerId){
        List<Renting> rentings = rentingRepository.findByCustomerId(customerId);
        return rentings;
    }



}

