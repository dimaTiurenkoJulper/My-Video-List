package com.example.myfirstappfome.DataClasses;

import java.io.Serializable;

/**
 * this class get all information about movie .
 */
public class MovieFullInfo implements Serializable {
    private String name;
    private String comment;
    private String description;
    private String image;
    private boolean isFavorite;

    /**
     * constructor for firebase RealTimeDatabase.
     */
    public MovieFullInfo() {

    }

    /**
     * constructor with three main parameters
     *
     * @param name        - movie name
     * @param description movie description
     * @param image       movie image
     * @param isFavorite  if movie add as favorite
     */

    public MovieFullInfo(String name, String description, String image, boolean isFavorite) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.isFavorite = isFavorite;
    }

//    public String getCastId() {
//        return castId;
//    }


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
        return this.comment;
    }

    /**
     * set new comment to the movie
     *
     * @param comment String  value , which will change old comment
     */
    public void setComment(String comment) {
        this.comment = comment;
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
    public String getImage() {
        return this.image;
    }

    /**
     * set new movie Image
     *
     * @param image - new value for cast image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * add new comment
     *
     * @param comment comment with replace old comment
     */
    public void addComment(String comment) {
        this.comment = comment;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
}
