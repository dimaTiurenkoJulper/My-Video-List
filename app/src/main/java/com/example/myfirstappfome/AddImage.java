package com.example.myfirstappfome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

import com.example.myfirstappfome.DataClasses.CastFullInfo;
import com.example.myfirstappfome.DataClasses.MovieFullInfo;
import com.example.myfirstappfome.DataClasses.MyMovie;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

public class AddImage extends AppCompatActivity {
    private ImageView imageView;
    private final int Pick_image = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);
        imageView = (ImageView) findViewById(R.id.addImage);

    }

    public void Confirm(View view) {
        Intent PickerIntent = new Intent(Intent.ACTION_PICK);
        PickerIntent.setType("image/*");
        startActivityForResult(PickerIntent, Pick_image);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (requestCode == Pick_image) {
            if (resultCode == RESULT_OK) {
                try {
                    //Получаем URI изображения, преобразуем его в Bitmap
                    //объект и отображаем в элементе ImageView нашего интерфейса:
                    final Uri imageUri = imageReturnedIntent.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    final ImageView image = (ImageView) findViewById(R.id.addImage);
                    image.setImageBitmap(selectedImage);
                    saveInStorage(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }

    private void saveInStorage(Bitmap selectedImage) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        StorageReference storageRef = storage.getReference().child("Images/myImage"+getIntent().getStringExtra("name")+ ".jpg");
        // StorageReference imagesRef = storageRef.child("images");
        UploadTask uploadTask = storageRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference ref = db.getReference(Objects.requireNonNull(getIntent().getStringExtra("name"))); // Key
                MovieFullInfo saveMovie = new MovieFullInfo(getIntent().getStringExtra("name"), getIntent().getStringExtra("description"), taskSnapshot.getMetadata().getPath());
                if (getIntent().getStringExtra("comment") != null && !Objects.equals(getIntent().getStringExtra("comment"), "")) {
                    saveMovie.addComment(getIntent().getStringExtra("comment"));
                }
                ref.setValue(saveMovie); // Value
                Intent intent = new Intent(getApplicationContext() , MainScreen.class);
                startActivity(intent);
                // ...
            }
        });
        //Intent intent = new Intent(this, MainScreen.class);
        // startActivity(intent);
    }
}
