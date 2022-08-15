package com.movierent.movierentapp.domen;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)//so that IDs won't repeat
    private Integer id; //unique identifier of a movie

    @Column
    private String title; //the name of the movie. Movies' names can repeat and contain punctuation marks

    @Column(length = 2000)
    private String description; //shprt description of the movie

    @Column
    private LocalDate releaseDate; //release date, which will be defined as LocalDate in format yyyy-mm-dd. The LocalDate format is used for more comfortable total price counting

    @Column
    private Integer duration; //duration - stored in Integer, but shown as string divided within hours and minutes, so user could understand the time better

    @Column
    private boolean availability; //checkpoint if it was rented or not


    @ElementCollection
    private List<String> mainActors; //main actors list is in List format, as the number of main actors for different movies is also different

    @Column
    @Enumerated
    private AgeCategory ageCategory; //age category is enumerated, as there are limited and defined age categories

    @Column
    @Enumerated
    private StyleCategory styleCategory; //style categories as genres, are also defined, so the user wouldn't create same genre names with different letter(Drama, drama and etc.)


    //constructor method
    public Film(String title, String description, LocalDate releaseDate, Integer duration, boolean availability, //Constructor for Film class variables, is used to initialize objects.
                List<String> mainActors, AgeCategory ageCategory, StyleCategory styleCategory) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.availability = availability;
        this.mainActors = mainActors;
        this.ageCategory = ageCategory;
        this.styleCategory = styleCategory;
    }

    protected Film(){}

    //Getter and Setter methods

    public Integer getId() {return id;}
    //ID does not have set method, because it is GeneratedValue and should npt repeat

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public LocalDate getReleaseDate() {return releaseDate;}

    public void setReleaseDate(LocalDate releaseDate) {this.releaseDate = releaseDate;}

    //Duration variable is changed, so user could understand the time better
    public String getDuration() {
        int hours;
        hours = duration / 60;
        int minutes;
        minutes = duration % 60;
        return String.valueOf(hours) + "h " + String.valueOf(minutes) + "m";
    }
    public void setDuration(Integer duration) {this.duration = duration;}

    //availability is used in boolean type, so it would be more comfortable to use (for example for if statements), but for user it is refactpred to String with more understandable words
    public String getAvailability() {
        if(availability) {return "available";
        }else{return "not avaialble";}
    }

    public void setAvailability(boolean availability) {this.availability = availability;}

    public List<String> getMainActors() {return mainActors;}

    public void setMainActors(List<String> mainActors) {this.mainActors = mainActors;}

    public AgeCategory getAgeCategory() {return ageCategory;}

    public void setAgeCategory(AgeCategory ageCategory) {this.ageCategory = ageCategory;}

    public StyleCategory getStyleCategory() {return styleCategory;}

    public void setStyleCategory(StyleCategory styleCategory) {this.styleCategory = styleCategory;}

    //ToString method
    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                ", duration='" + duration + '\'' +
                ", availability=" + availability +
                ", mainActors=" + mainActors +
                ", ageCategory=" + ageCategory +
                ", styleCategory=" + styleCategory +
                '}';
    }

    //equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Film film = (Film) obj;
        return Objects.equals(id, film.id) &&
                Objects.equals(title, film.title) &&
                Objects.equals(description, film.description) &&
                Objects.equals(releaseDate, film.releaseDate) &&
                Objects.equals(duration, film.duration) &&
                Objects.equals(availability, film.availability) &&
                Objects.equals(mainActors, film.mainActors) &&
                ageCategory == film.ageCategory &&
                styleCategory == film.styleCategory;
    }

    //hash method
    //determines the hash code for a given Integer. It overrides hashCode in class Object. By default, this method returns a random integer that is unique for each instance.
    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, releaseDate, duration, availability, mainActors, ageCategory, styleCategory);
    }
}
