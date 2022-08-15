package com.movierent.movierentapp.domen;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

//class with renting primary keys
@Embeddable
public class FilmRentingPk implements Serializable {
    @ManyToOne
    private Film film;

    @Column(insertable = false, updatable = false,nullable = false)
    private Integer customerId;

    public FilmRentingPk(){}

    //Constructor method
    public FilmRentingPk(Film film, Integer customerId){
        this.film = film;
        this.customerId = customerId;
    }

    //Getter methods
    public Film getFilm() {
        return film;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    //equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmRentingPk that = (FilmRentingPk) o;

        if (!film.equals(that.film)) return false;
        return customerId.equals(that.customerId);
    }


    //To String method
    @Override
    public String toString() {
        return "FilmRentingPk{" +
                "film=" + film +
                ", customerId=" + customerId +
                '}';
    }
}
