package com.example.myfirstappfome.ui.Casts.addcast;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myfirstappfome.DataClasses.CastFullInfo;
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

public class AddCastImage extends AppCompatActivity implements View.OnClickListener {
    private final int Pick_image = 1;
    private MovieFullInfo movie;
    private CastFullInfo cast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cast_image);
        movie = (MovieFullInfo) getIntent().getSerializableExtra("movie");
        cast = new CastFullInfo(movie.getName(), getIntent().getStringExtra("name"),
                getIntent().getStringExtra("description"));
    }

    @Override
    public void onClick(View view) {
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
        StorageReference storageRef = storage.getReference().child("Images/" + movie.getName() + "/" + cast.getName() +"_im.jpg");
        // StorageReference imagesRef = storageRef.child("images");
        UploadTask uploadTask = storageRef.putBytes(data);
        uploadTask.addOnFailureListener(exception -> {
            // Handle unsuccessful uploads
        }).addOnSuccessListener(taskSnapshot -> {
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference ref = db.getReference().child(movie.getName()+ "/casts/"+ cast.getName()); // Key
            cast.setImage(taskSnapshot.getMetadata().getPath());
            ref.setValue(cast); // Value

            // ...
        });
    }
}