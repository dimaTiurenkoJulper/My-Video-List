package com.example.myfirstappfome.DataClasses;

public class CastFullInfo {
    private String name;
    private String Comment;
    private String description;
    private int image;

    public CastFullInfo(String name, String description, int image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return this.Comment;
    }

    public void setComment(String comment){
        this.Comment = comment;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return this.image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
