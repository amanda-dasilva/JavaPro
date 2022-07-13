package com.mentorama.movie.entities;

import java.util.Arrays;
import java.util.List;

public class Movie {

    private Integer id;

    private String title;

    private String directorName;

    private Integer year;

    Integer rate;

    public Movie(Integer id, String title, String directorName, Integer year, Integer rate) {
        this.id = id;
        this.title = title;
        this.directorName = directorName;
        this.year = year;
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
