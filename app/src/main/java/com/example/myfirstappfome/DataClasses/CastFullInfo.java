package com.example.myfirstappfome.DataClasses;

import java.io.Serializable;

/***
 * this class get all information about cast .
 */
public class CastFullInfo implements Serializable {
    private String movieName;
    private String name;
    private String comment;
    private String description;
    private String image;

    /***
     * constructor for firebase realtime database
     */
    public CastFullInfo() {

    }

    /***
     * constructor with three main parameters
     * @param name - cast name
     * @param description cast description
     */
    public CastFullInfo(String movieName, String name, String description) {
        this.name = name;
        this.movieName = movieName;
        this.description = description;
    }

    /***
     *  method return cast name
     * @return string
     */
    public String getName() {
        return this.name;
    }

    /***
     * method change cast name
     * @param name will change old name .
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get cast description
     *
     * @return String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * set new cast description
     *
     * @param description new value for description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get cast Image
     *
     * @return Bitmap
     */
    public String getImage() {
        return this.image;
    }

    /**
     * set new cast Image
     *
     * @param image - new value for cast image
     */
    public void setImage(String image) {
        this.image = image;
    }
}