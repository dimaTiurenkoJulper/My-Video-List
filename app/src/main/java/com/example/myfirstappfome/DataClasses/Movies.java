package com.example.myfirstappfome.DataClasses;

import java.io.Serializable;
import java.util.ArrayList;

/***
 *singlton with all movies from firebase
 */
public class Movies implements Serializable {
    private static  Movies movies;
    private ArrayList<MovieFullInfo> movieList ;
    private Movies(){
        movieList = new ArrayList<>();
    }

    /***
     * get Instance
     * @return Movies single object
     */
    public static Movies getInstance(){
        if(movies==null){
            return movies = new Movies();
        }
        else{
            return movies;
        }
    }
    public void setList(ArrayList<MovieFullInfo> list){
        this.movieList = new ArrayList<>(list);
    }
    public ArrayList<MovieFullInfo> getList() {
        return new ArrayList<>(movieList);
    }

}
