package com.movierent.movierentapp.controller;
import com.movierent.movierentapp.domen.FilmRenting;


public class RentingDto {
    //variable for quantity of weeks for renting
    private String weeks;

    //variable for films' IDs for renting
    private String filmsIds;

    public RentingDto(FilmRenting filmRenting){
        this(filmRenting.getWeeks(), filmRenting.getFilmsIds());
    }

    //Constructor method
    public RentingDto(String weeks, String filmsIds) {
        this.weeks = weeks;
        this.filmsIds = filmsIds;
    }

    protected RentingDto(){}

    //Getter and Settter methods for class variables
    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) { this.weeks = weeks; }

    public String getFilmsIds() {return filmsIds;}

    public void setFilmsIds(String filmsIds) {
        this.filmsIds = filmsIds;
    }

}
