package com.example.myfirstappfome.ui.AddMovie;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myfirstappfome.DataClasses.MovieFullInfo;
import com.example.myfirstappfome.MainScreen;
import com.example.myfirstappfome.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

public class AddImage extends AppCompatActivity {
    private final int Pick_image = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);
        //ImageView imageView = findViewById(R.id.addImage);

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
                    final InputStream imageStream = getContentResolver().openInputStream(Objects.requireNonNull(imageUri));
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    final ImageView image = findViewById(R.id.addImage);
                    image.setImageBitmap(selectedImage);
                    saveInStorage(selectedImage);
                    Intent intent = new Intent(this, MainScreen.class);
                    startActivity(intent);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveInStorage(Bitmap selectedImage) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        StorageReference storageRef = storage.getReference().child("Images/" + getIntent().getStringExtra("name") + "/" + "mainImage.jpg");
        // StorageReference imagesRef = storageRef.child("images");
        UploadTask uploadTask = storageRef.putBytes(data);
        uploadTask.addOnFailureListener(exception -> {
            // Handle unsuccessful uploads
        }).addOnSuccessListener(taskSnapshot -> {
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference ref = db.getReference(Objects.requireNonNull(getIntent().getStringExtra("name"))); // Key
            MovieFullInfo saveMovie = new MovieFullInfo(getIntent().getStringExtra("name"),
                    getIntent().getStringExtra("description"),
                    taskSnapshot.getMetadata().getPath(),
                    getIntent().getBooleanExtra("favorite", false));
            if (getIntent().getStringExtra("comment") != null && !Objects.equals(getIntent().getStringExtra("comment"), "")) {
                saveMovie.addComment(getIntent().getStringExtra("comment"));
            }
            ref.setValue(saveMovie); // Value

            // ...
        });
    }
}
