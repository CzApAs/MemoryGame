package com.example.memorygame;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.GridLayout;

public class MemoryGameButton extends android.support.v7.widget.AppCompatButton {

    protected int row;
    protected int column;

    protected boolean isFlipped = false;
    protected boolean isMatched = false;

    protected Drawable frontImage;
    protected Drawable backImage;

    public MemoryGameButton(Context context, int row, int column, Drawable frontImage)
    {
        super(context);

        this.row=row;
        this.column=column;
        this.frontImage=frontImage;

        backImage = context.getDrawable(R.drawable.questionmark);

        setBackground(backImage);

        GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(row), GridLayout.spec(column));

        params.width = (int) getResources().getDisplayMetrics().density*100;
        params.height = (int) getResources().getDisplayMetrics().density*100;

        setLayoutParams(params);
    }

    public Drawable getFrontImage() {
        return frontImage;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }


    public void flip(){
        if(isMatched){
            return;
        }

        if(isFlipped){
            setBackground(backImage);
            isFlipped = false;
        }

        else{
            setBackground(frontImage);
            isFlipped = true;
        }


    }
}
