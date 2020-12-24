package com.example.myfirstappfome.Adapters;

import com.example.myfirstappfome.DataClasses.MyMovie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SaveMovieList implements Serializable {
    private ArrayList<MyMovie> movieList ;
    public SaveMovieList(ArrayList<MyMovie> list){
        this.movieList= list;
    }
    public ArrayList<MyMovie> getMovieList(){
        return new ArrayList<>(movieList);
    }
    public void addMovie(MyMovie movie){
        movieList.add(movie);
    }
}
