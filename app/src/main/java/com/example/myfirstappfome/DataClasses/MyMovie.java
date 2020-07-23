package com.example.myfirstappfome.DataClasses;

/**
 * data class , which used for show object in RecycleView
 */
public class MyMovie {
    private String name;
    private String description;
    private int image;

    /**
     * empty constructor for get object from firebase
     */
    public MyMovie() {

    }

    /**
     * default constructor with main properties
     *
     * @param name
     * @param description
     * @param image
     */
    public MyMovie(String name, String description, int image) {

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
}
