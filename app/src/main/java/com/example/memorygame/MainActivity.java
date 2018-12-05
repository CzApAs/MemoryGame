package com.example.memorygame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE_1 = 1;
    static final int REQUEST_IMAGE_CAPTURE_2 = 2;
    static final int REQUEST_IMAGE_CAPTURE_3 = 3;
    static final int REQUEST_IMAGE_CAPTURE_4 = 4;
    private Bitmap firstImageBitmap;
    private Bitmap secondImageBitmap;
    private Bitmap thirdImageBitmap;
    private Bitmap fourthImageBitmap;
    private ImageButton firstImageButton;
    private ImageButton secondImageButton;
    private ImageButton thirdImageButton;
    private ImageButton fourthImageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstImageButton = (ImageButton) findViewById(R.id.firstImage);
        secondImageButton = (ImageButton) findViewById(R.id.secondImage);
        thirdImageButton = (ImageButton) findViewById(R.id.thirdImage);
        fourthImageButton = (ImageButton) findViewById(R.id.fourthImage);

    }

    private void dispatchTakePictureIntent(int requestCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode)
        {
            case 1:
                if (requestCode == REQUEST_IMAGE_CAPTURE_1 && resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    firstImageBitmap = imageBitmap.copy(imageBitmap.getConfig(), true);
                    firstImageButton.setImageBitmap(firstImageBitmap);
                }
                break;
            case 2:
                if (requestCode == REQUEST_IMAGE_CAPTURE_2 && resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    secondImageBitmap = imageBitmap.copy(imageBitmap.getConfig(), true);
                    secondImageButton.setImageBitmap(secondImageBitmap);
                }
                break;
            case 3:
                if (requestCode == REQUEST_IMAGE_CAPTURE_3 && resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    thirdImageBitmap = imageBitmap.copy(imageBitmap.getConfig(), true);
                    thirdImageButton.setImageBitmap(thirdImageBitmap);
                }
                break;
            case 4:
                if (requestCode == REQUEST_IMAGE_CAPTURE_4 && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                fourthImageBitmap = imageBitmap.copy(imageBitmap.getConfig(), true);
                fourthImageButton.setImageBitmap(fourthImageBitmap);
            }
        }
    }

    public void takePhotoOfFirstImage(View view)
    {
        dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE_1);
    }
    public void takePhotoOfSecondImage(View view)
    {
        dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE_2);
    }
    public void takePhotoOfThirdImage(View view)
    {
        dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE_3);
    }
    public void takePhotoOfFourthImage(View view)
    {
        dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE_4);
    }


}
