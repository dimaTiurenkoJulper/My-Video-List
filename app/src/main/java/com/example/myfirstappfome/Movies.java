package com.example.myfirstappfome;

import com.example.myfirstappfome.DataClasses.MyMovie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movies implements Serializable {
    private static  Movies movies;
    private ArrayList<MyMovie> movieList ;
    private Movies(){
        movieList = new ArrayList<>();
    }
    public static Movies getInstance(){
        if(movies==null){
            return movies = new Movies();
        }
        else{
            return movies;
        }
    }
    public void setList(ArrayList<MyMovie> list){
        this.movieList = list;
    }
    public List<MyMovie> getList() {
        return movieList;
    }

}
