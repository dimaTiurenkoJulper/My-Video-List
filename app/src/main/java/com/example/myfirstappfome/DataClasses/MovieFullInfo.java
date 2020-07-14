package com.example.myfirstappfome.DataClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieFullInfo  implements Serializable {
    private String name;
    private String Comment;
    private String description;
    private int image;
    private boolean isFavorite ;
    private List<CastFullInfo> casts = new ArrayList<>();

    public MovieFullInfo(String name, String description, int image) {
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
    public List<CastFullInfo> getCasts ( ){return casts;}
    public void addCast (CastFullInfo cast ){
        casts.add(cast);
    }
}
