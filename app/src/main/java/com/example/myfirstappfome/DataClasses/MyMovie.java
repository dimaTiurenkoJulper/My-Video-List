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
    public String image;
    private List<CastFullInfo> casts = new ArrayList<>();

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
    public MyMovie(String name, String description , String Image) {

        this.name = name;
        this.description = description;
        this.image = Image;
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
       /* final Bitmap[] bmp = new Bitmap[1];
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child(image);
        final long ONE_MEGABYTE = 1024 * 1024;
        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap  bmp6 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                bmp[0] = bmp6;
            }
        });
        return bmp[0];*/
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
}
