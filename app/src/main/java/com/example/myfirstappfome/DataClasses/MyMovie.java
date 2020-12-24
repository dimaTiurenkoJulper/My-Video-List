package com.example.myfirstappfome.DataClasses;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * data class , which used for show object in RecycleView
 */
public class MyMovie implements Serializable {
    private String name;
    private String description;
    private String image;
    private List<CastFullInfo> casts = new ArrayList<>();
    private boolean isFavorite ;

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
     */
    public MyMovie(String name, String description , String Image , boolean isFavorite) {

        this.name = name;
        this.description = description;
        this.image = Image;
        this.isFavorite = isFavorite;
    }
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
    public String getImage() {
       return image;
    }

    /**
     * set new movie Image
     *
     * @param image - new value for cast image
     */
    public void setImage(String image) {
        this.image = image;
    }
    public void setFavorite (boolean isFavorite){
        this.isFavorite = isFavorite;
    }
    public boolean getIsFavorite (){
        return isFavorite;
    }
}
