package com.example.memorygame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_BITMAP_1 = "com.example.myfirstapp.BITMAP_1";
    public static final String EXTRA_BITMAP_2 = "com.example.myfirstapp.BITMAP_2";
    public static final String EXTRA_BITMAP_3 = "com.example.myfirstapp.BITMAP_3";
    public static final String EXTRA_BITMAP_4 = "com.example.myfirstapp.BITMAP_4";
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
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instantiateValues();
    }

    protected void instantiateValues()
    {
        firstImageButton = (ImageButton) findViewById(R.id.firstImage);
        secondImageButton = (ImageButton) findViewById(R.id.secondImage);
        thirdImageButton = (ImageButton) findViewById(R.id.thirdImage);
        fourthImageButton = (ImageButton) findViewById(R.id.fourthImage);

        Button startGameButton = (Button) findViewById(R.id.button_Start_Game);
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

    public void startGame(View view)
    {
        if(allImagesAreSet()) {
            setupIntentWithBitmaps();
            startActivity(intent);
        }
        else
        {
            displayNotAllImagesAreSetToast();
        }
    }

    public boolean allImagesAreSet()
    {
        if(firstImageBitmap!=null && secondImageBitmap!=null && thirdImageBitmap!=null && fourthImageBitmap!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void setupIntentWithBitmaps()
    {
        intent = new Intent(this, GameActivity.class);
        intent.putExtra(EXTRA_BITMAP_1, firstImageBitmap);
        intent.putExtra(EXTRA_BITMAP_2, secondImageBitmap);
        intent.putExtra(EXTRA_BITMAP_3, thirdImageBitmap);
        intent.putExtra(EXTRA_BITMAP_4, fourthImageBitmap);
    }

    public void displayNotAllImagesAreSetToast()
    {
        Context context = getApplicationContext();
        CharSequence text = "Not all images have a photo thumbnail attached.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
