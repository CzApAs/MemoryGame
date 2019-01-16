package com.example.memorygame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_BITMAP_1 = "com.example.myfirstapp.BITMAP_1";
    public static final String EXTRA_BITMAP_2 = "com.example.myfirstapp.BITMAP_2";
    public static final String EXTRA_BITMAP_3 = "com.example.myfirstapp.BITMAP_3";
    public static final String EXTRA_BITMAP_4 = "com.example.myfirstapp.BITMAP_4";
    static final int REQUEST_IMAGE_CAPTURE_1 = 0;
    static final int REQUEST_IMAGE_CAPTURE_2 = 1;
    static final int REQUEST_IMAGE_CAPTURE_3 = 2;
    static final int REQUEST_IMAGE_CAPTURE_4 = 3;
    private Bitmap[] bitmaps;
    private ImageButton[] buttons;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instantiateValues();
    }

    protected void instantiateValues() {
        buttons = new ImageButton[4];
        bitmaps = new Bitmap[4];
        buttons[0] = (ImageButton) findViewById(R.id.firstImage);
        buttons[1] = (ImageButton) findViewById(R.id.secondImage);
        buttons[2] = (ImageButton) findViewById(R.id.thirdImage);
        buttons[3] = (ImageButton) findViewById(R.id.fourthImage);
    }

    private void dispatchTakePictureIntent(int requestCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            bitmaps[requestCode] = imageBitmap.copy(imageBitmap.getConfig(), true);
            buttons[requestCode].setImageBitmap(bitmaps[requestCode]);
        }
    }

    public void takePhotoOfFirstImage(View view) {
        dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE_1);
    }

    public void takePhotoOfSecondImage(View view) {
        dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE_2);
    }

    public void takePhotoOfThirdImage(View view) {
        dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE_3);
    }

    public void takePhotoOfFourthImage(View view) {
        dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE_4);
    }

    public void startGame(View view) {
        if (allImagesAreSet()) {
            setupIntentWithBitmaps();
            startActivity(intent);
        } else {
            displayNotAllImagesAreSetToast();
        }
    }

    public boolean allImagesAreSet() {
        return (bitmaps[0] != null && bitmaps[1] != null && bitmaps[2] != null && bitmaps[3] != null);
    }

    public void setupIntentWithBitmaps() {
        intent = new Intent(this, GameActivity.class);
        intent.putExtra(EXTRA_BITMAP_1, bitmaps[0]);
        intent.putExtra(EXTRA_BITMAP_2, bitmaps[1]);
        intent.putExtra(EXTRA_BITMAP_3, bitmaps[2]);
        intent.putExtra(EXTRA_BITMAP_4, bitmaps[3]);
    }

    public void displayNotAllImagesAreSetToast() {
        Context context = getApplicationContext();
        CharSequence text = "Not all images have a photo thumbnail attached.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
