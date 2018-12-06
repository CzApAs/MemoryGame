package com.example.memorygame;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private int numberOfElements;
    private int numberOfRows;
    private int numberOfColumns;

    GridLayout gridLayout;

    private MemoryGameButton[] buttons;

    Drawable firstImageDrawable;
    Drawable secondImageDrawable;
    Drawable thirdImageDrawable;
    Drawable fourthImageDrawable;
    List<Drawable> randomImageLocations;

    private MemoryGameButton pressedButton;
    private MemoryGameButton firstSelectionButton;
    private MemoryGameButton secondSelectionButton;

    private boolean isBusy = false;

    private int numberOfFlips;   //"score"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        instantiateValues();
    }

    protected void instantiateValues()
    {
        numberOfFlips=0;

        setupGridValues();
        extractAndAssignDrawablesFromIntent();
        assignShuffledRandomLocations();
        createMemoryGameButtons();
    }

    protected void setupGridValues()
    {
        gridLayout = (GridLayout)(findViewById(R.id.grid_layout));
        numberOfRows = gridLayout.getRowCount();
        numberOfColumns = gridLayout.getColumnCount();
        numberOfElements = numberOfRows * numberOfColumns;
    }

    protected void extractAndAssignDrawablesFromIntent()
    {
        Intent intent = getIntent();
        Bitmap firstImage = (Bitmap) intent.getParcelableExtra(MainActivity.EXTRA_BITMAP_1);
        firstImageDrawable = new BitmapDrawable(getResources(), firstImage);
        Bitmap secondImage = (Bitmap) intent.getParcelableExtra(MainActivity.EXTRA_BITMAP_2);
        secondImageDrawable = new BitmapDrawable(getResources(), secondImage);
        Bitmap thirdImage = (Bitmap) intent.getParcelableExtra(MainActivity.EXTRA_BITMAP_3);
        thirdImageDrawable = new BitmapDrawable(getResources(), thirdImage);
        Bitmap fourthImage = (Bitmap) intent.getParcelableExtra(MainActivity.EXTRA_BITMAP_4);
        fourthImageDrawable = new BitmapDrawable(getResources(), fourthImage);
    }

    protected void assignShuffledRandomLocations()
    {
        randomImageLocations = new ArrayList<>();
        for (int i = 0; i < numberOfElements; i++) {
            switch(i%numberOfElements/2){  // one image is present in two locations
                case 0:
                    randomImageLocations.add(firstImageDrawable);
                    break;
                case 1:
                    randomImageLocations.add(secondImageDrawable);
                    break;
                case 2:
                    randomImageLocations.add(thirdImageDrawable);
                    break;
                case 3:
                    randomImageLocations.add(fourthImageDrawable);
                    break;
                default:
                    throw new IllegalStateException("Could not assign a drawable image to any location");
            }
        }
        Collections.shuffle(randomImageLocations);
    }

    protected void createMemoryGameButtons()
    {
        buttons = new MemoryGameButton[numberOfElements];

        for(int row=0; row<numberOfRows; row++) {
            for (int column = 0; column < numberOfColumns; column++) {
                MemoryGameButton newButton = new MemoryGameButton(this, row, column, randomImageLocations.get(row * numberOfColumns + column));
                newButton.setId(View.generateViewId());
                newButton.setOnClickListener(this);
                buttons[row * numberOfColumns + column] = newButton;  //saving the generated buttons for possible future use
                gridLayout.addView(newButton);
            }
        }
    }


    @Override
    public void onClick(View view) {
        if(isBusy)
        {
            return;
        }

        pressedButton = (MemoryGameButton) view;

        if(pressedButton.isMatched)
        {
            return;
        }

        if(firstSelectionButton == null) //first selection from a pair of images to compare
        {
            selectAndFlipFirstImage();
            return;
        }

        if(firstSelectionButton.getId() == pressedButton.getId()) //ignore attempt to select the same image
        {
            return;
        }

        if(firstSelectionButton.getFrontImage() == pressedButton.getFrontImage())
        {
            matchBothImagesAndDisableTheirButtons();

            firstSelectionButton = null;
            checkIfGameEnded();
        }

        else  // two images were chosen and did not match
        {
            secondSelectionButton = pressedButton;
            secondSelectionButton.flip();
            numberOfFlips++;

            flipBothImagesAfterDelay();
        }
    }

    private void selectAndFlipFirstImage()
    {
        firstSelectionButton = pressedButton;
        firstSelectionButton.flip();
        numberOfFlips++;
    }

    private void matchBothImagesAndDisableTheirButtons()
    {
        pressedButton.flip();
        numberOfFlips++;

        firstSelectionButton.setMatched(true);
        pressedButton.setMatched(true);

        firstSelectionButton.setEnabled(false);
        pressedButton.setEnabled(false);
    }

    private void checkIfGameEnded()
    {
        if(checkIfAllMatched())
        {
            displayGameOverScreenAlert();
        }
    }

    private boolean checkIfAllMatched() {
        boolean allImagesMatched = true;
        for (MemoryGameButton button : buttons) {
            if (!button.isMatched()) {
                return false;
            }
        }
        return true;
    }

    private void displayGameOverScreenAlert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setMessage("Game Over!\nNumber of image flips: " + numberOfFlips)
                .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Return with same images", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void flipBothImagesAfterDelay()
    {
        isBusy=true;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {  // the player is shown both images before they are flipped back
            @Override
            public void run() {
                firstSelectionButton.flip();
                secondSelectionButton.flip();
                firstSelectionButton=null;
                secondSelectionButton=null;
                isBusy=false;
            }
        }, 500);
    }


}
