package com.movierent.movierentapp;

//Exception class, if the film, which user wants to rent, was already rented/npt available
public class FilmIsNotAvailableException extends RuntimeException{
    public FilmIsNotAvailableException(String errorMessage) {
        super(errorMessage);
    }
}
