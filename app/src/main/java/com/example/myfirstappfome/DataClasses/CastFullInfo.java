package com.example.myfirstappfome.DataClasses;

import java.io.Serializable;

/***
 * this class get all information about cast .
 */
public class CastFullInfo implements Serializable {
    private String name;
    private String Comment;
    private String description;
    private int image;

    /***
     * constructor with three main parameters
     * @param name - cast name
     * @param description cast description
     * @param image cast image
     */
    public CastFullInfo(String name, String description, int image) {
        this.name = name;
        this.description = description;
        this.image = image;
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
     * get comment to cast
     *
     * @return String
     */
    public String getComment() {
        return this.Comment;
    }

    /**
     * set new comment to the cast
     *
     * @param comment value , which will change old comment
     */
    public void setComment(String comment) {
        this.Comment = comment;
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
     * @return int
     */
    public int getImage() {
        return this.image;
    }

    /**
     * set new cast Image
     *
     * @param image - new value for cast image
     */
    public void setImage(int image) {
        this.image = image;
    }
}
