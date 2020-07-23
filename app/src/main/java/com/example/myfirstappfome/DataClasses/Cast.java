package com.example.myfirstappfome.DataClasses;

/**
 * data class , which used for show object in RecycleView
 */
public class Cast {
    private String name;
    private String description;
    private int image;

    /**
     * empty constructor for get object from firebase
     */
    public Cast() {
    }

    /**
     * default constructor with main properties
     *
     * @param name
     * @param description
     * @param image
     */
    public Cast(String name, String description, int image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    /**
     * method give cast name
     *
     * @retur cast name
     */
    public String getName() {
        return this.name;
    }

    /**
     * method set new cast name
     *
     * @param name - new cast name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * method give cast description
     *
     * @return String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * method set new cast description
     *
     * @param description - new cast description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * method give cast image
     *
     * @return int
     */
    public int getImage() {
        return this.image;
    }

    /**
     * set new Cast image
     *
     * @param image - new image
     */
    public void setImage(int image) {
        this.image = image;
    }
}