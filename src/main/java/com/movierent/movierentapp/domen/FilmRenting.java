package com.movierent.movierentapp.domen;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class FilmRenting {

    //Creating primary key, which is unique
    @EmbeddedId
    private FilmRentingPk pk;

    //weeks variable
   @Column(nullable = false)
    private String weeks;

   //Films' IDs variable
   @Column
   private String filmsIds;

   @Column
   private Integer customerId1;

   //Constructor method
    public FilmRenting(FilmRentingPk pk, String weeks, String filmsIds, Integer customerId1) {
        this.pk = pk;
        this.weeks = weeks;
        this.filmsIds = filmsIds;
        this.customerId1 = customerId1;
    }

    protected FilmRenting() {}

    //ToString method
    @Override
    public String toString() {
        return "FilmRenting{" +
                "pk=" + pk +
                ", weeks=" + weeks +
                ", IDs='" + filmsIds +
                ",customerId1=" + customerId1 + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, weeks, filmsIds, customerId1);
    }

    //Getter and Setter methods
    public FilmRentingPk getPk() {
        return pk;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public String getFilmsIds() { return filmsIds; }

    public void setFilmsIds(String filmsIds) { this.filmsIds = filmsIds; }

    public Integer getCustomerId() {
        return customerId1;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId1 = customerId;
    }
}
