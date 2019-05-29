package com.example.myfitnessapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotoActivity extends AppCompatActivity {

    Button buttonTakePhoto;
    ImageView imageView;
    private Uri file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        setTitle("Progress");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonTakePhoto = findViewById(R.id.buttonTakePhoto);
        imageView = findViewById(R.id.imageView);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            buttonTakePhoto.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                buttonTakePhoto.setEnabled(true);
            }
        }
    }

    public void takePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = Uri.fromFile(getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                imageView.setImageURI(file);
            }
        }
    }

    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
    }

    /* Old code commented out in case further reference is needed. Should be removed at some stage.

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

    */
}
