package com.example.myfitnessapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class PhotoActivity extends AppCompatActivity {

    Button buttonTakePhoto;
    ImageView imageView;
    String pathToFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        buttonTakePhoto = findViewById(R.id.buttonTakePhoto);
        imageView = findViewById(R.id.imageView);

        buttonTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchPictureTakerAction();
            }
        });
    }

    private void dispatchPictureTakerAction(){
        Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Check if their is an App to handle the intent
        if (takePhoto.resolveActivity(getPackageManager()) != null){
            // Define a file where a photo will be stored
            File photoFile = null;

            // Create this method to handle file creation
            photoFile = createPhotoFile();

            // Check if photo file is not empty
            if (photoFile != null) {
                // Get path
                String pathToFile = photoFile.getAbsolutePath();

                // Create URL for all the apps to access the file
                Uri photoURL = FileProvider.getUriForFile(PhotoActivity.this,"com.example.myfitnessapp.fileprovider",photoFile);
                takePhoto.putExtra(MediaStore.EXTRA_OUTPUT, photoURL);
                startActivityForResult(takePhoto, 1); // Start the intent
            }
            else {

            }
        }
    }

    private File createPhotoFile(){
        // Create file where the name will be stored
        String name = new SimpleDateFormat("yyyymmdd").format(new Date());
        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image1 = null;
        try{
            image1 = File.createTempFile(name,".jpg",storageDir);
        } catch (IOException e){
            Log.d("mylog", "Exception :"+ e.toString());
        }
        return image1;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1){
            Bitmap bitmap = BitmapFactory.decodeFile(pathToFile); // decode the path to File
            imageView.setImageBitmap(bitmap); //Set the results to Bitmap
        }
    }
}
