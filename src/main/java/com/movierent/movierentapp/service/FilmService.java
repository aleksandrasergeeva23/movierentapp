package com.movierent.movierentapp.service;

import com.movierent.movierentapp.MovierentappApplication;
import com.movierent.movierentapp.domen.AgeCategory;
import com.movierent.movierentapp.domen.Film;
import com.movierent.movierentapp.domen.StyleCategory;
import com.movierent.movierentapp.repo.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FilmService {
    private FilmRepository filmRepository;
    @Autowired
    //Constructor for filmRepository
    public FilmService (FilmRepository filmRepository){
        this.filmRepository = filmRepository;
    }

    //method for adding film
    public Film addFilm (String title, String description, LocalDate releaseDate, Integer duration, boolean availability,
                         List<String> mainActors, AgeCategory ageCategory, StyleCategory styleCategory) {
        return filmRepository.save(new Film(title, description, releaseDate, duration, availability, mainActors, ageCategory, styleCategory));
    }
    //method for counting how many films are in repository(DB)
    public long total() {return filmRepository.count();}

    //method for updating partial information for a film in repository
    public void updateFilmInfo(Integer id, String newTitle) {
        Film someFilm = filmRepository.findAllById(id);
        someFilm.setTitle(newTitle);
        filmRepository.save(someFilm);
    }

}
