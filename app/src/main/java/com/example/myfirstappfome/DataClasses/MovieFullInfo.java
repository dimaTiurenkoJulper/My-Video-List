package com.example.myfirstappfome.DataClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * this class get all information about movie .
 */
public class MovieFullInfo implements Serializable {
    private String name;
    private String Comment;
    private String description;
    private int image;
    private boolean isFavorite;
    private List<CastFullInfo> casts = new ArrayList<>();

    /**
     * constructor with three main parameters
     *
     * @param name        - cast name
     * @param description cast description
     * @param image       cast image
     */
    public MovieFullInfo(String name, String description, int image) {
        this.name = name;
        this.description = description;
        this.image = image;

    }

    /**
     * method return movie name
     *
     * @return string
     */
    public String getName() {
        return this.name;
    }

    /**
     * method change movie name
     *
     * @param name will change old name .
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * get comment to movie
     *
     * @return String
     */
    public String getComment() {
        return this.Comment;
    }

    /**
     * set new comment to the movie
     *
     * @param comment String  value , which will change old comment
     */
    public void setComment(String comment) {
        this.Comment = comment;
    }

    /**
     * get movie description
     *
     * @return String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * set new movie description
     *
     * @param description new value for description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get movie Image
     *
     * @return int
     */
    public int getImage() {
        return this.image;
    }

    /**
     * set new movie Image
     *
     * @param image - new value for cast image
     */
    public void setImage(int image) {
        this.image = image;
    }

    /**
     * get casts list
     *
     * @return List<CastFullInfo>
     */
    @NonNull
    public List<CastFullInfo> getCasts() {
        return casts;
    }

    /**
     * add new cast in Casts list
     *
     * @param cast
     */
    public void addCast(@NonNull CastFullInfo cast) {
        casts.add(cast);
    }
}
